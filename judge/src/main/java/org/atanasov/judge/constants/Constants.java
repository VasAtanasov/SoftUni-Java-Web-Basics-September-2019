package org.atanasov.judge.constants;

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

    public static final String PROBLEMS_URL = "/problems";

    public static final String CREATE_URL = "/create";
    public static final String SUBMIT_URL = "/submit";
    public static final String DETAILS_URL = "/details";


    public static final String PROBLEMS_CREATE_URL = PROBLEMS_URL + CREATE_URL;
    public static final String PROBLEMS_SUBMIT_URL = PROBLEMS_URL + SUBMIT_URL;
    public static final String PROBLEMS_DETAILS_URL = PROBLEMS_URL + DETAILS_URL;


    private Constants() {
    }
}
