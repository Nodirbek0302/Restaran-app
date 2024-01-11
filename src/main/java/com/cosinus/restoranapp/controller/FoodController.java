package com.cosinus.restoranapp.controller;

import com.cosinus.restoranapp.dto.*;
import com.cosinus.restoranapp.service.food.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/all")
    public HttpEntity<ApiResult<List<FoodResponseDTO>>> list(){
        return ResponseEntity.ok(foodService.list());
    }

    @GetMapping("/{id}")
    public HttpEntity<ApiResult<FoodResponseDTO>> byId(@PathVariable Integer id){
        return ResponseEntity.ok(foodService.byId(id));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','MODERATOR')")
    @PostMapping("/add")
    public HttpEntity<ApiResult<FoodResponseDTO>> add(@Valid @RequestBody FoodAddDTO foodAddDTO){
        return ResponseEntity.ok(foodService.add(foodAddDTO));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','MODERATOR')")
    @PutMapping("/update")
    public HttpEntity<ApiResult<FoodResponseDTO>> update(@RequestParam Integer id, @Valid @RequestBody FoodAddDTO foodAddDTO){
        return ResponseEntity.ok(foodService.update(id,foodAddDTO));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','MODERATOR')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HttpEntity<ApiResult<Boolean>> delete(@PathVariable Integer id){
        return ResponseEntity.ok(foodService.delete(id));
    }

    @PostMapping("/order")
    public HttpEntity<ApiResult<Boolean>> orderFood(@Valid @RequestBody List<OrderFoodDTO> orderFood){
        return ResponseEntity.ok(foodService.order(orderFood));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','MODERATOR','AFITSANT')")
    @PostMapping("/calc")
    public HttpEntity<ApiResult<CalcResponseDTO>> calculation(@RequestParam Integer tableId){
        return ResponseEntity.ok(foodService.calculation(tableId));
    }
    @PreAuthorize(value = "hasAnyAuthority('ADMIN','MODERATOR')")
    @PostMapping("/discount")
    public HttpEntity<ApiResult<Boolean>> setDiscount(@Valid @RequestBody SetDiscountDTO setDiscountDTO){
        return ResponseEntity.ok(foodService.discount(setDiscountDTO));
    }






}
