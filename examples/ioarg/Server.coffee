io = require('socket.io').listen 3001

#
#
#

class Clone 
    constructor: (@count, @colour, @thing) ->

io.sockets.on 'connection', (socket) -> 

    socket.on 'event:sent:to:server', (knowledge, afterthought) -> 

        console.log knowledge
        console.log afterthought

        socket.emit 'server:sends:cloned:sheep', new Clone(2, 'black', 'sheep'), 
            instruction1: 'Make warmer jerseys'
            instruction2: 'Make double ply cardigans'

