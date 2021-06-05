var hostWS;

	var notifyRisultati = {};

	notifyRisultati.write = function(data) {
	    for(let i = 0; i < localStorage.length; i++) {
            if(localStorage.key(i) == "risultatoBottone") {
               localStorage.removeItem(localStorage.key(i));

                while (document.getElementById('divRis').childElementCount != 0){
                    var button = document.getElementById('btnRis');
                    document.getElementById('divRis').removeChild(button);
                }
            }
        }

		localStorage.setItem('risultatoBottone', data);

		risultatiSessionload();
	};


        var channelRisultati = {};

        channelRisultati.socket = null;


        channelRisultati.connect = function(host) {
            let connected = localStorage.getItem("connected");
   	    	channelRisultati.socket = new WebSocket(host);


            channelRisultati.socket.onmessage = function (message) {
                notifyRisultati.write(message.data);
            };
        };
 		hostWS = document.location.origin;
 		hostWS = hostWS.substring(6);
        channelRisultati.connect('ws:/'+hostWS+'/ws/rispostaRicercaGlobale');