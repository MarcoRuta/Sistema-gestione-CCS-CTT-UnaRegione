package it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans;

import java.util.Objects;

/**A user bean class*/
public class User {
    private String token;
    private String ruolo;
    private String nome;
    private String cognome;
    private String cdf;

    /**Metodo costruttore di User 
     * @param token Oggetto che il server restituito al client dopo che un generico user si è correttamente loggato, valido per tutta la sessione
     * @param ruolo Ruolo recuperato dalla fase di login
     * @param nome Nome recuperato dalla fase di login
     * @param cognome Cognome recuperato dalla fase di login
     * @param cdf CDF recuperato dalla fase di login
     */
    public User(String token, String ruolo, String nome, String cognome, String cdf){
        this.ruolo = ruolo;
        this.token = token;
        this.nome = nome;
        this.cognome = cognome;
        this.cdf = cdf;
    }

    /**Metodo costruttore di User senza argomenti
     */
    public User(){};

    /**Restituisce il token dell'User
     * @return token Token dell'User
     */
    public String getToken() {
        return token;
    }

    /**Modifica il token dell'User
     * @param token Token dell'User
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**Restituisce il ruolo dell'User
     * @return Ruolo
     */
    public String getRuolo() {
        return ruolo;
    }

    /**Modifica il ruolo dell'User
     * @param ruolo Ruolo dell'user
     */
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    /**Restituisce il nome dell'User
     * @return nome Nome dell'user
     */
    public String getNome() {
        return nome;
    }

    /**Modifica il Nome dell'User
     * @param nome Nome dell'user
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**Restituisce il cognome dell'User
     * @return cognome Cognome dell'User
     */
    public String getCognome() {
        return cognome;
    }

    /**Modifica il Cognome dell'User
     * @param cognome Cognome dell'User
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**Restituisce il Codice fiscale dell'User
     * @return cdf Codice Fiscale dello user
     */
    public String getCdf() {
        return cdf;
    }

    /**Modifica il Codice fiscale dell'User
     * @param cdf Codice Fiscale dello user
     */
    public void setCdf(String cdf) {
        this.cdf = cdf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return cdf.equals(user.cdf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cdf);
    }

    @Override
    public String toString() {
        return "User{" +
                "token='" + token + '\'' +
                ", ruolo='" + ruolo + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", cdf='" + cdf + '\'' +
                '}';
    }
}