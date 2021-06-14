        var token = sessionStorage.getItem("token");
        var ruolo = sessionStorage.getItem("ruolo");
        var host = document.location.origin;
        var objRl , objBgSend, objBgReceive;

         function verificoToken(){
                        if(token == null || ruolo != 'AmministratoreCCS'){
                            alert("E' necessario effettuare il login per accedere a questa risorsa");
                            window.location.href='Login.html';
                       }
         }

        function openTab(evt, queryName) {
            let i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }

            tablinks = document.getElementsByClassName("tablinks");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].className = tablinks[i].className.replace(" active", "");
            }
            document.getElementById(queryName).style.display = "block";
            evt.currentTarget.className += " active";
        }

        /*###############################
        *       QUERY DIPENDENTI
        *################################*/
        function queryDipendents(){

            let table = document.getElementById('table');

            let btns = document.getElementsByName("pressBtn");
            let ruolo;

            for (let i = 0; i < btns.length; i++) {
                      if (btns[i].checked)
                          ruolo = btns[i].value;
            }

            let xhttp = new XMLHttpRequest();
            let url = host + "/rest/CCS/reportDipendentiCCS?ruolo="+ruolo;

            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {

                    objRl = JSON.parse(this.responseText);
                    if(table.rows.length != 1) {
                      while(table.rows.length != 1) {
                           table.deleteRow(1);
                      }
                    }
                    addPrint(table);
                    }
                else if (this.readyState == 4 && this.status != 200) {
                  alert(this.response);
                }

            };

            xhttp.open("GET", url, true);
            xhttp.setRequestHeader('Authorization', 'Basic '+token);
            xhttp.withCredentials = true;
            xhttp.send();

        }


        function addPrint(table) {

              let row;

                for(let o of Object.keys(objRl)) {
                        let name = o.substring(17,23);
                        console.log(name);
                        let v= objRl[o];
                        for(let i =0; i < v.length; i++) {
                            row = document.createElement('tr');
                            row.appendChild(document.createElement('td'));
                            row.appendChild(document.createElement('td'));
                            row.appendChild(document.createElement('td'));
                            row.appendChild(document.createElement('td'));
                            row.appendChild(document.createElement('td'));
                            row.cells[0].innerHTML = name;
                            row.cells[1].innerHTML = v[i].cdf.codiceFiscale;
                            row.cells[2].innerHTML = v[i].nome;
                            row.cells[3].innerHTML = v[i].cognome;
                            row.cells[4].innerHTML = v[i].dataDiNascita;
                            table.appendChild(row);
                            console.log(v[i]);
                        }

                }
        }



       /*###############################
        *       QUERY SACCHE MAGAZZINO
        *################################*/
        function queryNumberOfBags(){

            let ctxBar = document.getElementById("bar-chart");
            ctxBar.remove();
            document.getElementById("chartBags").innerHTML = '<canvas id="bar-chart" width="800" height="450"></canvas>';


            let obj;
            let xhttp = new XMLHttpRequest();
            let url = host + "/rest/CCS/reportStatisticoSaccheCCS";

            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    obj = JSON.parse(this.responseText);
                    setCanvasSacche(obj);
                    }
                else if (this.readyState == 4 && this.status != 200) {
                  alert(this.response);
                }

            };

            xhttp.open("GET", url, true);
            xhttp.setRequestHeader('Authorization', 'Basic '+token);
            xhttp.withCredentials = true;
            xhttp.send();

            setCanvasSacche = function(obj) {
                ctxBar = document.getElementById("bar-chart").getContext("2d");
                var myChartBar = new Chart(ctxBar, {
                      type: 'bar',
                      data: {
                          labels: [
                                  'SACCHE DISPONIBILI IN MAGAZZINO',
                                ],
                                datasets: [{
                                    label: ['A+'],
                                    data: [obj.Ap],
                                    backgroundColor: [
                                      'rgb(255, 200, 50)'
                                    ],
                                    hoverOffset: 4
                                }, {
                                    label: ['A-'],
                                    data: [obj.Am],
                                    backgroundColor: [
                                      'rgb(255, 215, 0)'
                                    ],
                                }, {
                                    label: ['B+'],
                                    data: [obj.Bp],
                                    backgroundColor: [
                                      'rgb(0, 150, 50)'
                                    ],
                                }, {
                                    label: ['B-'],
                                    data: [obj.Bm],
                                    backgroundColor: [
                                      'rgb(0, 230, 50)'
                                    ],
                                }, {
                                    label: ['AB+'],
                                    data: [obj.ABp],
                                    backgroundColor: [
                                      'rgb(230, 0, 0)'
                                    ],
                                }, {
                                    label: ['AB-'],
                                    data: [obj.ABm],
                                    backgroundColor: [
                                      'rgb(230, 40, 0)'
                                    ],
                                }, {
                                    label: ['0+'],
                                    data: [obj.ZEROp],
                                    backgroundColor: [
                                      'rgb(0, 100, 255)'
                                    ],
                                }, {
                                    label: ['0-'],
                                    data: [obj.ZEROm],
                                    backgroundColor: [
                                      'rgb(0, 150, 255)'
                                    ],
                                }]
                      },

                      options: {
                          responsive: true,
                          maintainAspectRatio: false,
                          scales: {
                              yAxes: [{
                                  ticks: {
                                      beginAtZero:true
                                  }
                              }]
                          }
                      }

                  });

                myChartBar.update();
            }

        }

       /*###############################
        *       QUERY SACCHE INVIATE
        *################################*/
        function queryNumberOfBagsSendByTemporalDate() {

            var el = document.getElementById('myTable');

            if(datein.value != "") {
                let xhttp = new XMLHttpRequest();
                let tableSangueSend = document.getElementById('tableSangueSend');

                let params = "dataInizio=" + datein.value + "&dataFine=" + dateout.value;
                url = host + "/rest/CCS/reportSaccheInviateCCS?"+params;

                xhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        objBgSend = JSON.parse(this.responseText);
                            if(typeof(el) != 'undefined' && el != null) {
                                tableSangueSend.removeChild(el);
                            }
                                addPrintBagsSend(tableSangueSend);

                        }
                    else if (this.readyState == 4 && this.status != 200) {
                      alert(this.response);
                    }

                };

                xhttp.open("GET", url, true);
                xhttp.setRequestHeader('Authorization', 'Basic '+token);
                xhttp.withCredentials = true;
                xhttp.send();
            }
            else {
                alert("Compila tutti i campi.");
                refresh();
            }

        }

        function refresh() {
            document.getElementById('datein').value = "";
            document.getElementById('dateout').value = "";
        }


        function addPrintBagsSend(tableSangueSend) {
            var row;
            let tbody = document.createElement('tbody');
            tbody.id = 'myTable';
                for(let i = 0; i < objBgSend.length; i++) {

                    row = document.createElement('tr');
                    row.appendChild(document.createElement('td'));
                    row.appendChild(document.createElement('td'));
                    row.appendChild(document.createElement('td'));
                    row.appendChild(document.createElement('td'));
                    row.appendChild(document.createElement('td'));
                    row.appendChild(document.createElement('td'));
                    row.appendChild(document.createElement('td'));
                    row.cells[0].innerHTML = objBgSend[i].seriale.seriale;
                    row.cells[1].innerHTML = objBgSend[i].gruppoSanguigno;
                    row.cells[2].innerHTML = objBgSend[i].dataArrivo;
                    row.cells[3].innerHTML = objBgSend[i].dataAffidamento;
                    row.cells[4].innerHTML = objBgSend[i].enteDonatore;
                    row.cells[5].innerHTML = objBgSend[i].enteRichiedente;
                    row.cells[6].innerHTML = objBgSend[i].indirizzoEnte;
                    tbody.appendChild(row);
              }
              tableSangueSend.appendChild(tbody);


        }

        /*###############################
        *       QUERY SACCHE RICEVUTE
        *################################*/
        function queryNumberOfBagsReceivedByTemporalDate() {

            var el = document.getElementById('myTableRec');

            if(dateIn.value != "") {
                let xhttp = new XMLHttpRequest();
                let tableSangueRec = document.getElementById('tableSangueRec');
                let params = "dataInizio=" + dateIn.value + "&dataFine=" + dateOut.value;
                url = host + "/rest/CCS/reportSaccheRicevuteCCS?"+params;

                xhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        objBgReceive = JSON.parse(this.responseText);
                         if(typeof(el) != 'undefined' && el != null) {
                                tableSangueRec.removeChild(el);
                         }
                            addPrintBagsRec(tableSangueRec);
                    }
                    else if (this.readyState == 4 && this.status != 200) {
                      alert(this.response);
                    }

                };

                xhttp.open("GET", url, true);
                xhttp.setRequestHeader('Authorization', 'Basic '+token);
                xhttp.withCredentials = true;
                xhttp.send();
            }
            else {
                alert("Compila tutti i campi.");
                refresh();
            }

        }

        function refresh() {
            document.getElementById('dateIn').value = "";
            document.getElementById('dateOut').value = "";
        }


        function addPrintBagsRec(tableSangueRec) {
            var row;
            var tbody = document.createElement('tbody');
            tbody.id = 'myTableRec';
                for(let i = 0; i < objBgReceive.length; i++) {

                    row = document.createElement('tr');
                    row.appendChild(document.createElement('td'));
                    row.appendChild(document.createElement('td'));
                    row.appendChild(document.createElement('td'));
                    row.appendChild(document.createElement('td'));
                    row.appendChild(document.createElement('td'));
                    row.appendChild(document.createElement('td'));
                    row.appendChild(document.createElement('td'));
                    row.cells[0].innerHTML = objBgReceive[i].seriale.seriale;
                    row.cells[1].innerHTML = objBgReceive[i].gruppoSanguigno;
                    row.cells[2].innerHTML = objBgReceive[i].dataArrivo;
                    row.cells[3].innerHTML = objBgReceive[i].dataAffidamento;
                    row.cells[4].innerHTML = objBgReceive[i].enteDonatore;
                    row.cells[5].innerHTML = objBgReceive[i].enteRichiedente;
                    row.cells[6].innerHTML = objBgReceive[i].indirizzoEnte;
                    tbody.appendChild(row);
              }
              tableSangueRec.appendChild(tbody);

        }

        /*###############################
        *       QUERY PERMANENZA SACCHE
        *################################*/
        function reportMediumStayBags(){

            let ctxBarPerm = document.getElementById("bar-chart-perm");
            ctxBarPerm.remove();
            document.getElementById("chartBagsPerm").innerHTML = '<canvas id="bar-chart-perm" width="800" height="450"></canvas>';


            let obj;
            let xhttp = new XMLHttpRequest();
            let url = host + "/rest/CCS/giacenzaMediaSaccheCCS";

            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    obj = JSON.parse(this.responseText);
                    setCanvasSacchePerm(obj);
                    }
                else if (this.readyState == 4 && this.status != 200) {
                  alert(this.response);
                }

            };

            xhttp.open("GET", url, true);
            xhttp.setRequestHeader('Authorization', 'Basic '+token);
            xhttp.withCredentials = true;
            xhttp.send();

            setCanvasSacchePerm = function(obj) {
                ctxBarPerm = document.getElementById("bar-chart-perm").getContext("2d");
                var myChartBarPerm = new Chart(ctxBarPerm, {
                      type: 'bar',
                      data: {
                          labels: [
                                  'PERMANENZA SACCHE NEL MAGAZZINO',
                                ],
                                datasets: [{
                                    label: ['A+'],
                                    data: [obj.Ap],
                                    backgroundColor: [
                                      'rgb(255, 200, 50)'
                                    ],
                                    hoverOffset: 4
                                }, {
                                    label: ['A-'],
                                    data: [obj.Am],
                                    backgroundColor: [
                                      'rgb(255, 215, 0)'
                                    ],
                                }, {
                                    label: ['B+'],
                                    data: [obj.Bp],
                                    backgroundColor: [
                                      'rgb(0, 150, 50)'
                                    ],
                                }, {
                                    label: ['B-'],
                                    data: [obj.Bm],
                                    backgroundColor: [
                                      'rgb(0, 230, 50)'
                                    ],
                                }, {
                                    label: ['AB+'],
                                    data: [obj.ABp],
                                    backgroundColor: [
                                      'rgb(230, 0, 0)'
                                    ],
                                }, {
                                    label: ['AB-'],
                                    data: [obj.ABm],
                                    backgroundColor: [
                                      'rgb(230, 40, 0)'
                                    ],
                                }, {
                                    label: ['0+'],
                                    data: [obj.ZEROp],
                                    backgroundColor: [
                                      'rgb(0, 100, 255)'
                                    ],
                                }, {
                                    label: ['0-'],
                                    data: [obj.ZEROm],
                                    backgroundColor: [
                                      'rgb(0, 150, 255)'
                                    ],
                                }]
                      },

                      options: {
                          responsive: true,
                          maintainAspectRatio: false,
                          scales: {
                              yAxes: [{
                                 scaleLabel: {
                                     display:     true,
                                     labelString: 'value'
                                 }
                             }]
                          }
                      }

                  });

                myChartBarPerm.update();
            }

        }

