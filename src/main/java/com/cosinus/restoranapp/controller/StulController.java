package com.cosinus.restoranapp.controller;

import com.cosinus.restoranapp.dto.ApiResult;
import com.cosinus.restoranapp.dto.StulAddDTO;
import com.cosinus.restoranapp.service.stul.StulService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stul")
@RequiredArgsConstructor
public class StulController {

    private final StulService stulService;

    @PostMapping("/add")
    public HttpEntity<ApiResult<Boolean>> add(@Valid @RequestBody StulAddDTO stulAddDTO){
        return ResponseEntity.ok(stulService.add(stulAddDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HttpEntity<ApiResult<Boolean>> delete(@PathVariable Integer id){
        return ResponseEntity.ok(stulService.delete(id));
    }


}
