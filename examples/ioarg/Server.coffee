io = require('socket.io').listen 3001

class Clone 
    constructor: (@count, @colour, @thing) ->

io.sockets.on 'connection', (socket) -> 

    socket.on 'event:sent:to:server', (knowledge, afterthought) -> 

        console.log knowledge
        console.log afterthought
