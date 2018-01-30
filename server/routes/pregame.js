const Player = require('../models/Player');
const Game = require('../models/Game');
const { socketMessages } = require('../config');

export default function(socket, io) {

  async function joinPrivateGame(data) {

    const game = await Game.findById(data.gameId);
    const player = await Player.findOne({ currentGameToken: data.playerToken });

    game.players.push(player);
    await game.save();

    socket.join(game.id);

    if (game.players.length === 3) {
      // init game state
      game.data = {
        round: 0,
        players: game.players.map(p => ({
          id: p.id,
          money: 5000,
          regionsOwned: [],
          armies: [],
        })),
        regions: [], // TODO: Import map data
      };


      io.in(game.id).emit(socketMessages.INITIAL_GAME_STATE, game.data);
    }

  }

  return {
    joinPrivateGame,
  };

}
