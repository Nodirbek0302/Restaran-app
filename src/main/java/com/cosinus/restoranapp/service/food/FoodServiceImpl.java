package com.cosinus.restoranapp.service.food;

import com.cosinus.restoranapp.dto.*;
import com.cosinus.restoranapp.enums.FoodState;
import com.cosinus.restoranapp.enums.TableState;
import com.cosinus.restoranapp.exceptions.RestException;
import com.cosinus.restoranapp.model.*;
import com.cosinus.restoranapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final TableRepository tableRepository;
    private final BronTableRepository bronTableRepository;
    private final OrderFoodRepository orderFoodRepository;
    private final HistoryRepository historyRepository;

    @Override
    public ApiResult<List<FoodResponseDTO>> list() {
        List<FoodResponseDTO> foodResponseDTOS = new ArrayList<>();
        for (Food food : foodRepository.findAll())
            foodResponseDTOS.add(mapFoodResponseDTO(food));

        return ApiResult.successResponse(foodResponseDTOS);
    }

    @Override
    public ApiResult<FoodResponseDTO> byId(Integer id) {
        return ApiResult.successResponse(mapFoodResponseDTO(foodRepository
                .findById(id)
                .orElseThrow(() -> RestException.restThrow("Bunday ovqat mavjud emas", HttpStatus.BAD_REQUEST))));
    }

    @Override
    public ApiResult<FoodResponseDTO> add(FoodAddDTO foodAddDTO) {
        Food food = Food.builder()
                .name(foodAddDTO.getFoodName())
                .CurrentPrice(foodAddDTO.getPrice())
                .discount(0)
                .build();
        foodRepository.save(food);
        return ApiResult.successResponse(mapFoodResponseDTO(food));
    }

    @Override
    public ApiResult<FoodResponseDTO> update(Integer id, FoodAddDTO foodAddDTO) {
        Food food = foodRepository.findById(id).orElseThrow(() -> RestException.restThrow("Bunday food mavjud emas", HttpStatus.BAD_REQUEST));
        food.setName(foodAddDTO.getFoodName());
        food.setCurrentPrice(foodAddDTO.getPrice());
        foodRepository.save(food);
        return ApiResult.successResponse(mapFoodResponseDTO(food));
    }

    @Override
    public ApiResult<Boolean> delete(Integer id) {
        foodRepository.deleteById(id);
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> order(List<OrderFoodDTO> orderFood) {
        for (OrderFoodDTO orderFoodDTO : orderFood) {
            if (checkTableBron(orderFoodDTO.getTableId())) {
                OrderFood orderFood1 = OrderFood.builder()
                        .foodId(orderFoodDTO.getFoodId())
                        .tableId(orderFoodDTO.getTableId())
                        .state(FoodState.TAYYORLANMOQDA)
                        .count(orderFoodDTO.getCount())
                        .build();
                orderFoodRepository.save(orderFood1);
            }else {
                throw RestException.restThrow(orderFoodDTO.getTableId()+" Bunday Idli table BRON qilinmagan bron qilinmagan stolga buyurtma qilib bo'lmaydi",HttpStatus.BAD_REQUEST);
            }
        }
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<CalcResponseDTO> calculation(Integer tableId) {
        if (!checkTableBron(tableId))
            throw RestException.restThrow("Bu stol bron qilinmagan",HttpStatus.BAD_REQUEST);


        List<FoodPriceDTO> foodPriceDTOS = new ArrayList<>();
        double totalPriceFood=0.0;

        List<OrderFood> foods = orderFoodRepository.findByTableId(tableId);
        for (OrderFood food : foods) {
            Food food1 = findFood(food.getId());

            FoodPriceDTO build = FoodPriceDTO.builder()
                    .foodName(food1.getName())
                    .price(food1.getCurrentPrice())
                    .build();
            foodPriceDTOS.add(build);
            if (food1.getDiscount()!=0){
                totalPriceFood+=(food1.getCurrentPrice()-(food1.getCurrentPrice()*food1.getDiscount()/ 100))*food.getCount();
            }else {
                totalPriceFood+=food1.getCurrentPrice()*food.getCount();
            }
        }
        CalcResponseDTO calcResponseDTO = CalcResponseDTO.builder()
                .priceDTOList(foodPriceDTOS)
                .totalPrice(totalPriceFood)
                .count(foodPriceDTOS.size())
                .build();

       BronTable bronTable = todayTable(tableId);

       if (bronTable==null)
           throw RestException.restThrow("Bunday table bron qilinmagan",HttpStatus.BAD_REQUEST);


        History build = History.builder()
                .tableId(tableId)
                .stulNumber(bronTable.getStulNumber())
                .customerId(bronTable.getUserId())
                .foodList(foods.stream().map(orderFood -> findFood(orderFood.getFoodId())).toList())
                .historyTime(LocalDateTime.now())
                .totalPrice(totalPriceFood )
                .build();
        historyRepository.save(build);

        List<Integer> list = foods.stream().map(OrderFood::getId).toList();
        orderFoodRepository.deleteAllById(list);

        Tables tables = tableRepository.findById(tableId).orElseThrow(() -> RestException.restThrow("bunday table mavjud emas", HttpStatus.BAD_REQUEST));
        tables.setState(TableState.ACTIVE);
        tableRepository.save(tables);

        return ApiResult.successResponse(calcResponseDTO);
    }

    @Override
    public ApiResult<Boolean> discount(SetDiscountDTO setDiscountDTO) {
        Food food = foodRepository.findById(setDiscountDTO.getFoodId()).orElseThrow(() -> RestException.restThrow("Bunday food mavjud emas", HttpStatus.BAD_REQUEST));
        food.setDiscount(setDiscountDTO.getSetDiscount());
        foodRepository.save(food);
        return ApiResult.successResponse(true);
    }

    private Food findFood(Integer foodId) {
       return foodRepository.findById(foodId).orElseThrow(() -> RestException.restThrow("bunday ovqat mavjud emas",HttpStatus.BAD_REQUEST));
    }

    private BronTable todayTable(Integer tableId) {
        List<BronTable> bronTables = bronTableRepository.findByTablesId(tableId);
        for (BronTable bronTable : bronTables) {
            if (checkBronTable(bronTable))
                return bronTable;
        }
        return null;
    }

    private boolean checkBronTable(BronTable bronTable) {
        return bronTable.getStartBronTime().getYear() == LocalDateTime.now().getYear()
                && bronTable.getStartBronTime().getDayOfMonth()==LocalDateTime.now().getDayOfMonth()
                && bronTable.getStartBronTime().getDayOfWeek() == LocalDateTime.now().getDayOfWeek();
    }

    private boolean checkTableBron(Integer tableId) {
        Tables tables = tableRepository.findById(tableId).orElseThrow(() -> RestException.restThrow("Bunday table mavjud emas", HttpStatus.BAD_REQUEST));
        return tables.getState() == TableState.BRON;
    }

    private FoodResponseDTO mapFoodResponseDTO(Food food) {
        return FoodResponseDTO.builder()
                .id(food.getId())
                .foodName(food.getName())
                .price(food.getCurrentPrice())
                .discount(food.getDiscount())
                .build();
    }
}
