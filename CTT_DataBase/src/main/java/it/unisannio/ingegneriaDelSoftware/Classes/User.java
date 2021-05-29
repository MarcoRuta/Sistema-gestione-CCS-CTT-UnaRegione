package it.unisannio.ingegneriaDelSoftware.Classes;

import java.util.Objects;

/**A user bean class*/
public class User {
    private String token;
    private String ruolo;
    private String nome;
    private String cognome;
    private String cdf;

    public User(String token, String ruolo, String nome, String cognome, String cdf){
        this.ruolo = ruolo;
        this.token = token;
        this.nome = nome;
        this.cognome = cognome;
        this.cdf = cdf;
    }
    public User(){};

    public String getCdf() {
        return cdf;
    }

    public void setCdf(String cdf) {
        this.cdf = cdf;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getToken() {
        return token;
    }

    public String getRuolo() {
        return ruolo;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

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
