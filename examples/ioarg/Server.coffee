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

        socket.emit 'event:sent:to:client', new Clone( 2, 'sheep')
