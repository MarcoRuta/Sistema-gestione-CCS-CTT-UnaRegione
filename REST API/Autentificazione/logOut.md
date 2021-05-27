# logOut

  Metodo che permette ad un user generico di uscire dalla piattaforma CTT/CCS

* **URL**

        /rest/autentificazione/logout

* **Method:**
  
        DELETE
  
*  **URL Params**

        @HeaderParam(HttpHeaders.AUTHORIZATION) String header
   

* **Success Response:**
  

        Code: 200 OK
        Content: Token rimosso correttamente
 
* **Error Response:**

        Code: 400 BAD_REQUEST
        Content: Impossibile rimuovere il Token,  + Eccezione


* **Sample Call:**

        -H "Authorization: Basic QUI CI VA IL TOKEN DA RIMUOVERE" -X DELETE 127.0.0.1:8080/rest/autentificazione/logout