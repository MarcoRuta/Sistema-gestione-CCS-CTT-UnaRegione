package it.unisannio.ingegneriaDelSoftware.Classes;

import java.util.Objects;

public class User {
    private String token;
    private String ruolo;
    private String nome;
    private String cognome;

    public User(String token, String ruolo, String nome, String cognome){
        this.ruolo = ruolo;
        this.token = token;
        this.nome = nome;
        this.cognome = cognome;
    }
    public User(){};

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
        return token.equals(user.token) && ruolo.equals(user.ruolo) && nome.equals(user.nome) && cognome.equals(user.cognome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, ruolo, nome, cognome);
    }

    @Override
    public String toString() {
        return "User{" +
                "token='" + token + '\'' +
                ", ruolo='" + ruolo + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                '}';
    }
}
