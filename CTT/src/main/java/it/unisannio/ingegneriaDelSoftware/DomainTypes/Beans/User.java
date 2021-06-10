package it.unisannio.ingegneriaDelSoftware.DomainTypes.Beans;

import java.util.Objects;

/**A user bean class*/
public class User {
	
	/**Token di accesso relativo all'user */
    private String token;
    /**Ruolo dell'user */
    private String ruolo;
    /**Nome dell'user */
    private String nome;
    /**Cognome dell'user */
    private String cognome;
    /**Codice fiscale dell'user */
    private String cdf;

    /**Metodo costruttore di User 
     * @param token
     * @param ruolo
     * @param nome
     * @param cognome
     * @param cdf
     */
    public User(String token, String ruolo, String nome, String cognome, String cdf){
        this.ruolo = ruolo;
        this.token = token;
        this.nome = nome;
        this.cognome = cognome;
        this.cdf = cdf;
    }
    
    
    /**Metodo costruttore di User
     */
    public User(){};

    
    /**Restituisce il Codice fiscale dell'User
	 * @return cdf
     */
    public String getCdf() {
        return cdf;
    }

    
    /**Modifica il Codice fiscale dell'User
     * @param cdf
     */
    public void setCdf(String cdf) {
        this.cdf = cdf;
    }

    
    /**Modifica il Token dell'User
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    
    /**Modifica il Ruolo dell'User
     * @param ruolo
     */
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    
    /**Restituisce il Token dell'User 
     * @return token
     */
    public String getToken() {
        return token;
    }

    
    /**Restituisce il Ruolo dell'User
     * @return Ruolo
     */
    public String getRuolo() {
        return ruolo;
    }

    
    /**Restituisce il Cognome dell'User
     * @return cognome
     */
    public String getCognome() {
        return cognome;
    }

    
    /**Restituisce il nome dell'User
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**Modifica il Cognome dell'User
     * @param cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**Modifica il Nome dell'User
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
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