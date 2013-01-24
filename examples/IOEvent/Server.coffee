io = require('socket.io').listen 3001

class Palette
    @colours: ['white','black','orange']

class Sheep
    constructor: (n) -> 
        @colour = Palette.colours[0]
        if n.match /3/ 
            @colour = Palette.colours[1]
        else if n.match /7/
            @colour = Palette.colours[2]


class Flock
    constructor: (count) -> 
        @sheep = []
        for i in [1..count] 
            @sheep.push new Sheep(''+i)



io.sockets.on 'connection', (socket) -> 

    socket.emit 'server:sends:flock:of:sheep', new Flock(500), Palette
