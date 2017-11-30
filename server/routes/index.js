const socket = require('socket.io');
const mongoose = require('mongoose');

const Player = require('../models/Player');
const { socketMessages: messages } = require('../config');

module.exports = function (app, io) {
    
    io.on('connection', () => {
        
        socket.on(message.PLAYER_LOGIN, async authData => {
            const player = await Player.find({ username: authData.username });
            if (!player) {
                // send no account message
                return;
            }
            player.currentGameToken = mongoose.Types.ObjectId();
            await player.save();
            socket.emit(messages.PLAYER_LOGIN_SUCCESS, currentGameToken);           
        });

    })

}