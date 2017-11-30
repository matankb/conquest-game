const config = {
    server: {
        IP: 'localhost',
        PORT: 5000,
    },
    db: {
        URL: 'mongodb://127.0.0.1/conquest-game'
    },
    socketMessages: {
        PLAYER_LOGIN: 'accountInfo',
        PLAYER_LOGIN_SUCCESS: 'playerLoginSuccess',
    }
}

module.exports = config;