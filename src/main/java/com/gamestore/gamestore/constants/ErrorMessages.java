package com.gamestore.gamestore.constants;

public class ErrorMessages {

    public static final String EMAIL_ALREADY_USED =
            "Email '%s' is already used by another user.";

    public static final String EMAIL_INVALID =
            "Email is not valid!";

    public static final String PASSWORD_INVALID =
            "Password is not valid!";

    public static final String PASSWORD_LENGTH_INVALID =
            "Password length must be at least 6 symbols";

    public static final String NAME_NOT_NULL =
            "Name can not be null!";

    public static final String PASSWORDS_DONT_MATCH =
            "Passwords do not match!";

    public static final String INCORRECT_USER_PASSWORD =
            "Incorrect username / password";

    public static final String ALREADY_LOGGED_IN =
            "'%s' is already logged in!%n";

    public static final String ANOTHER_USER_LOGGED_IN =
            "Another User is currently logged in!";

    public static final String LOGGED_IN =
            "Successfully logged in '%s'%n";

    public static final String CAN_NOT_LOG_OUT =
            "Cannot log out. No user was logged in.";

    public static final String LOGGED_OUT =
            "User '%s' successfully logged out%n";

    public static final String NOT_ADMIN =
            "Logged user is not Admin";

    public static final String TITLE_INVALID =
            "Title is not valid";

    public static final String PRICE_INVALID =
            "Price must be a positive number";

    public static final String SIZE_INVALID =
            "Size must be a positive number";

    public static final String TRAILER_ID_INVALID =
            "Trailer's ID must be exactly 11 symbols";

    public static final String THUMBNAIL_URL_INVALID =
            "Thumbnail URL is not valid";

    public static final String DESCRIPTION_INVALID =
            "Description must be at least 20 symbols";

    public static final String DATE_INVALID =
            "Date format should be [dd-mm-yyyy]";

    public static final String ID_INVALID =
            "ID must be a positive number";

    public static final String GAME_ALREADY_EXISTS =
            "Game '%s' already exists!";

    public static final String GAME_ID_NOT_EXISTS =
            "Game with ID=%d does not exists!%n";

    public static final String GAME_TITLE_NOT_EXISTS =
            "Game '%s' does not exists!%n";

    public static final String GAME_TITLE_EMPTY =
            "Game title can not be null or empty!";

    public static final String CATALOG_EMPTY =
            "Games catalog is empty!";

    public static final String NO_GAMES_OWNED =
            "You don't own any games!";

    public static final String ALREADY_BOUGHT =
            "You already bought game '%s'!";

    public static final String NOT_LOGGED_IN =
            "You are not logged in!";

    public static final String CART_EMPTY =
            "Your cart is empty!";

    public static final String NOT_FOUND_IN_CART =
            "Game '%s' not found in Shopping cart%n";

    public static final String INVALID_COMMAND_PARAMS =
            "Make sure that the Command parameters are entered in the right format:%n%s%s";

    public static final String COMMAND_INVALID =
            "'%s' is not a valid command!%n%s";
}
