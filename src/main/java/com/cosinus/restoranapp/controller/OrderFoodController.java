package com.cosinus.restoranapp.controller;

import com.cosinus.restoranapp.dto.ApiResult;
import com.cosinus.restoranapp.dto.SetStateDTO;
import com.cosinus.restoranapp.model.OrderFood;
import com.cosinus.restoranapp.service.order.OrderFoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("order-food")
@RequiredArgsConstructor
public class OrderFoodController {

    private final OrderFoodService orderFoodService;

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','MODERATOR','AFITSANT')")
    @PostMapping("/set-state")
    public HttpEntity<ApiResult<Boolean>> setState(@Valid @RequestBody SetStateDTO setStateDTO){
        return ResponseEntity.ok(orderFoodService.setState(setStateDTO));
    }


    @PreAuthorize(value = "hasAnyAuthority('ADMIN','MODERATOR','AFITSANT')")
    @GetMapping("/all")
    public HttpEntity<ApiResult<List<OrderFood>>> all(){
        return ResponseEntity.ok(orderFoodService.all());
    }
}
