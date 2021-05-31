	var j= 0;
	var hostWS;

	var notify = {};

	notify.write = function(data) {
	    for(let i = 0; i < localStorage.length; i++) {
            if(localStorage.key(i).substring(0,15) == "bottoneScadenza") {
               localStorage.removeItem(localStorage.key(i));
            }
        }
		localStorage.setItem('bottoneScadenza' + j, data);
	};


        var channel = {};

        channel.socket = null;


        channel.connect = function(host) {
   	    	channel.socket = new WebSocket(host);


            channel.socket.onmessage = function (message) {
                notify.write(message.data);
            };
        };
 		hostWS = document.location.origin;
 		hostWS = hostWS.substring(6);
        channel.connect('ws:/'+hostWS+'/ws/saccheInScadenza');