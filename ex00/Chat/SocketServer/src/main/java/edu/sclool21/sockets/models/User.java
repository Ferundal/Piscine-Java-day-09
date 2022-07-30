package edu.sclool21.sockets.models;

public class User {
    private Long identifier = null;
    private String login;
    private String password;

    public User (Long identifier, String login, String password) {
        this.identifier = identifier;
        this.login = login;
        this.password = password;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", email='" + login + '\'' +
                '}';
    }
}
