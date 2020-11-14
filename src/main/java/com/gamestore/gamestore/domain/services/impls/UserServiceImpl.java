package com.gamestore.gamestore.domain.services.impls;

import com.gamestore.gamestore.domain.dtos.OwnedGameDto;
import com.gamestore.gamestore.domain.dtos.UserDto;
import com.gamestore.gamestore.domain.dtos.UserLoginDto;
import com.gamestore.gamestore.domain.dtos.UserRegisterDto;
import com.gamestore.gamestore.domain.entities.Game;
import com.gamestore.gamestore.domain.entities.Role;
import com.gamestore.gamestore.domain.entities.User;
import com.gamestore.gamestore.domain.repository.UserRepository;
import com.gamestore.gamestore.domain.services.GameService;
import com.gamestore.gamestore.domain.services.UserService;
import com.gamestore.gamestore.domain.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gamestore.gamestore.constants.ErrorMessages.*;
import static com.gamestore.gamestore.constants.GlobalConstants.DELIMITER;
import static com.gamestore.gamestore.constants.GlobalConstants.USER_REGISTERED;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GameService gameService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private UserDto userDto;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           @Lazy GameService gameService,
                           ModelMapper modelMapper,
                           ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.gameService = gameService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {

        if (!this.validationUtil.isValid(userRegisterDto)) {
            return this.validationUtil.getViolationsMessages(userRegisterDto);
        }

        User user = this.userRepository.findByEmailAndPassword(
                userRegisterDto.getEmail(), userRegisterDto.getPassword());

        if (user != null)  {
            return String.format(DELIMITER + EMAIL_ALREADY_USED,
                    userRegisterDto.getEmail());
        }

        user = this.modelMapper.map(userRegisterDto, User.class);
        user.setRole(this.userRepository.count() == 0 ? Role.ADMIN : Role.USER);
        this.userRepository.saveAndFlush(user);

        return String.format(USER_REGISTERED, userRegisterDto.getFullName());
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {

        User user = this.userRepository
                .findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());

        if (user == null) {
            return INCORRECT_USER_PASSWORD;
        }

        if (this.userDto != null) {
            if (this.userDto.getFullName().equals(user.getFullName())) {
                return String.format(ALREADY_LOGGED_IN, this.userDto.getFullName());
            } else {
                return ANOTHER_USER_LOGGED_IN;
            }
        }

        this.userDto = this.modelMapper.map(user, UserDto.class);

        return String.format(LOGGED_IN, userDto.getFullName());
    }

    @Override
    public String logOutUser() {

        if (userDto == null) {
            return CAN_NOT_LOG_OUT;
        }

        String name = userDto.getFullName();
        this.userDto = null;

        return String.format(LOGGED_OUT, name);

    }

    @Override
    public boolean isLoggedUserAdmin() {
        if (userDto == null) {
            throw new NullPointerException("You need to be logged in as an Admin User in order to Manage games!");
        }
        return this.userDto.getRole().equals(Role.ADMIN);
    }

    @Override
    public boolean isLogged() {
        if (userDto == null) {
            throw new NullPointerException("You are not logged in!");
        }
        return true;
    }

    @Override
    public UserDto getUserDto() {
        return userDto;
    }

    @Override
    public Set<String> getOwnedGames() {

        return this.userDto.getOwnedGames().stream()
                .map(Game::getTitle)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isOwned(String gameTitle) {

        for (Game ownedGame : this.getUserDto().getOwnedGames()) {
            if (ownedGame.getTitle().equals(gameTitle)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public User getUser() {
        return this.userRepository
                .findByEmailAndPassword(this.userDto.getEmail(), this.userDto.getPassword());
    }

    @Override
    public Game addToOwnedGames(Long id) {
        Game game = this.gameService.getGame(id);

        if (game != null) {
            User user = this.getUser();
            user.getOwnedGames().add(game);

            this.userRepository.saveAndFlush(user);

            this.userDto = this.modelMapper.map(user, UserDto.class);
        }

        return game;
    }

}
