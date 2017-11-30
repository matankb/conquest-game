const config = {
  server: {
    IP: 'localhost',
    PORT: 5000,
  },
  db: {
    URL: 'mongodb://127.0.0.1/conquest-game',
  },

  // messages for client-server communication
  socketMessages: {
    PLAYER_REGISTER: 'accountRegister',
    PLAYER_REGISTER_SUCCESS: 'accountRegister',
    PLAYER_LOGIN: 'accountInfo',
    PLAYER_LOGIN_SUCCESS: 'loginSuccess',
    PLAYER_LOGIN_FAILURE: 'loginFailure',
  },

};

module.exports = config;
