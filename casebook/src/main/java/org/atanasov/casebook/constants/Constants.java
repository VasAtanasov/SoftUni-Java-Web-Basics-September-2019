package org.atanasov.casebook.constants;

public final class Constants {

    public static final String EMAIL_ATTR = "email";
    public static final String CONF_PASSWORD_ATTR = "confirmPassword";
    public static final String PASSWORD_ATTR = "password";
    public static final String USERNAME_ATTR = "username";
    public static final String USER_ID_ATTR = "userId";

    public static final String MODEL_ATTR = "model";

    public static final String ERR_MSG_ATTR = "errMsg";

    public static final String HTTP_POST_METHOD = "POST";

    public static final String ROOT_URL = "/";
    public static final String INDEX_URL = "/index";

    public static final String USERS_URL = "/users";
    public static final String REGISTER_URL = "/register";
    public static final String LOGIN_URL = "/login";
    public static final String LOGOUT_URL = "/logout";
    public static final String HOME_URL = "/home";

    public static final String FRIENDS_URL = "/friends";
    public static final String PROFILE_URL = "/profile";

    public static final String ADD_URL = "/add";
    public static final String REMOVE_URL = "/remove";

    public static final String FRIENDS_ADD_URL = FRIENDS_URL + ADD_URL;
    public static final String FRIENDS_REMOVE_URL = FRIENDS_URL + REMOVE_URL;


    private Constants() {
    }
}
