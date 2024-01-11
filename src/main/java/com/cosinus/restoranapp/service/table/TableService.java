package com.cosinus.restoranapp.service.table;

import com.cosinus.restoranapp.dto.*;

import java.util.List;

public interface TableService {

    ApiResult<List<TableResponseDTO>> list();

    ApiResult<TableResponseDTO> add(AddTableDTO addTableDTO);

    ApiResult<TableResponseDTO> findById(Integer id);

    ApiResult<TableResponseDTO> update(Integer id, TableUpdateDTO tableUpdateDTO);

    ApiResult<Boolean> delete(Integer id);

    ApiResult<Boolean> bronTable(BronTableDTO bronTableDTO);
}
