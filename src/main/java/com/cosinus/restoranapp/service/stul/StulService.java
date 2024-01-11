package com.cosinus.restoranapp.service.stul;

import com.cosinus.restoranapp.dto.ApiResult;
import com.cosinus.restoranapp.dto.StulAddDTO;

public interface StulService {

    ApiResult<Boolean> add(StulAddDTO stulAddDTO);

    ApiResult<Boolean> delete(Integer id);
}
