const mongoose = require('mongoose');

const Player = require('../models/Player');
const { socketMessages } = require('../config');

module.exports = function (app, io) {

  io.on('connection', socket => {

    socket.on(socketMessages.PLAYER_LOGIN, async authData => {
      const player = await Player.findOne({ email: authData.email });

      if (!player) {
        socket.emit(socketMessages.PLAYER_LOGIN_FAILURE, "You're not signed up!");
      } else if (!(await player.validPassword(authData.password))) {
        socket.emit(socketMessages.PLAYER_LOGIN_FAILURE, 'Wrong Password. Please try again');
      }

      const currentGameToken = mongoose.Types.ObjectId();
      player.currentGameToken = currentGameToken;
      await player.save();
      socket.emit(socketMessages.PLAYER_LOGIN_SUCCESS, currentGameToken);
    });

    socket.on(socketMessages.PLAYER_REGISTER, async playerData => {
      const player = new Player();
      player.username = playerData.username;
      player.email = playerData.email;
      player.password = player.generateHash(playerData.password);
      await player.save();
      socket.emit(socketMessages.PLAYER_REGISTER_SUCCESS);
    });

  });

};
