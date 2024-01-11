package com.cosinus.restoranapp.service.stul;

import com.cosinus.restoranapp.dto.ApiResult;
import com.cosinus.restoranapp.dto.StulAddDTO;
import com.cosinus.restoranapp.exceptions.RestException;
import com.cosinus.restoranapp.model.Stul;
import com.cosinus.restoranapp.model.Tables;
import com.cosinus.restoranapp.repository.StulRepository;
import com.cosinus.restoranapp.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StulServiceImpl implements StulService {

    private final StulRepository stulRepository;
    private final TableRepository tableRepository;


    @Override
    public ApiResult<Boolean> add(StulAddDTO stulAddDTO) {
        Tables tables = tableRepository.findById(stulAddDTO.getTableId()).orElseThrow(() -> RestException.restThrow("bunday table mavjud emas", HttpStatus.BAD_REQUEST));
        for (int i = 0; i < stulAddDTO.getCount(); i++) {
            Stul build = Stul.builder()
                    .table(tables).build();
            stulRepository.save(build);
        }
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Boolean> delete(Integer id) {
        stulRepository.deleteById(id);
        return ApiResult.successResponse(true);
    }
}
