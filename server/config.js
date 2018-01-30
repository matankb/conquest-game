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
    // auth
    PLAYER_REGISTER: 'accountRegister',
    PLAYER_REGISTER_SUCCESS: 'accountRegister',
    PLAYER_LOGIN: 'accountInfo',
    PLAYER_LOGIN_SUCCESS: ' loginSuccess',
    PLAYER_LOGIN_FAILURE: 'loginFailure',
    // pregame
    CREATE_PRIVATE_GAME: 'createPrivateGame',
    JOIN_PRIVATE_GAME: 'joinPrivateGame',
    JOIN_RANDOM_GAME: 'joinRandomGame',
    // game
    INITIAL_GAME_STATE: 'initialGameState',
    SUBMIT_ORDER_FORM: 'submitOrderForm',
    NEW_GAME_STATE: 'newGameState',
  },

};

module.exports = config;
