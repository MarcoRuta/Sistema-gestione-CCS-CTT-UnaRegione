	var j= 0;
	var hostWS;

	var notify = {};

	notify.write = function(data) {
	    for(let i = 0; i < localStorage.length; i++) {
            if(localStorage.key(i) == "scadenzaBottone") {
               localStorage.removeItem(localStorage.key(i));

                while (document.getElementById('divNot').childElementCount != 0){
                    var button = document.getElementById('btnSc');
                    document.getElementById('divNot').removeChild(button);
                }
            }
        }

		localStorage.setItem('scadenzaBottone', data);

		sessionload();
	};


        var channel = {};

        channel.socket = null;


        channel.connect = function(host) {
            let connected = localStorage.getItem("connected");
   	    	channel.socket = new WebSocket(host);


            channel.socket.onmessage = function (message) {
                notify.write(message.data);
            };
        };
 		hostWS = document.location.origin;
 		hostWS = hostWS.substring(6);
        channel.connect('ws:/'+hostWS+'/ws/saccheInScadenza');