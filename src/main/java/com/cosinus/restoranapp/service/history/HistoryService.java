package com.cosinus.restoranapp.service.history;

import com.cosinus.restoranapp.dto.ApiResult;
import com.cosinus.restoranapp.dto.HistoryResponseDTO;
import com.cosinus.restoranapp.dto.StatDTO;

public interface HistoryService {

    ApiResult<HistoryResponseDTO> sata(StatDTO statDTO);
}
