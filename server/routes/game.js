const Game = require('../models/Game');
const { socketMessages } = require('../config');


export default function (socket, io) {

  async function submitOrderForm(data) {

    const game = await Game.findById(data.gameId);

    game.pendingOrderForms.push(data.orderForm);
    await game.save();

    if (game.pendingOrderForms.length === game.players.length) {
      // calculate new game state
      io.in(game.id).emit(socketMessages.NEW_GAME_STATE, game.data);
    }

  }

  return { submitOrderForm };

}
