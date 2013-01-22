io = require('socket.io').listen 3001

#
#
#

class Clone
    constructor: (@count, @thing) ->
        @description = "#{@count*=2} #{@thing}"
        console.log @description

io.sockets.on 'connection', (socket) -> 

    socket.on 'event:sent:to:server', (knowledge, afterthought) -> 

        console.log knowledge
        console.log afterthought

        socket.emit 'server:sends:cloned:sheep', new Clone( 2, 'sheep'), instruction1: 'Make warmer jerseys.'

