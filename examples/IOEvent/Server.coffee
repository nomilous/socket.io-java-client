io = require('socket.io').listen 3001

colours = ['white','black','orange']

class Sheep
    constructor: (n) -> 
        @colour = colours[0]
        if n.match /3/ 
            @colour = colours[1]
        else if n.match /7/
            @colour = colours[2]


class Flock
    constructor: (count) -> 
        @sheep = []
        for i in [1..count] 
            @sheep.push new Sheep(''+i)



io.sockets.on 'connection', (socket) -> 

    socket.emit 'server:sends:flock:of:sheep', new Flock(500), colours: colours
