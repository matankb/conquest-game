const http = require('http');

const express = require('express');
const mongoose = require('mongoose');
const socketio = require('socket.io');

const config = require('./config');
const routes = require('./routes');

// connect to mongodb
mongoose.Promise = global.Promise;
mongoose.connect(config.db.URL, {
  useMongoClient: true,
});


const app = express();
const server = http.createServer(app);
const io = socketio(server);

routes(app, io);

server.listen(config.server.PORT, config.server.IP, () => {
  console.log('Server Started!'); // eslint-disable-line no-console
});

