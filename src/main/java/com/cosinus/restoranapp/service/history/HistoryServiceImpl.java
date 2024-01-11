package com.cosinus.restoranapp.service.history;

import com.cosinus.restoranapp.dto.ApiResult;
import com.cosinus.restoranapp.dto.HistoryResponseDTO;
import com.cosinus.restoranapp.dto.StatDTO;
import com.cosinus.restoranapp.model.History;
import com.cosinus.restoranapp.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    public final HistoryRepository historyRepository;

    @Override
    public ApiResult<HistoryResponseDTO> sata(StatDTO statDTO) {
        List<History> histories = historyRepository.findByHistoryTimeAndIdBetween(statDTO.getStartDate(), statDTO.getEndDate());
        Double totalPrice = totalPriceHistory(histories);
        return ApiResult.successResponse(HistoryResponseDTO.builder()
                .historyList(histories)
                .totalPrice(totalPrice)
                .build());
    }

    private Double totalPriceHistory(List<History> histories) {
        double totalP = 0.0;
        for (History history : histories) {
            totalP+=history.getTotalPrice();
        }
        return totalP;
    }
}
