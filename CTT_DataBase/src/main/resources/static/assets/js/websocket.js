	var j;
	var hostWS;
	
	var notify = {};

	notify.write = function(data) {
		

		if(localStorage.getItem('j') == null) {
			  j = 0;
		}
		else { j = localStorage.getItem('j');}
		
		localStorage.setItem('bottone' + j, data);
		localStorage.setItem('j',++j);

		setTimeout(reload,1000);
	};

	function reload(){
		location.reload();
	}
	

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
        channel.connect('ws:/'+hostWS+'/ws/notifiche');