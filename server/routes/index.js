const { socketMessages } = require('../config');

const auth = require('./auth');
const pregame = require('./pregame');
const game = require('./game');

module.exports = function (app, io) {

  io.on('connection', socket => {

    const authRoutes = auth(socket, io);
    const pregameRoutes = pregame(socket, io);
    const gameRoutes = game(socket, io);

    // auth
    socket.on(socketMessages.PLAYER_LOGIN, authRoutes.playerLogin);
    socket.on(socketMessages.PLAYER_REGISTER, authRoutes.playerRegister);

    // pregame
    socket.on(socketMessages.JOIN_PRIVATE_GAME, pregameRoutes.joinPrivateGame);

    // game
    socket.on(socketMessages.SUBMIT_ORDER_FORM, gameRoutes.submitOrderForm);

  });

};
