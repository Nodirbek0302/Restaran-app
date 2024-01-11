package com.cosinus.restoranapp.controller;


import com.cosinus.restoranapp.dto.*;
import com.cosinus.restoranapp.service.auth.AuthService;
import com.cosinus.restoranapp.utils.AppConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.AUTHE_CONTROLLER_BASE_PATH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(AppConstants.AUTHE_CONTROLLER_LOGIN_PATH)
    public HttpEntity<ApiResult<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO){
      return ResponseEntity.ok(authService.login(loginDTO));
    }

    @PostMapping(AppConstants.AUTHE_CONTROLLER_REGISTER_PATH)
    public HttpEntity<ApiResult<String>> register(@Valid @RequestBody RegisterDTO registerDTO){
      return ResponseEntity.ok(authService.register(registerDTO));
    }

   @PatchMapping(AppConstants.AUTHE_CONTROLLER_REFRESH_TOKEN_PATH)
    public HttpEntity<ApiResult<TokenDTO>> refreshToken(String accessToken,String refreshToken){
        return ResponseEntity.ok(authService.refreshToken(accessToken,refreshToken));
   }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @PostMapping("/setRole")
    public HttpEntity<ApiResult<Boolean>> setRole(@Valid @RequestBody SetUserRole setUserRole){
        return ResponseEntity.ok(authService.setRole(setUserRole));
    }
}
