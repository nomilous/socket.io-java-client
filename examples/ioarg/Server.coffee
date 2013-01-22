io = require('socket.io').listen 3001

io.sockets.on 'connection', (socket) -> 

    socket.on 'event:sent', (box1, box2) -> 

        console.log box1, box2

