/*----------------- QUERY SACCHE INVIATE -------------------*/
       /*-------- FILTRO SERIALI TABLE ------*/
        function filterSerial() {
          var input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("serialFilter");
          filter = input.value.toUpperCase();
          table = document.getElementById("myTable");
          tr = table.getElementsByTagName("tr");
          for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
              txtValue = td.textContent || td.innerText;
              if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
              } else {
                tr[i].style.display = "none";
              }
            }
          }
        }

        /*-------- FILTRO GRUPPO SANGUIGNO ------*/
        function filterGroup() {
          var input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("serialGroup");
          filter = input.value.toUpperCase();
          table = document.getElementById("myTable");
          tr = table.getElementsByTagName("tr");
          for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[1];
            if (td) {
              txtValue = td.textContent || td.innerText;
              if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
              } else {
                tr[i].style.display = "none";
              }
            }
          }
        }

        /*-------- FILTRO DATA ARRIVO ------*/
        function filterArrive() {
          var input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("arriveDate");
          filter = input.value.toUpperCase();
          table = document.getElementById("myTable");
          tr = table.getElementsByTagName("tr");
          for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[2];
            if (td) {
              txtValue = td.textContent || td.innerText;
              if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
              } else {
                tr[i].style.display = "none";
              }
            }
          }
        }

        /*-------- FILTRO DATA AFFIDAMENTO ------*/
        function filterDeliver() {
          var input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("AffDate");
          filter = input.value.toUpperCase();
          table = document.getElementById("myTable");
          tr = table.getElementsByTagName("tr");
          for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[3];
            if (td) {
              txtValue = td.textContent || td.innerText;
              if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
              } else {
                tr[i].style.display = "none";
              }
            }
          }
        }

        /*-------- FILTRO ENTE DONATORE ------*/
        function filterDonator() {
          var input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("enteDonatore");
          filter = input.value.toUpperCase();
          table = document.getElementById("myTable");
          tr = table.getElementsByTagName("tr");
          for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[4];
            if (td) {
              txtValue = td.textContent || td.innerText;
              if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
              } else {
                tr[i].style.display = "none";
              }
            }
          }
        }

        /*-------- FILTRO ENTE RICHIEDENTE ------*/
        function filterRequest() {
          var input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("enteRichiedente");
          filter = input.value.toUpperCase();
          table = document.getElementById("myTable");
          tr = table.getElementsByTagName("tr");
          for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[5];
            if (td) {
              txtValue = td.textContent || td.innerText;
              if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
              } else {
                tr[i].style.display = "none";
              }
            }
          }
        }

        /*-------- FILTRO INDIRIZZO ENTE ------*/
        function filterStreet() {
          var input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("indirizzoEnte");
          filter = input.value.toUpperCase();
          table = document.getElementById("myTable");
          tr = table.getElementsByTagName("tr");
          for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[6];
            if (td) {
              txtValue = td.textContent || td.innerText;
              if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
              } else {
                tr[i].style.display = "none";
              }
            }
          }
        }

/*------------------- QUERY SACCHE RICEVUTE ----------------*/
        /*-------- FILTRO SERIALI TABLE ------*/
        function filterSerialRec() {
          var input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("serialFilterRec");
          filter = input.value.toUpperCase();
          table = document.getElementById("myTableRec");
          tr = table.getElementsByTagName("tr");
          for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
              txtValue = td.textContent || td.innerText;
              if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
              } else {
                tr[i].style.display = "none";
              }
            }
          }
        }

        /*-------- FILTRO GRUPPO SANGUIGNO ------*/
        function filterGroupRec() {
          var input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("serialGroupRec");
          filter = input.value.toUpperCase();
          table = document.getElementById("myTableRec");
          tr = table.getElementsByTagName("tr");
          for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[1];
            if (td) {
              txtValue = td.textContent || td.innerText;
              if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
              } else {
                tr[i].style.display = "none";
              }
            }
          }
        }

        /*-------- FILTRO DATA ARRIVO ------*/
        function filterArriveRec() {
          var input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("arriveDateRec");
          filter = input.value.toUpperCase();
          table = document.getElementById("myTableRec");
          tr = table.getElementsByTagName("tr");
          for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[2];
            if (td) {
              txtValue = td.textContent || td.innerText;
              if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
              } else {
                tr[i].style.display = "none";
              }
            }
          }
        }

        /*-------- FILTRO DATA AFFIDAMENTO ------*/
        function filterDeliverRec() {
          var input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("AffDateRec");
          filter = input.value.toUpperCase();
          table = document.getElementById("myTableRec");
          tr = table.getElementsByTagName("tr");
          for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[3];
            if (td) {
              txtValue = td.textContent || td.innerText;
              if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
              } else {
                tr[i].style.display = "none";
              }
            }
          }
        }

        /*-------- FILTRO ENTE DONATORE ------*/
        function filterDonatorRec() {
          var input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("enteDonatoreRec");
          filter = input.value.toUpperCase();
          table = document.getElementById("myTableRec");
          tr = table.getElementsByTagName("tr");
          for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[4];
            if (td) {
              txtValue = td.textContent || td.innerText;
              if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
              } else {
                tr[i].style.display = "none";
              }
            }
          }
        }

        /*-------- FILTRO ENTE RICHIEDENTE ------*/
        function filterRequestRec() {
          var input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("enteRichiedenteRec");
          filter = input.value.toUpperCase();
          table = document.getElementById("myTableRec");
          tr = table.getElementsByTagName("tr");
          for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[5];
            if (td) {
              txtValue = td.textContent || td.innerText;
              if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
              } else {
                tr[i].style.display = "none";
              }
            }
          }
        }

        /*-------- FILTRO INDIRIZZO ENTE ------*/
        function filterStreetRec() {
          var input, filter, table, tr, td, i, txtValue;
          input = document.getElementById("indirizzoEnteRec");
          filter = input.value.toUpperCase();
          table = document.getElementById("myTableRec");
          tr = table.getElementsByTagName("tr");
          for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[6];
            if (td) {
              txtValue = td.textContent || td.innerText;
              if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
              } else {
                tr[i].style.display = "none";
              }
            }
          }
        }
