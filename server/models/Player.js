const mongoose = require('mongoose');
const bcrypt = require('bcryptjs');

const playerSchema = new mongoose.Schema({
  email: String,
  name: String,
  password: String,
});

playerSchema.methods.generateHash = function(password) {
  return bcrypt.hash(password, 8);  // returns promise
};

playerSchema.methods.validPassword = function(password) {
  return bcrypt.compare(password, this.localAuth.password); // returns promise
};


const Player = mongoose.model('User', playerSchema);

module.exports = Player;