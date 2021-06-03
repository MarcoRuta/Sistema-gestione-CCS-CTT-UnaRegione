	var hostWSSma;
	var smaltimento;
	var notifySmaltimento = {};


	notifySmaltimento.write = function(smaltimento) {
    	    for(let i = 0; i < localStorage.length; i++) {
                if(localStorage.key(i) == "smaltimentoBottone") {
                   localStorage.removeItem(localStorage.key(i));
                }
            }

    		localStorage.setItem('smaltimentoBottone', smaltimento);
    		smaltimentoload();
    	};



        var channelSma = {};

        channelSma.socket = null;


        channelSma.connect = function(host) {
   	    	channelSma.socket = new WebSocket(host);


            channelSma.socket.onmessage = function (message) {
                notifySmaltimento.write(message.data);
            };
        };
 		hostWSSma = document.location.origin;
 		hostWSSma = hostWSSma.substring(6);
        channelSma.connect('ws:/'+hostWS+'/ws/SmaltimentoSacche');