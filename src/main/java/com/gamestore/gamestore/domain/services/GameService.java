package com.gamestore.gamestore.domain.services;

import com.gamestore.gamestore.domain.dtos.*;
import com.gamestore.gamestore.domain.entities.Game;

import java.util.List;

public interface GameService {

    String addGame(GameAddDto gameAddDto);

    String editGame(GameEditDto gameEditDto);

    String deleteGame(GameDeleteDto gameDeleteDto);

    List<AllGamesViewDto> getAllGames();

    String getGameDetails(String title);

    BuyItemDto getGameToAdd(String gameTitle);

    Game getGame(Long id);
}
