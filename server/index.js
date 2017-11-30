const http = require('http');

const express = require('express');
const mongoose = require('mongoose');
const socketio = require('socket.io');

const config = require('./config');
// connect to mongodb
mongoose.Promise = global.Promise;
mongoose.connect(config.db.URL, {
  useMongoClient: true,
});


const app = express();
const server = http.createServer(app);
const io = socketio(server);
server.listen(config.server.PORT, config.server.IP, () => {
    console.log('Server Started!');
})