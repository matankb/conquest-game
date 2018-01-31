const Player = require('../models/Player');
const Game = require('../models/Game');
const { socketMessages } = require('../config');

module.exports = function(socket, io) {

  function generateInitialGameState(game) {
    return {
      round: 0,
      players: game.players.map(p => ({
        id: p.id,
        money: 5000,
        regionsOwned: [],
        armies: [],
      })),
      regions: [], // TODO: Import map data
    };
  }

  async function joinPrivateGame(data) {

    const game = await Game.findById(data.gameId);
    const player = await Player.findOne({ currentGameToken: data.token });

    game.players.push(player);
    await game.save();

    socket.join(game.id);

    if (game.players.length === 4) {
      // init game state
      game.data = generateInitialGameState(game);
    }

    io.in(game.id).emit(socketMessages.INITIAL_GAME_STATE, game.data);
  }


  async function joinPublicGame(data) {

    let game = await Game.findOne({ inProgress: false });
    const player = await Player.findOne({ currentGameToken: data.token });

    if (!game) {
      game = new Game(); // init new game if all are in progress
    }

    game.players.push(player);
    socket.join(game.id);
    await game.save();

  }

  return {
    joinPrivateGame,
    joinPublicGame,
  };

};
