package com.cosinus.restoranapp.utils;


public interface AppConstants {
    String BEARER_TYPE = "Bearer";
    String AUTH_HEADER = "Authorization";
    String REFRESH_AUTH_HEADER = "RefreshToken";
    String PASSWORD_REGEXP = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#_$%^&+=])(?=\\S+$).{8,}$";

//    Auth Controller Path
    String AUTHE_CONTROLLER_BASE_PATH = "/api/auth";
    String AUTHE_CONTROLLER_LOGIN_PATH = "/login";
    String AUTHE_CONTROLLER_REFRESH_TOKEN_PATH = "/refresh-token";
    String AUTHE_CONTROLLER_REGISTER_PATH = "/register";
    String AUTHE_CONTROLLER_SEND_EMAIL = "/email";
    //    ----------------------------------------------------------------------------------------------


}
