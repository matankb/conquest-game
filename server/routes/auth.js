const mongoose = require('mongoose');
const Player = require('../models/Player');

const { socketMessages } = require('../config');

module.exports = function(socket) {

  async function playerLogin(authData) {

    const player = await Player.findOne({ email: authData.email });

    if (!player) {
      return socket.emit(socketMessages.PLAYER_LOGIN_FAILURE, {
        message: "You're not signed up!",
      });
    } else if (!(await player.validPassword(authData.password))) {
      return socket.emit(socketMessages.PLAYER_LOGIN_FAILURE, {
        message: 'Wrong Password. Please try again',
      });
    }

    const currentGameToken = mongoose.Types.ObjectId();
    player.currentGameToken = currentGameToken;
    await player.save();

    socket.emit(socketMessages.PLAYER_LOGIN_SUCCESS, { token: currentGameToken });

  }

  async function playerRegister(playerData) {

    // check to see if email/username already exists
    const playerEmailCheck = await Player.findOne({ email: playerData.email });
    const playerUsernameCheck = await Player.findOne({ username: playerData.username });

    if (playerEmailCheck) {
      return socket.emit(socketMessages.PLAYER_REGISTER_FAILURE, {
        message: 'That email is taken.',
      });
    } else if (playerUsernameCheck) {
      return socket.emit(socketMessages.PLAYER_REGISTER_FAILURE, {
        message: 'That username is taken. Please try another.',
      });
    }

    const player = new Player();

    player.username = playerData.username;
    player.email = playerData.email;
    player.password = await player.generateHash(playerData.password);

    const currentGameToken = mongoose.Types.ObjectId();
    player.currentGameToken = currentGameToken;

    await player.save();
    socket.emit(socketMessages.PLAYER_REGISTER_SUCCESS, { token: currentGameToken });

  }

  return {
    playerLogin,
    playerRegister,
  };

};
