package com.cosinus.restoranapp.service.food;

import com.cosinus.restoranapp.dto.*;

import java.util.List;

public interface FoodService {

    ApiResult<List<FoodResponseDTO>> list();

    ApiResult<FoodResponseDTO> byId(Integer id);

    ApiResult<FoodResponseDTO> add(FoodAddDTO foodAddDTO);

    ApiResult<FoodResponseDTO> update(Integer id, FoodAddDTO foodAddDTO);

    ApiResult<Boolean> delete(Integer id);

    ApiResult<Boolean> order(List<OrderFoodDTO> orderFood);

    ApiResult<CalcResponseDTO> calculation(Integer tableId);

    ApiResult<Boolean> discount(SetDiscountDTO setDiscountDTO);
}
