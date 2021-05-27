# login

  Metodo che permette ad un user generico di accedere alla piattaforma CTT/CCS

* **URL**

        /rest/autentificazione

* **Method:**
  
        POST
  
*  **URL Params**

        @FormParam username String username
        @FormParam password String password formato almeno 8 caratteri di cui almeni 1 numerico
   

* **Success Response:**
  

        Code: 200 OK
        Content: L'utente loggato correttamente
 
* **Error Response:**

        Code: 403 FORBIDDEN
        Content: username o password errati!


* **Sample Call:**
        
        curl -X POST -d "username=admin" -d "password=admin" http://127.0.0.1:8080/rest/autentificazione