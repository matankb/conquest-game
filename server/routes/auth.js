const mongoose = require('mongoose');
const Player = require('../models/Player');

const { socketMessages } = require('../config');

export default function(socket) {

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

    const player = new Player();

    player.username = playerData.username;
    player.email = playerData.email;
    player.password = await player.generateHash(playerData.password);

    await player.save();
    socket.emit(socketMessages.PLAYER_REGISTER_SUCCESS);

  }

  return {
    playerLogin,
    playerRegister,
  };

}
