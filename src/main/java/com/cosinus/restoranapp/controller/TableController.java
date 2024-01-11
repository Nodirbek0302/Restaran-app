package com.cosinus.restoranapp.controller;

import com.cosinus.restoranapp.dto.*;
import com.cosinus.restoranapp.service.table.TableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/table")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping("/list")
    public HttpEntity<ApiResult<List<TableResponseDTO>>> list(){
        return ResponseEntity.ok(tableService.list());
    }

    @PostMapping("add")
    public HttpEntity<ApiResult<TableResponseDTO>> add(@Valid @RequestBody AddTableDTO addTableDTO){
        return ResponseEntity.ok(tableService.add(addTableDTO));
    }

    @GetMapping("/{id}")
    public HttpEntity<ApiResult<TableResponseDTO>> tableFindById(@PathVariable Integer id){
        return ResponseEntity.ok(tableService.findById(id));
    }

    @PutMapping
    public HttpEntity<ApiResult<TableResponseDTO>> update(@RequestParam Integer id,@RequestBody TableUpdateDTO tableUpdateDTO){
        return ResponseEntity.ok(tableService.update(id,tableUpdateDTO));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','MODERATOR')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HttpEntity<ApiResult<Boolean>> delete(@PathVariable Integer id){
        return ResponseEntity.ok(tableService.delete(id));
    }

    @PostMapping("/bron-table")
    public HttpEntity<ApiResult<Boolean>> bronTable(@Valid @RequestBody BronTableDTO bronTableDTO){
      return ResponseEntity.ok(tableService.bronTable(bronTableDTO));
    }
}
