package it.unisannio.ingegneriaDelSoftware.Classes;

import it.unisannio.ingegneriaDelSoftware.Exceptions.DipendenteNotFoundException;
import it.unisannio.ingegneriaDelSoftware.Exceptions.TokenNotFoundException;

import java.security.SecureRandom;
import java.util.*;

/** Token implenta Flyweight pattern, esso rappresenta un token di autentificazione creato
 * in seguito ad una corretta autentificazione*/
public class Token {

    /**Collezione statica di tutti i token che sono generati dal momento dell'accensione del server
     * fino al suo spegnimento*/
    private static Map<String,Token> tokens= new HashMap<String, Token>();

    private final String token;

    /**
     *java.security.SecureRandom is a class that provides a cryptographically strong random number generator.
     * Dà una sequenza di interi che è meno prevedibile rispetto alla classe java.util.Random*/
    private static final SecureRandom secureRandom = new SecureRandom();
    /**Una classe java che si occupa di effettuare un Encoding in Base64*/
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    /**@param usernamePassword  username + password dell'utente loggato senza alcuno spazio
     * @return  token  */
    public static Token getToken(String usernamePassword){
        assert usernamePassword != null: "Username e password non possono essere null";
        if (Token.tokens.containsKey(usernamePassword))
            return Token.tokens.get(usernamePassword);
        Token aToken = new Token();
        Token.tokens.put(usernamePassword,aToken);
        return aToken;

    }

    /**Controlla se il token è presente
     * @param token il token di autentificazione di un utente
     * @return token se presente, altrimenti solleva una eccezione
     */
    public static boolean containsToken(String token){
        assert token != null: "il token non puo essere null";
        Collection<Token> tokens = Token.tokens.values();
        for (Token aToken: tokens)
            if (aToken.getValue().equals(token))
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
        throw new DipendenteNotFoundException("Il token non è associato a nessun dipendente");

    }

    /**@return il token come stringa */
    public String getValue() {
        return this.token;
    }

    /**costruttore privato per il FlyWeight pattern*/
    private Token(){
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        this.token =  base64Encoder.encodeToString(randomBytes);
    }

    /**Elimina dalla lista dei token il token passatogli come parametro
     * @param token il token da eliminare
     */
    public static void removeToken(String token) throws TokenNotFoundException {
        if (Token.containsToken(token)) {
            for (String key : Token.tokens.keySet())
                if (tokens.get(key).getValue().equals(token)) {
                    Token.tokens.remove(key);
                    return;
                }
        } else  throw new TokenNotFoundException("Il token non esiste");
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
