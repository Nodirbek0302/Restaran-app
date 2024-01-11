package com.cosinus.restoranapp.service.auth;

import com.cosinus.restoranapp.config.jwtFilter.JWTProvider;
import com.cosinus.restoranapp.dto.*;
import com.cosinus.restoranapp.enums.Roles;
import com.cosinus.restoranapp.exceptions.RestException;
import com.cosinus.restoranapp.model.Users;
import com.cosinus.restoranapp.repository.UserRepository;
import com.cosinus.restoranapp.utils.AppConstants;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository,
                           @Lazy AuthenticationManager authenticationManager,
                           PasswordEncoder passwordEncoder, JWTProvider jwtProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ApiResult<TokenDTO> login(LoginDTO loginDTO) {
        Users user1 = checkCredential(loginDTO.email(), loginDTO.password());
        return ApiResult.successResponse(generateTokenDTO(user1));
    }

    @Override
    public ApiResult<TokenDTO> refreshToken(String accessToken, String refreshToken) {
        if (!accessToken.startsWith(AppConstants.BEARER_TYPE))
            throw RestException.restThrow("Bearer emas");

        accessToken = accessToken.substring(AppConstants.BEARER_TYPE.length()).trim();
        refreshToken = refreshToken.substring(AppConstants.BEARER_TYPE.length()).trim();
        if (!jwtProvider.isExpired(accessToken))
            throw RestException.restThrow("Token muddati tugamagan");

        if (!jwtProvider.validRefreshToken(refreshToken))
            throw RestException.restThrow("Refresh token valid emas");

        String userId = jwtProvider.extractUserIdFromRefreshToken(refreshToken);
        Users user = findUserById(Integer.valueOf(userId))
                .orElseThrow(() -> RestException.restThrow("User not found: " + userId, HttpStatus.NOT_FOUND));

        TokenDTO tokenDTO = generateTokenDTO(user);

        return ApiResult.successResponse(tokenDTO);
    }

    @Override
    public ApiResult<String> register(RegisterDTO registerDTO) {

        if (!registerDTO.password().equals(registerDTO.perPassword()))
            throw RestException.restThrow("parrollar birxilmas", HttpStatus.BAD_REQUEST);

        Users user = Users.builder()
                .name(registerDTO.name())
                .email(registerDTO.email())
                .password(passwordEncoder.encode(registerDTO.password()))
                .accountNonLocked(false)
                .roles(Roles.USER)
                .build();
        userRepository.save(user);

        return ApiResult.successResponse("successfully");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public Optional<Users> findUserById(Integer userId) {
        try {
            if (userId == null)
                return Optional.empty();
            return userRepository.findById(userId);
        } catch (Exception e) {
           throw RestException.restThrow("Bunday user mavjud emas",HttpStatus.BAD_REQUEST);
        }
    }


    private TokenDTO generateTokenDTO(Users user) {
        String id = user.getId().toString();
        String accessToken = jwtProvider.createAccessToken(id);
        String refreshToken = jwtProvider.createRefreshAccessToken(id);
        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Users checkCredential(String username, String password) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                password
        );
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return (Users) authentication.getPrincipal();
    }

    @Override
    public ApiResult<Boolean> setRole(SetUserRole setUserRole) {
        try {
            Users us = userRepository.findById(setUserRole.getUserId()).orElseThrow(() -> RestException.restThrow("Bunday user mavjud emas", HttpStatus.BAD_REQUEST));
            String upperCase = setUserRole.getRoleName().toUpperCase();
            us.setRoles(Roles.valueOf(upperCase));
            userRepository.save(us);
            return ApiResult.successResponse(true);
        } catch (Exception e) {
            throw RestException.restThrow("Iltimos rolni tug'ri kiriting ",HttpStatus.BAD_REQUEST);
        }
    }
}
