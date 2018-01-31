const mongoose = require('mongoose');

const gameSchema = new mongoose.Schema({
  name: String,
  players: [{ type: mongoose.Schema.Types.ObjectId, ref: 'Player' }],
  inProgress: { type: Boolean, default: false },
  data: {
    round: Number,
    players: [{
      playerToken: { type: mongoose.Schema.Types.ObjectId },
      money: Number,
      regionsOwned: [{ type: mongoose.Schema.Types.ObjectId }],
      armies: [{
        region: { type: mongoose.Schema.Types.ObjectId },
        amount: Number,
      }],
    }],
    regions: [{
      id: { type: mongoose.Schema.Types.ObjectId },
      connectors: [{ type: mongoose.Schema.Types.ObjectId }],
    }],
    pendingOrderForms: [{
      playerToken: { type: mongoose.Schema.Types.ObjectId },
      regionsBuying: [{ type: mongoose.Schema.Types.ObjectId }],
      unitsBuying: [{
        region: { type: mongoose.Schema.Types.ObjectId },
        amount: Number,
      }],
      armiesBuying: [{
        region: { type: mongoose.Schema.Types.ObjectId },
        amount: Number,
      }],
      armiedMoving: [{
        fromRegion: { type: mongoose.Schema.Types.ObjectId },
        toRegion: { type: mongoose.Schema.Types.ObjectId }, // region to move to
        amount: Number,
      }],
      armiesAttacking: [{
        fromRegion: { type: mongoose.Schema.Types.ObjectId },
        region: { type: mongoose.Schema.Types.ObjectId }, // region to attack

      }],
      maintenanceCost: Number,
    }],
  },
});

const Game = mongoose.model('Game', gameSchema);

module.exports = Game;
