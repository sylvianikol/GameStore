package com.gamestore.gamestore.constants;

public class GlobalConstants {

    public static final String DELIMITER =
            String.format("%n%s%n", "-".repeat(40));

    public static final String ENTER_COMMAND = "Enter Command: ";



    public static final String USER_REGISTERED =
            "%s was registered%n";

    public static final String ERROR = "ERROR: ";

    public static final String DATE_PATTERN = "dd-MM-yyyy";

    public static final String GAME_ADDED = "Added %s%n";

    public static final String GAME_EDITED = "Edited %s%n";

    public static final String GAME_DELETED = "Deleted %s%n";

    public static final String GAME_TITLE_PRICE = "%s %.2f%n";

    public static final String GAME_TITLE = "\t-%s%n";

    public static final String GAME_DETAILS =
            "Title: %s%n" +
                    "Price: %.2f %n" +
                    "Description: %s%n" +
                    "Release date: %s%n";

    public static final String ADDED_TO_CART =
            "Game %s added to Shopping cart%n";

    public static final String ALREADY_IN_CART =
            "Game %s is already added in Shopping cart%n";

    public static final String REMOVED_FROM_CART =
            "Game %s removed from Shopping cart%n";

    public static final String BOUGHT_GAMES =
            "Successfully bought games:";

    public static final String NO_ITEMS_IN_CART =
            "No items in Shopping cart.";

    public static final String LOGIN_INPUT =
            String.format("     -> LoginUser|ivan@ivan.com|Ivan12%n");

    public static final String REGISTER_INPUT =
            String.format("     -> RegisterUser|ivan@ivan.com|Ivan12|Ivan12|Ivan%n");

    public static final String ADD_GAME_INPUT =
            String.format("     -> AddGame|Overwatch|100.00|15.5|FqnKB22pOC0|https://us.battle.net/overwatch.png|Overwatch is a team-based multiplayer...|24-05-2016%n");

    public static final String EDIT_GAME_INPUT =
            String.format("     -> EditGame|1|price=80.00|size=12.0%n");

    public static final String DELETE_INPUT =
            String.format("     -> DeleteGame|1");

    public static final String ACCEPTED_COMMANDS =
            String.format("Accepted Commands are:%n" +
                    "       -> RegisterUser%n" +
                    "       -> LoginUser%n" +
                    "       -> Logout%n" +
                    "       -> AddGame%n" +
                    "       -> EditGame%n" +
                    "       -> DeleteGame%n" +
                    "       -> AllGames%n" +
                    "       -> DetailGame%n" +
                    "       -> OwnedGames%n" +
                    "       -> AddItem%n" +
                    "       -> RemoveItem%n" +
                    "       -> BuyItem%n");
}
