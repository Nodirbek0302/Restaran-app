package com.cosinus.restoranapp.service.table;

import com.cosinus.restoranapp.dto.*;
import com.cosinus.restoranapp.enums.TableState;
import com.cosinus.restoranapp.exceptions.RestException;
import com.cosinus.restoranapp.model.*;
import com.cosinus.restoranapp.repository.*;
import com.cosinus.restoranapp.service.stul.StulService;
import com.cosinus.restoranapp.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;
    private final StulService stulService;
    private final UserRepository  userRepository;
    private final FoodRepository foodRepository;
    private final StulRepository stulRepository;
    private final BronTableRepository bronTableRepository;


    @Override
    public ApiResult<List<TableResponseDTO>> list() {
        List<Tables> all = tableRepository.findAll();
        return ApiResult.successResponse(mapTableResponseDTO(all));
    }

    @Override
    public ApiResult<TableResponseDTO> add(AddTableDTO addTableDTO) {

        Optional<Tables> byTableName = tableRepository.findByTableName(addTableDTO.getTableName());
        if (byTableName.isPresent())
          throw  RestException.restThrow("bunday table mavjud",HttpStatus.BAD_REQUEST);

        Tables tables = Tables.builder()
                .tableName(addTableDTO.getTableName())
                .state(TableState.ACTIVE)
                .build();
        Tables save = tableRepository.save(tables);
        stulService.add(StulAddDTO.builder().tableId(save.getId()).count(10).build());

        return ApiResult.successResponse(mapTableResponseDTO(tables));
    }

    @Override
    public ApiResult<TableResponseDTO> findById(Integer id) {
        Tables t = tableRepository.findById(id).orElseThrow(() -> RestException.restThrow("Bunday table mavjud emas", HttpStatus.BAD_REQUEST));
        return ApiResult.successResponse(TableResponseDTO.builder().id(t.getId()).tableName(t.getTableName()).build());
    }

    @Override
    public ApiResult<TableResponseDTO> update(Integer id, TableUpdateDTO tableUpdateDTO) {
        Tables tables = tableRepository.findById(id).orElseThrow(() -> RestException.restThrow("Bunday table mavjud emas", HttpStatus.BAD_REQUEST));
        List<Stul> stulList = findAllStulByIds(tableUpdateDTO.getStulIds());


        tables.setTableName(tableUpdateDTO.getTableName());
        tables.setStulList(stulList);
        tableRepository.save(tables);

        return ApiResult.successResponse(mapTableResponseDTO(tables));
    }

    @Override
    public ApiResult<Boolean> delete(Integer id) {
        tableRepository.deleteById(id);
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> bronTable(BronTableDTO bronTableDTO) {
        Tables tables = tableRepository.findById(bronTableDTO.getTableNumber()).orElseThrow(() -> RestException.restThrow("bunday stol mavjud emas", HttpStatus.BAD_REQUEST));
        if (tables.getState()!=TableState.ACTIVE)
            throw RestException.restThrow("Bu table bron qilingan ",HttpStatus.BAD_REQUEST);

        BronTable bronTable = BronTable.builder()
                .tablesId(tables.getId())
                .stulNumber(bronTableDTO.getStulNumber())
                .userId(CommonUtils.getCurrentUserFromContext().getId())
                .startBronTime(bronTableDTO.getStartBronTime())
                .endBronTime(bronTableDTO.getEndBronTime())
                .build();
        tables.setState(TableState.BRON);
        tableRepository.save(tables);

        bronTableRepository.save(bronTable);
        return ApiResult.successResponse(true);
    }







    private List<TableResponseDTO> mapTableResponseDTO(List<Tables> all) {
        List<TableResponseDTO> tableResponseDTOS = new ArrayList<>();

        for (Tables tables : all) {
            TableResponseDTO tableResponseDTO = TableResponseDTO.builder()
                    .tableName(tables.getTableName())
                    .id(tables.getId())
                    .build();
            tableResponseDTOS.add(tableResponseDTO);
        }

        return tableResponseDTOS;
    }

    private TableResponseDTO mapTableResponseDTO(Tables tables) {
        return TableResponseDTO.builder()
                .id(tables.getId())
                .tableName(tables.getTableName())
                .build();
    }

    private List<UserResponseDTO> mapToUserResponseDTO(List<Users> users) {
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        for (Users user : users) {
            UserResponseDTO build = UserResponseDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .roleName(user.getRoles().name())
                    .build();
            userResponseDTOS.add(build);
        }
        return userResponseDTOS;
    }

    private List<Food> findAllFoodsByIds(List<Integer> foodIds) {
        List<Food> foods;
        try {
            foods = foodRepository.findAllById(foodIds);
            return foods;
        } catch (Exception e) {
            throw RestException.restThrow("Berilgan foods topilmadi",HttpStatus.BAD_REQUEST);
        }
    }

    private List<Users> findAllUsersByIds(List<Integer> userIds) {
        List<Users> users;
        try {
            users = userRepository.findAllById(userIds);
            return users;
        } catch (Exception e) {
            throw RestException.restThrow("Berilgan userlar topilmadi",HttpStatus.BAD_REQUEST);
        }
    }

    private List<Stul> findAllStulByIds(List<Integer> stulIds) {
        List<Stul> stulList;
        try {
            stulList = stulRepository.findAllById(stulIds);
            return stulList;
        } catch (Exception e) {
            throw RestException.restThrow("Berilgan Stullar topilmadi",HttpStatus.BAD_REQUEST);
        }
    }
}
