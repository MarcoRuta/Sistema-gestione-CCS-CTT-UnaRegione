        function controllaCF()
        {
          // Definisco un pattern per il confronto
          var pattern = /^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$/;
        
          // creo una variabile per richiamare con facilità il nostro campo di input
          var CodiceFiscale = document.getElementById("codice");
        
          // utilizzo il metodo search per verificare che il valore inserito nel campo
          // di input rispetti la stringa di verifica (pattern)
          if (CodiceFiscale.value.search(pattern) == -1)
          {
            // In caso di errore stampo un avviso e pulisco il campo...
            CodiceFiscale.value = "";
            print1();
            CodiceFiscale.focus();
          }else{
             // ...in caso contrario stampo un avviso di successo!
          }
        }

        function controllaNome()
        {
          // Definisco un pattern per il confronto
          var pattern = /^[a-zA-Z\s]{1,24}$/;
        
          // creo una variabile per richiamare con facilità il nostro campo di input
          var name = document.getElementById("nome");
        
          // utilizzo il metodo search per verificare che il valore inserito nel campo
          // di input rispetti la stringa di verifica (pattern)
          if (name.value.search(pattern) == -1)
          {
            // In caso di errore stampo un avviso e pulisco il campo...
            name.value = "";
            print2();
            name.focus();
          }else{
             // ...in caso contrario stampo un avviso di successo!
             
          }
        }
        
        function controllaCognome()
        {
          // Definisco un pattern per il confronto
          var pattern = /^[a-zA-Z\s]{1,24}$/;
        
          // creo una variabile per richiamare con facilità il nostro campo di input
          var surname = document.getElementById("cognome");
        
          // utilizzo il metodo search per verificare che il valore inserito nel campo
          // di input rispetti la stringa di verifica (pattern)
          if (surname.value.search(pattern) == -1)
          {
            // In caso di errore stampo un avviso e pulisco il campo...
            surname.value = "";
            print3();
            surname.focus();
          }else{
             // ...in caso contrario stampo un avviso di successo!
             
          }
        }
        
        function controllaPassword()
        {
          // Definisco un pattern per il confronto
          var pattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
        
          // creo una variabile per richiamare con facilità il nostro campo di input
          var pass = document.getElementById("password");
        
          // utilizzo il metodo search per verificare che il valore inserito nel campo
          // di input rispetti la stringa di verifica (pattern)
          if (pass.value.search(pattern) == -1)
          {
            // In caso di errore stampo un avviso e pulisco il campo...
            pass.value = "";
            print6();
            pass.focus();
          }else{
             // ...in caso contrario stampo un avviso di successo!
             
          }
        }

        
        function controllaEmail()
        {
          // Definisco un pattern per il confronto
          var pattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;
        
          // creo una variabile per richiamare con facilità il nostro campo di input
          var email = document.getElementById("mail");
        
          // utilizzo il metodo search per verificare che il valore inserito nel campo
          // di input rispetti la stringa di verifica (pattern)
          if (email.value.search(pattern) == -1)
          {
            // In caso di errore stampo un avviso e pulisco il campo...
            email.value = "";
            print7();
            email.focus();
          }else{
             // ...in caso contrario stampo un avviso di successo!
             
          }
        }
        
        function controllaTelefono()
        {
          // Definisco un pattern per il confronto
          var pattern = /^[0-9]{10}$/;
        
          // creo una variabile per richiamare con facilità il nostro campo di input
          var tel = document.getElementById("telefono");
        
          // utilizzo il metodo search per verificare che il valore inserito nel campo
          // di input rispetti la stringa di verifica (pattern)
          if (tel.value.search(pattern) == -1)
          {
            // In caso di errore stampo un avviso e pulisco il campo...
            tel.value = "";
            print6();
            tel.focus();
          }else{
             // ...in caso contrario stampo un avviso di successo!
             
          }
        }
        
        function controllaLatitude()
        {
          // Definisco un pattern per il confronto
          var pattern = /^[-+]?([1-8]?\d(\.\d+)?|90(\.0+)?)$/;
        
          // creo una variabile per richiamare con facilità il nostro campo di input
          var lat = document.getElementById("latitude");
        
          // utilizzo il metodo search per verificare che il valore inserito nel campo
          // di input rispetti la stringa di verifica (pattern)
          if (lat.value.search(pattern) == -1)
          {
            // In caso di errore stampo un avviso e pulisco il campo...
            lat.value = "";
            print8();
            lat.focus();
          }else{
             // ...in caso contrario stampo un avviso di successo!
             
          }
        }
        
        function controllaLongitude()
        {
          // Definisco un pattern per il confronto
          var pattern = /^[-+]?(180(\.0+)?|((1[0-7]\d)|([1-9]?\d))(\.\d+)?)$/;
        
          // creo una variabile per richiamare con facilità il nostro campo di input
          var lon = document.getElementById("longitude");
        
          // utilizzo il metodo search per verificare che il valore inserito nel campo
          // di input rispetti la stringa di verifica (pattern)
          if (lon.value.search(pattern) == -1)
          {
            // In caso di errore stampo un avviso e pulisco il campo...
            lon.value = "";
            print9();
            lon.focus();
          }else{
             // ...in caso contrario stampo un avviso di successo!
             
          }
        }
        
        function controllaNumSacca()
        {
          // Definisco un pattern per il confronto
          var pattern = /^[a-zA-Z]{3}[0-9]{3}-[0-9]{8}$/;
        
          // creo una variabile per richiamare con facilità il nostro campo di input
          var sacca = document.getElementById("numsacca");
        
          // utilizzo il metodo search per verificare che il valore inserito nel campo
          // di input rispetti la stringa di verifica (pattern)
          if (sacca.value.search(pattern) == -1)
          {
            // In caso di errore stampo un avviso e pulisco il campo...
            sacca.value = "";
            print2();
            document.getElementById("result").innerHTML = "Sacca non esiste";
            sacca.focus();
          }else{
             // ...in caso contrario stampo un avviso di successo!
             document.getElementById("result").innerHTML = "";
             
          }
        }
        
        function controllaEnteRic()
        {
          // Definisco un pattern per il confronto
          var pattern = /^[a-zA-Z\s]{1,24}$/;
        
          // creo una variabile per richiamare con facilità il nostro campo di input
          var ente = document.getElementById("enteRic");
        
          // utilizzo il metodo search per verificare che il valore inserito nel campo
          // di input rispetti la stringa di verifica (pattern)
          if (ente.value.search(pattern) == -1)
          {
            // In caso di errore stampo un avviso e pulisco il campo...
            ente.value = "";
            print1();
            ente.focus();
          }else{
             // ...in caso contrario stampo un avviso di successo!
             
          }
        }