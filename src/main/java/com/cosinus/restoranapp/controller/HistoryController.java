package com.cosinus.restoranapp.controller;

import com.cosinus.restoranapp.dto.ApiResult;
import com.cosinus.restoranapp.dto.HistoryResponseDTO;
import com.cosinus.restoranapp.dto.StatDTO;
import com.cosinus.restoranapp.service.history.HistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    public final HistoryService historyService;

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','MODERATOR')")
    @PostMapping("/stat")
    public HttpEntity<ApiResult<HistoryResponseDTO>> stat(@Valid @RequestBody StatDTO statDTO){
        return ResponseEntity.ok(historyService.sata(statDTO));
    }
}
