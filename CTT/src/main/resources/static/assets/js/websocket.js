			
//	var xhttp = new XMLHttpRequest();
//	var token = sessionStorage.getItem("token");
	
	var j;
//	var obj;
//	var res;
	var hostWS;
//	var urlPdf;
	
	var notify = {};

	notify.write = function(data) {
		
		
/*		var str = "";
		obj = JSON.parse(data);
		
		for(var i=0; i < obj.listaSeriali.length; i++) {
			str = str + obj.listaSeriali[i] + ",";
		}

		str2 = str.substring(0,str.length-1);
		var btn = document.createElement("button");
		btn.className='btn btn-warning';
		btn.innerHTML = "Sacche: " + str2 + "<br>Ente: " + obj.enteRichiedente + "<br>Indirizzo: " + obj.indirizzoEnte;
		btn.type = 'submit';
		btn.value = str;
		btn.style = 'width: 98%; max-height: 280px; vertical-align: baseline; margin: 1%; text-align: left;';
		
		 /*####### EVASIONE SACCA ######
		btn.onclick = function() {

			var x = document.activeElement;
		    var params = 'listaSeriali='+ x.value +'&enteRichiedente='+obj.enteRichiedente+'&indirizzoEnte='+obj.indirizzoEnte;
			hostWS = document.location.origin;
		    var url = hostWS + "/rest/magazziniere/evasione";
		    xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
			    urlPdf = xhttp.getResponseHeader("Content-Location");
			    creaPdf();
			    setTimeout(removeNotify,1500);
			}else if(this.readyState == 4 && this.status != 200) {
				//QUALCOSA
				 alert(this.response);
			}
	    
		    };
		    
		    xhttp.onerror = function(){
			alert( "Sacca non esistente");
		    };
	    
	    
		    xhttp.open("PUT", url, true);
		    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		    xhttp.setRequestHeader('Authorization', 'Basic '+token);
		    xhttp.withCredentials = true;
		    xhttp.send(params);
	
		};
    
		/*#############################
		document.getElementById("divNot").appendChild(btn);
		
		*/
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
	
	/*####### CREA PDF FUNCTION ######
      function creaPdf() {
              var xhr = new XMLHttpRequest();
              xhr.open("GET", urlPdf, true);
              xhr.setRequestHeader('Authorization', 'Basic '+token);
              xhr.withCredentials = true;
              xhr.responseType = 'arraybuffer';
              xhr.send();

              xhr.onreadystatechange = function() {
                if (xhr.readyState == 4){
                    var blobSrc = window.URL.createObjectURL(new Blob([this.response], { type: 'application/pdf'}));
                    window.open(blobSrc);
                }
              };
      }
      /*#############################*/
    
      /*####### REMOVE FUNCTION ######
      function removeNotify() {
          var x = document.activeElement;
          let sr = x.value.substring(0,15);
          for(let i = 0; i < localStorage.length; i++) {
                  if(localStorage.key(i).substring(0,7) == "bottone") {
                          data = localStorage.getItem(localStorage.key(i));
                          if(JSON.parse(data).listaSeriali[0] == sr) {
                              localStorage.removeItem(localStorage.key(i));
                              location.reload();
                          }
                      
                  }
           }
          
      }
      /*#############################*/
	

        var channel = {};
        
        channel.socket = null;


        channel.connect = function(host) {
   	    	channel.socket = new WebSocket(host);
   	    	
            /*channel.socket.onopen = function () {
               		console.write("Connection open");
            	};

           	channel.socket.onclose = function () {
              	console.write("Connection close");
            };*/

            channel.socket.onmessage = function (message) {
                notify.write(message.data);
            };
        };
 		hostWS = document.location.origin;
 		hostWS = hostWS.substring(6);
        channel.connect('ws://'+hostWS+'/ws/notifiche');