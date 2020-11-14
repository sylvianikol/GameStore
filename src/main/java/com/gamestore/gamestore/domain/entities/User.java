package com.gamestore.gamestore.domain.entities;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String email;
    private String fullName;
    private String password;
    private Role role;

    private Set<Game> ownedGames;

    public User() {
    }

    @Column(unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToMany(fetch = EAGER, cascade = ALL)
    @JoinTable(name = "users_games",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
    public Set<Game> getOwnedGames() {
        return ownedGames;
    }

    public void setOwnedGames(Set<Game> games) {
        this.ownedGames = games;
    }

}
