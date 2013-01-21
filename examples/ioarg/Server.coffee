io = require('socket.io').listen 3001

io.sockets.on 'connection', (socket) -> 

    socket.on 'event:example', (args) -> 

        console.log args

