package com.cosinus.restoranapp.service.auth;


import com.cosinus.restoranapp.dto.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    ApiResult<TokenDTO> login(LoginDTO loginDTO);

    ApiResult<TokenDTO> refreshToken(String accessToken,String refreshToken);

    ApiResult<String> register(RegisterDTO registerDTO);

    ApiResult<Boolean> setRole(SetUserRole setUserRole);
}
