package com.perloven.server.model;


import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @Column(unique = true)
    private String username;
    private String password;
    @Transient
    public boolean loggedIn = false;

    public User() {

    }

    public User(String username, String password, Long userId) {
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long userId) {
        this.id = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn'" + loggedIn + '\'' +
                '}';
    }
}


