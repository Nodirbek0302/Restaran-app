package com.cosinus.restoranapp.service.order;

import com.cosinus.restoranapp.dto.ApiResult;
import com.cosinus.restoranapp.dto.SetStateDTO;
import com.cosinus.restoranapp.model.OrderFood;

import java.util.List;

public interface OrderFoodService {
    ApiResult<Boolean> setState(SetStateDTO setStateDTO);

    ApiResult<List<OrderFood>> all();
}
