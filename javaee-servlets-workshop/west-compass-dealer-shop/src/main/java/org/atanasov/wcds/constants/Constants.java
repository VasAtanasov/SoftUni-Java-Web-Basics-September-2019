package org.atanasov.wcds.constants;

public final class Constants {

    public static final String EMAIL_ATTR = "email";
    public static final String CONF_PASSWORD_ATTR = "confirmPassword";
    public static final String PASSWORD_ATTR = "password";
    public static final String USERNAME_ATTR = "username";

    public static final String MODEL_ATTR = "model";
    public static final String BRAND_ATTR = "brand";
    public static final String YEAR_ATTR = "year";
    public static final String ENGINE_ATTR = "engine";

    public static final String ERR_MSG_ATTR = "errMsg";
    public static final String CAR_ATTR = "car";
    public static final String CARS_ATTR = "cars";

    public static final String HTTP_POST_METHOD = "POST";
    public static final String INDEX_URL = "/index";

    public static final String USERS_URL = "/users";
    public static final String REGISTER_URL = "/register";
    public static final String LOGIN_URL = "/login";
    public static final String USER_REGISTER_URL = USERS_URL + "/register";
    public static final String USER_LOGIN_URL = USERS_URL + "/login";

    public static final String LOGOUT_URL = "/logout";
    public static final String HOME_URL = "/home";

    public static final String CARS_URL = "/cars";
    public static final String CREATE_URL = "/create";
    public static final String ALL_URL = "/all";
    public static final String CARS_CREATE_URL = CARS_URL + CREATE_URL;
    public static final String CARS_ALL_URL = CARS_URL + ALL_URL;
    private static final String EXTENSION = ".jsp";

    private static final String VIEWS_PATH = "/views";
    private static final String JSP_INDEX_URL = "/index";

    public static final String HOME_VIEW = VIEWS_PATH + HOME_URL + EXTENSION;
    public static final String INDEX_VIEW = VIEWS_PATH + JSP_INDEX_URL + EXTENSION;

    public static final String REGISTER_VIEW = VIEWS_PATH + USERS_URL + REGISTER_URL + EXTENSION;
    public static final String LOGIN_VIEW = VIEWS_PATH + USERS_URL + LOGIN_URL + EXTENSION;

    public static final String CARS_CREATE_VIEW = VIEWS_PATH + CARS_URL + CREATE_URL + EXTENSION;
    public static final String CARS_ALL_VIEW = VIEWS_PATH + CARS_URL + ALL_URL + EXTENSION;

    private Constants() {
    }
}
