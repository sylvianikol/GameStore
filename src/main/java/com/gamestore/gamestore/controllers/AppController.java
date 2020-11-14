package com.gamestore.gamestore.controllers;

import com.gamestore.gamestore.constants.Command;
import com.gamestore.gamestore.domain.dtos.*;
import com.gamestore.gamestore.domain.services.CartService;
import com.gamestore.gamestore.domain.services.GameService;
import com.gamestore.gamestore.domain.services.OrderService;
import com.gamestore.gamestore.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import static com.gamestore.gamestore.constants.ErrorMessages.*;
import static com.gamestore.gamestore.constants.GlobalConstants.*;

@Component
public class AppController implements CommandLineRunner {

    private final BufferedReader reader;
    private final UserService userService;
    private final GameService gameService;
    private final OrderService orderService;
    private final CartService cartService;

    @Autowired
    public AppController(BufferedReader reader,
                         UserService userService,
                         GameService gameService,
                         OrderService orderService,
                         CartService cartService) {
        this.reader = reader;
        this.userService = userService;
        this.gameService = gameService;
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @Override
    public void run(String... args) throws Exception {

        while (true) {

            System.out.println(ENTER_COMMAND);
            String[] tokens = parseInput();

            String action = tokens[0].trim();

            if (!isValidCommand(action)) {
                printErrorMessage(action);
                continue;
            }

            Command command = Command.valueOf(action);

             switch (command) {
                case REGISTERUSER:
                    try {
                        if (!tokens[2].equals(tokens[3])) {
                            System.out.println(PASSWORDS_DONT_MATCH);
                            break;
                        }

                        UserRegisterDto userRegisterDto =
                                new UserRegisterDto(tokens[1], tokens[2], tokens[4]);

                        System.out.println(this.userService.registerUser(userRegisterDto));

                    } catch (IllegalStateException | ArrayIndexOutOfBoundsException e) {
                        System.out.println(ERROR + e.getMessage());
                        printErrorMessage(action);
                    }

                    break;

                case LOGINUSER:
                    try {

                        UserLoginDto userLoginDto = new UserLoginDto(tokens[1], tokens[2]);

                        System.out.println(this.userService.loginUser(userLoginDto));

                    } catch (RuntimeException e) {
                        System.out.println(ERROR + e.getMessage());
                        printErrorMessage(action);
                    }

                    break;

                case LOGOUT:
                    System.out.println(this.userService.logOutUser());
                    break;

                case ADDGAME:
                    try {

                        GameAddDto gameAddDto = new GameAddDto(
                                tokens[1],
                                new BigDecimal(tokens[2]),
                                Double.parseDouble(tokens[3]),
                                tokens[4], tokens[5], tokens[6],
                                LocalDate.parse(tokens[7],
                                        DateTimeFormatter.ofPattern(DATE_PATTERN))
                        );

                        System.out.println(this.gameService.addGame(gameAddDto));

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(ERROR + e.getMessage());
                        printErrorMessage(action);
                    } catch (NullPointerException e) {
                        System.out.println(ERROR + e.getMessage());
                    } catch (DateTimeParseException e) {
                        System.out.println(ERROR + e.getMessage());
                        System.out.println(DATE_INVALID);
                    }

                    break;

                case EDITGAME:
                    try {

                        GameEditDto gameEditDto = new GameEditDto(
                                Long.parseLong(tokens[1]),
                                new BigDecimal(tokens[2].substring(tokens[2].indexOf("=") + 1)),
                                Double.parseDouble(tokens[3].substring(tokens[3].indexOf("=") + 1))
                        );

                        System.out.println(this.gameService.editGame(gameEditDto));

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(ERROR + e.getMessage());
                        printErrorMessage(action);
                    } catch (NumberFormatException | NullPointerException e) {
                        System.out.println(ERROR + e.getMessage());
                    }

                    break;

                case DELETEGAME:
                    try {

                        GameDeleteDto gameDeleteDto = new GameDeleteDto(Long.parseLong(tokens[1]));

                        System.out.println(this.gameService.deleteGame(gameDeleteDto));

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(ERROR + e.getMessage());
                        printErrorMessage(action);
                    } catch (NumberFormatException | NullPointerException e) {
                        System.out.println(ERROR + e.getMessage());
                    }

                    break;

                case ALLGAMES:

                    if (this.gameService.getAllGames().isEmpty()) {
                        System.out.println(CATALOG_EMPTY);
                    } else {
                        this.gameService.getAllGames()
                                .forEach(g -> System.out.printf(GAME_TITLE_PRICE,
                                        g.getTitle(),
                                        g.getPrice()));
                    }

                    break;

                case DETAILGAME:
                    try {

                        if (tokens.length <= 1) {
                            System.out.println(GAME_TITLE_EMPTY);
                            break;
                        }

                        System.out.println(this.gameService.getGameDetails(tokens[1]));

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(ERROR + e.getMessage());
                        printErrorMessage(action);
                    }

                    break;

                case OWNEDGAMES:

                    try {
                        if (!this.userService.isLogged()) {
                            System.out.println(NOT_LOGGED_IN);
                            break;
                        }

                        if (this.userService.getOwnedGames().isEmpty()) {
                            System.out.println(NO_GAMES_OWNED);
                        } else {
                            this.userService.getOwnedGames()
                                    .forEach(System.out::println);
                        }

                    } catch (IllegalArgumentException | NullPointerException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case ADDITEM:

                    try {

                       if (!this.userService.isLogged()) {
                           System.out.println(NOT_LOGGED_IN);
                           break;
                       }

                       if (tokens.length <= 1) {
                           System.out.println(GAME_TITLE_EMPTY);
                           break;
                       }

                       BuyItemDto buyItemDto = this.gameService.getGameToAdd(tokens[1]);

                       System.out.println(this.cartService.add(buyItemDto));

                    } catch (IllegalArgumentException | NullPointerException e) {
                       System.out.println(e.getMessage());
                   }

                    break;
                case REMOVEITEM:

                    try {

                        if (!this.userService.isLogged()) {
                            System.out.println(NOT_LOGGED_IN);
                            break;
                        }

                        System.out.println(this.cartService.remove(tokens[1]));

                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case BUYITEM:

                    try {

                        if (!this.userService.isLogged()) {
                            break;
                        }

                        if (this.cartService.isCarEmpty()) {
                            System.out.println(CART_EMPTY);
                            break;
                        }

                        System.out.println(BOUGHT_GAMES);

                        this.orderService.placeOrder()
                                .forEach(game -> System.out.printf(GAME_TITLE, game));

                        System.out.println(this.cartService.empty());

                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case EXIT:
                    System.exit(0);
                    break;
                default:
                    printErrorMessage(action);
            }
        }
    }

    protected String[] parseInput() throws IOException {

        String[] tokens = Arrays.stream(this.reader.readLine().split("\\|"))
                .map(String::trim)
                .toArray(String[]::new);

        tokens[0] = tokens[0].toUpperCase();

        return tokens;
    }

    protected boolean isValidCommand(String input) {
        return Arrays.stream(Command.values())
                .anyMatch(c -> input.equals(c.toString()));
    }

    private void printErrorMessage(String command) {

        System.out.println(DELIMITER);

        switch (command) {
            case "RegisterUser":
            case "LoginUser":
                System.out.printf(INVALID_COMMAND_PARAMS,
                        REGISTER_INPUT + LOGIN_INPUT,
                        DELIMITER);
                return;
            case "AddGame":
            case "EditGame":
            case "DeleteGame":
                System.out.printf(INVALID_COMMAND_PARAMS,
                        ADD_GAME_INPUT + EDIT_GAME_INPUT + DELETE_INPUT,
                        DELIMITER);
                return;
            default:
                System.out.printf(COMMAND_INVALID,
                        command,
                        ACCEPTED_COMMANDS);
        }


    }
}
