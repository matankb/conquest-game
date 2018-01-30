const mongoose = require('mongoose');
const bcrypt = require('bcryptjs');

const playerSchema = new mongoose.Schema({
  email: String,
  username: String,
  name: String,
  password: String,
  currentGameToken: String,
});

playerSchema.methods.generateHash = function(password) {
  return bcrypt.hash(password, 8); // returns promise
};

playerSchema.methods.validPassword = function(password) {
  return bcrypt.compare(password, this.password); // returns promise
};


const Player = mongoose.model('User', playerSchema);

module.exports = Player;
