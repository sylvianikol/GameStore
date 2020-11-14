package com.gamestore.gamestore.constants;

public class GlobalRegex {

    public static final String EMAIL_REGEX =
            "^[a-z0-9]+@{1}[a-z0-9]+\\.{1}[a-z]+$";

    public static final String PASSWORD_REGEX =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,})";

    public static final String TITLE_REGEX =
            "^[A-Z][a-z\\sA-Z0-9':\\-/\\.\\,\";()\\!?#$&]{2,99}";

    public static final String THUMBNAIL_URL_REGEX =
            "http[s]?:\\/\\/([a-z0-9]+\\-?[a-z0-9]+\\.?)+([a-z]+\\.?)+[a-z]{2,}(\\/[A-Za-z0-9\\-_~]+)+\\.[a-z]+";
}
