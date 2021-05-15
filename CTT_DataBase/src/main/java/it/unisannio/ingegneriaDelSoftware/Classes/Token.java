package it.unisannio.ingegneriaDelSoftware.Classes;

import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;

import java.security.SecureRandom;
import java.util.*;

/** Token implenta Flyweight pattern, esso rappresenta un token di autentificazione creato
 * in seguito ad una corretta autentificazione*/
public class Token {

    /**collezione statica di tutti i token che sono generati dal momento dell'accensione del server
     * fino al suo spegnimento*/
    private static Map<String,Token> tokens;
    static{
        tokens= new HashMap<String, Token>();
    }
    private String token;

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    /**@param usernamePassword  username + password dell'utente loggato senza alcuno spazione
     * @return  token  */
    public static Token getToken(String usernamePassword){
        assert usernamePassword != null: "Username e password non possono essere null";
        if (Token.tokens.containsKey(usernamePassword))
            return Token.tokens.get(usernamePassword);
        Token aToken = new Token();
        Token.tokens.put(usernamePassword,aToken);
        return aToken;

    }

    /**@param token il token di autentificazione di un utente
     * @return  token se presente altrimenti solleva una eccezzone
     */
    public static boolean containsToken(String token){
        assert token != null: "il token non puo essere null";
        Collection<Token> tokens = Token.tokens.values();
        for (Token aToken:tokens)
            if(aToken.getValue().equals(token))
                return true;
        return false;
    }

    /**@param token il token di autentificazione di un utente
     * @return  username:password dell'utente a cui è associato il token
     */
    public static String getDipendenteByToken(String token) throws DipendenteNotFoundException {
        assert token != null: "il token non puo essere null";
        for (String usernamePassword : Token.tokens.keySet())
            if (Token.tokens.get(usernamePassword).getValue().equals(token))
                return usernamePassword;
        throw new DipendenteNotFoundException("Il toke non è associato a nessun dipendente");

    }

    /**@return il token come stringa */
    public String getValue() {
        return this.token;
    }

    private Token(){
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        this.token =  base64Encoder.encodeToString(randomBytes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return token.equals(token1.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                '}';
    }
}
