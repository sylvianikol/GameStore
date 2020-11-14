package com.gamestore.gamestore.domain.services.impls;

import com.gamestore.gamestore.domain.dtos.*;
import com.gamestore.gamestore.domain.entities.Game;
import com.gamestore.gamestore.domain.repository.GameRepository;
import com.gamestore.gamestore.domain.services.GameService;
import com.gamestore.gamestore.domain.services.UserService;
import com.gamestore.gamestore.domain.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.gamestore.gamestore.constants.ErrorMessages.*;
import static com.gamestore.gamestore.constants.GlobalConstants.*;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository,
                           UserService userService,
                           ModelMapper modelMapper,
                           ValidationUtil validationUtil) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public String addGame(GameAddDto gameAddDto) {

        if (!this.userService.isLoggedUserAdmin()) {
            return NOT_ADMIN;
        }

        if (!this.validationUtil.isValid(gameAddDto)) {
            return this.validationUtil.getViolationsMessages(gameAddDto);
        }

        String gameTitle = gameAddDto.getTitle();
        Game game = this.gameRepository.findByTitle(gameTitle).orElse(null);

        if (game != null) {
            return String.format(GAME_ALREADY_EXISTS, gameTitle);
        }

        game = this.modelMapper.map(gameAddDto, Game.class);
        this.gameRepository.saveAndFlush(game);

        return String.format(GAME_ADDED, gameTitle);
    }

    @Override
    public String editGame(GameEditDto gameEditDto) {

        if (!this.userService.isLoggedUserAdmin()) {
            return NOT_ADMIN;
        }

        if (!this.validationUtil.isValid(gameEditDto)) {
            return this.validationUtil.getViolationsMessages(gameEditDto);
        }

        Game game = this.gameRepository.findById(gameEditDto.getId());

         if (game == null) {
            return String.format(GAME_ID_NOT_EXISTS, gameEditDto.getId());
        }

        this.gameRepository.updatePrice(gameEditDto.getId(), gameEditDto.getPrice());
        this.gameRepository.updateSize(gameEditDto.getId(), gameEditDto.getSize());

        return String.format(GAME_EDITED, game.getTitle());
    }

    @Override
    @Transactional
    public String deleteGame(GameDeleteDto gameDeleteDto) {

        if (!this.userService.isLoggedUserAdmin()) {
            return NOT_ADMIN;
        }

        Game game = this.gameRepository.findById(gameDeleteDto.getId());

        if (game == null) {
            return String.format(GAME_ID_NOT_EXISTS, gameDeleteDto.getId());
        }

        String name = game.getTitle();
        this.gameRepository.deleteGameById(gameDeleteDto.getId());

        return String.format(GAME_DELETED, name);
    }

    @Override
    public List<AllGamesViewDto> getAllGames() {

        return this.gameRepository.findAll().stream()
                .map(game -> this.modelMapper.map(game, AllGamesViewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String getGameDetails(String title) {

        Game game = this.gameRepository.findByTitle(title).orElse(null);

        if (game == null) {
            return String.format(GAME_TITLE_NOT_EXISTS, title);
        }

        return String.format(GAME_DETAILS,
                game.getTitle(),
                game.getPrice(),
                game.getDescription(),
                DateTimeFormatter.ofPattern(DATE_PATTERN)
                        .format(game.getReleaseDate()));
    }

    @Override
    public BuyItemDto getGameToAdd(String gameTitle) {

        Game game = this.gameRepository.findByTitle(gameTitle).orElse(null);

        if (game == null) {
            throw new IllegalArgumentException(String.format(GAME_TITLE_NOT_EXISTS, gameTitle));
        }

        return this.modelMapper.map(game, BuyItemDto.class);
    }

    @Override
    public Game getGame(Long id) {

        return this.gameRepository.findById(id).orElse(null);
    }
}
