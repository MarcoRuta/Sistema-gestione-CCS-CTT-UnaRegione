# cambioPassword

  Metodo che permette ad un user generico di cambiare la password di accesso alla piattaforma CTT/CCS

* **URL**

        /rest/autentificazione/cambiopassword/DCFSDF34F45E564R

* **Method:**
  
        PUT
  
*  **URL Params**

        @PathParam("cdf")String cdf formato [CCCCCCNNCNNCNNNC] con C: char[A-Z] && N: int[1-9] 

*  **Required:**
 
         String password formato almeno 8 caratteri di cui almeno 1 numerico 
   

* **Success Response:**
  
        Code: 200 OK
        Content: Password cambiata correttamente
 
* **Error Response:**

        Code: 400 BAD_REQUEST
        Content: Dipendente non trovato


* **Sample Call:**
        
       curl -H "Authorization: Basic GqJOAP_RviiTA7CePN2w5Yct9J-dH-QU" -X PUT 127.0.0.1:8080/rest/autentificazione/cambiopassword/DCFSDF34F45E564R