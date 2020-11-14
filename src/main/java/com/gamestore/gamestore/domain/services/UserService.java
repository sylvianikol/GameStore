package com.gamestore.gamestore.domain.services;

import com.gamestore.gamestore.domain.dtos.*;
import com.gamestore.gamestore.domain.entities.Game;
import com.gamestore.gamestore.domain.entities.User;

import java.util.Set;

public interface UserService {

    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto userLoginDto);

    String logOutUser();

    boolean isLoggedUserAdmin();

    boolean isLogged();

    UserDto getUserDto();

    Set<String> getOwnedGames();

    boolean isOwned(String gameTitle);

    User getUser();

    Game addToOwnedGames(Long id);
}
