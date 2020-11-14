package com.gamestore.gamestore.domain.dtos;

import com.gamestore.gamestore.domain.entities.Game;
import com.gamestore.gamestore.domain.entities.Order;
import com.gamestore.gamestore.domain.entities.Role;
import com.gamestore.gamestore.domain.services.CartService;

import java.util.Set;

public class UserDto {

    private String email;
    private String fullName;
    private String password;
    private Role role;

    private Set<Game> ownedGames;

    public UserDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Game> getOwnedGames() {
        return ownedGames;
    }

    public void setOwnedGames(Set<Game> ownedGames) {
        this.ownedGames = ownedGames;
    }
}
