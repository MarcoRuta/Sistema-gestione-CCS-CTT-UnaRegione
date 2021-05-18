package it.unisannio.ingegneriaDelSoftware.Classes;

import java.util.Objects;

/**A user bean class*/
public class User {
    private String token;
    private String username;
    private String ruolo;

    public User(String token, String username, String ruolo){
        this.ruolo = ruolo;
        this.username = username;
        this.token = token;
    }
    public User(){};

    @Override
    public String toString() {
        return "User{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", ruolo='" + ruolo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return token.equals(user.token) && username.equals(user.username) && ruolo.equals(user.ruolo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, username, ruolo);
    }

    public String getRuolo() {
        return ruolo;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
