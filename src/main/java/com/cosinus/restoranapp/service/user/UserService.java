package com.cosinus.restoranapp.service.user;

import com.cosinus.restoranapp.dto.ApiResult;
import com.cosinus.restoranapp.dto.UserAddDTO;
import com.cosinus.restoranapp.dto.UserResponseDTO;
import com.cosinus.restoranapp.model.Users;

import java.util.List;

public interface UserService {

    ApiResult<List<Users>> list();

    ApiResult<UserResponseDTO> byId(Integer id);

    ApiResult<Boolean> update(Integer id, UserAddDTO userAddDTO);

    ApiResult<Boolean> delete(Integer id);
}
