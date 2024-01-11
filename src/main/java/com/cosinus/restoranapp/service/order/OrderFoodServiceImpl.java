package com.cosinus.restoranapp.service.order;

import com.cosinus.restoranapp.dto.ApiResult;
import com.cosinus.restoranapp.dto.SetStateDTO;
import com.cosinus.restoranapp.enums.FoodState;
import com.cosinus.restoranapp.exceptions.RestException;
import com.cosinus.restoranapp.model.OrderFood;
import com.cosinus.restoranapp.repository.OrderFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFoodServiceImpl implements OrderFoodService {

    private final OrderFoodRepository orderFoodRepository;

    @Override
    public ApiResult<Boolean> setState(SetStateDTO setStateDTO) {
        try {
            OrderFood orderFood = orderFoodRepository.findById(setStateDTO.getOrderId()).orElseThrow(() -> RestException.restThrow("Bunday order mavjud emas", HttpStatus.BAD_REQUEST));
            String upperCase = setStateDTO.getState().toUpperCase();
            orderFood.setState(FoodState.valueOf(upperCase));
            orderFoodRepository.save(orderFood);
            return ApiResult.successResponse(true);
        } catch (Exception e) {
            throw RestException.restThrow("Iltimos state to'g'riligiga etiborli buling", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ApiResult<List<OrderFood>> all() {
        return ApiResult.successResponse(orderFoodRepository.findAll());
    }
}
