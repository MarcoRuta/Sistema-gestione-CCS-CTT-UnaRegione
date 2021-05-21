			
	var xhttp = new XMLHttpRequest();
	var token = sessionStorage.getItem("token");
	
	var j;
	var obj;
	var res;
	var data;
	
	var notify = {};

	notify.write = function(data) {
		
		var str = "";
		obj = JSON.parse(data);
		
		for(var i=0; i < obj.listaSeriali.length; i++) {
			str = str + obj.listaSeriali[i] + ",";
		}

		//for(i = 0; i < obj.listaSeriali.length ; i++) {
		str2 = str.substring(0,str.length-1);
		sr = str2.substring(0,15);
		var btn = document.createElement("button");
		btn.className='btn btn-warning';
		btn.innerHTML = "Sacche: " + str2 + "<br>Ente: " + obj.enteRichiedente + "<br>Indirizzo: " + obj.indirizzoEnte;
		btn.type = 'submit';
		btn.value = sr;
		btn.style = 'width: 98%; max-height: 280px; vertical-align: baseline; margin: 1%; text-align: left;';
		
		 /*####### EVASIONE SACCA ######*/
		btn.onclick = function() {
	
		    
		
		    var params = 'listaSeriali='+str +'&enteRichiedente='+obj.enteRichiedente+'&indirizzoEnte='+obj.indirizzoEnte;

		    var url = "http://127.0.0.1:8080/rest/magazziniere/evasione";
		    xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
			    res = this.response;
			    setTimeout(creaPdf,1000);
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
        
		/*#############################*/
		document.getElementById("divNot").appendChild(btn);
		//}
		
		if(localStorage.getItem('j') == null) {
			  j = 0;
		}
		else { j = localStorage.getItem('j');}
		
		localStorage.setItem('bottone' + j, data);
		localStorage.setItem('j',++j);
	};
	
	/*####### CREA PDF FUNCTION ######*/
	function creaPdf() {
		alert("STO CREANDO IL PDF!");
		var doc = new jsPDF();
		
		/*var host = "http://"+ window.location.hostname;*/
		
		doc.setFontSize(25);
		doc.text(20, 20, "Evasione sacche");
		
		doc.setLineWidth(0.5);
		doc.line(20, 25, 197, 25);
	
	        doc.setFontSize(20);
		doc.text(20, 40, res);


		//doc.text(20,45,indirizzo.value);
	
		// Save the PDF
		doc.save('EvasioneSacche.pdf');
	}
	 /*#############################*/
	 
        /*####### REMOVE FUNCTION ######*/
	function removeNotify() {
		var x = document.activeElement;
		for(let i = 0; i < localStorage.length; i++) {
			if(localStorage.key(i).substring(0,7) == "bottone") {
				data = localStorage.getItem(localStorage.key(i));
				if(JSON.parse(data).listaSeriali[0] == x.value) {
					localStorage.removeItem(localStorage.key(i));
					location.reload();
				}
			}
		}
	}
	/*#############################*/
	
        var console = {};

        console.write = function(message) {
            var p = document.createElement('p');
            p.innerHTML = message;
            document.getElementById('console').appendChild(p);
        };

        var channel = {};
        
        channel.socket = null;


        channel.connect = function(host) {
   	    	channel.socket = new WebSocket(host);
   	    	
            channel.socket.onopen = function () {
               		console.write("Connection open");
            	};

           	channel.socket.onclose = function () {
              	console.write("Connection close");
            };

            channel.socket.onmessage = function (message) {
                notify.write(message.data);
            };
        };
 
        channel.connect('ws://127.0.0.1:8080/ws/mosquitoTry');