package it.unisannio.ingegneriaDelSoftware.DomainTypes;

import it.unisannio.ingegneriaDelSoftware.DataManagers.MongoDataManager;
import it.unisannio.ingegneriaDelSoftware.Exceptions.EntityNotFoundException;
import java.security.SecureRandom;
import java.util.*;

/**Flyweight*/
public class Token {

    /**Mappa statica che mantiene tutte le istanze di token*/
    private static Map<String,Token> tokens= new HashMap<String, Token>();

    private final String token;
    
    /**java.security.SecureRandom is a class that provides a cryptographically strong random number generator.
     * D° una sequenza di interi che è meno prevedibile rispetto alla classe java.util.Random*/
    private static final SecureRandom secureRandom = new SecureRandom();

    /**Una classe java che si occupa di effettuare un Encoding in Base64*/
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    /**Restituisce il token partendo da username e password se presente, altrimenti lo crea e lo aggiunge alla mappa statica
     * @param usernamePassword username + password dell'utente loggato senza alcuno spazio
     * @return token  Il token realizzato a partire da username e password dello user
     * */
    public static Token getToken(String usernamePassword){
        assert usernamePassword != null: "Username e password non possono essere null";
        if (Token.tokens.containsKey(usernamePassword))
            return Token.tokens.get(usernamePassword);
        Token aToken = new Token();
        Token.tokens.put(usernamePassword,aToken);
        return aToken;

    }

    /**Controlla se è presente il token nella collezione 
     * @param token il token di autentificazione di un utente
     * @return boolean true se presente; false se non è presente
     */
    public static boolean containsToken(String token){
        assert token != null: "il token non puo essere null";
        Collection<Token> tokens = Token.tokens.values();
        for (Token aToken: tokens)
            if (aToken.getValue().equals(token))
                return true;
        return false;
    }

    /**Restituisce il Dipendente a cui è associato il token
     * @param token il token di autentificazione di un utente
     * @return Dipendente Utente a cui è associato il token
     * @throws EntityNotFoundException se il token ricercato non è associato a nessun Dipendente
     */
    public static Dipendente getDipendenteByToken(String token) throws EntityNotFoundException {
        assert token != null: "il token non puo essere null";
        for (String usernamePassword : Token.tokens.keySet())
            if (Token.tokens.get(usernamePassword).getValue().equals(token)) {
                StringTokenizer tokenizer = new StringTokenizer(usernamePassword);
                String username = tokenizer.nextToken(":");
                String password = tokenizer.nextToken();
                return MongoDataManager.getInstance().getDipendente(username, password);
            }
        throw new EntityNotFoundException("Il token non è associato a nessun Dipendente");

    }
    
    /**Restituisce il token come stringa
     * @return token il token come stringa
     */
    public String getValue() {
        return this.token;
    }

    /**Costruttore privato di Token per il FlyWeight pattern*/
    private Token(){
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        this.token =  base64Encoder.encodeToString(randomBytes);
    }

    /**Elimina dalla lista dei token il token passatogli come parametro
     * @param token il token da eliminare
     * @throws EntityNotFoundException se il token che si vuole rimuovere non è presente
     */
    public static void removeToken(String token) throws EntityNotFoundException {
        if (Token.containsToken(token)) {
            for (String key : Token.tokens.keySet())
                if (tokens.get(key).getValue().equals(token)) {
                    Token.tokens.remove(key);
                    return;
                }
        } else throw new EntityNotFoundException("Il token non esiste");
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