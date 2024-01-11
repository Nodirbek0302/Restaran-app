package com.cosinus.restoranapp.service.user;

import com.cosinus.restoranapp.dto.ApiResult;
import com.cosinus.restoranapp.dto.UserAddDTO;
import com.cosinus.restoranapp.dto.UserResponseDTO;
import com.cosinus.restoranapp.exceptions.RestException;
import com.cosinus.restoranapp.model.Users;
import com.cosinus.restoranapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ApiResult<List<Users>> list() {
        return ApiResult.successResponse(userRepository.findAll());
    }


    @Override
    public ApiResult<UserResponseDTO> byId(Integer id) {
        Users users = getUser(id);
        return ApiResult.successResponse(UserResponseDTO.builder()
                .id(users.getId())
                .name(users.getName())
                .roleName(users.getRoles().name())
                .email(users.getEmail())
                .build());
    }

    @Override
    public ApiResult<Boolean> update(Integer id, UserAddDTO userAddDTO) {
        Users user = getUser(id);
        if (!user.getPassword().equals(userAddDTO.getOldPassword()))
            throw RestException.restThrow("old password xato kiritildi",HttpStatus.BAD_REQUEST);

        user.setName(user.getName());
        user.setPassword(user.getPassword());
        user.setEmail(user.getEmail());
        userRepository.save(user);
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> delete(Integer id) {
       try {
           userRepository.deleteById(id);
           return ApiResult.successResponse(true);
       } catch (Exception e) {
           throw RestException.restThrow("bunday user mavjud emas",HttpStatus.NO_CONTENT);
       }
    }

    private Users getUser(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> RestException.restThrow("Bunday user mavjud emas", HttpStatus.BAD_REQUEST));
    }
}
