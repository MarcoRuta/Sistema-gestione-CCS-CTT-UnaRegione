	var hostWSSma;
	var smaltimento;
	var notifySmaltimento = {};


	notifySmaltimento.write = function(smaltimento) {
    	    for(let i = 0; i < localStorage.length; i++) {
                if(localStorage.key(i) == "smaltimentoBottone") {
                   localStorage.removeItem(localStorage.key(i));

                    while (document.getElementById('divSma').childElementCount != 0){
                        var button = document.getElementById('btnSma');
                        document.getElementById('divSma').removeChild(button);
                    }
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