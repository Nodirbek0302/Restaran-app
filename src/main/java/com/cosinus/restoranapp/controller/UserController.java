package com.cosinus.restoranapp.controller;

import com.cosinus.restoranapp.dto.ApiResult;
import com.cosinus.restoranapp.dto.UserAddDTO;
import com.cosinus.restoranapp.dto.UserResponseDTO;
import com.cosinus.restoranapp.model.Users;
import com.cosinus.restoranapp.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','MODERATOR')")
    @GetMapping("/list")
    public HttpEntity<ApiResult<List<Users>>> list() {
       return ResponseEntity.ok(userService.list());
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','MODERATOR')")
    @GetMapping("/{id}")
    public HttpEntity<ApiResult<UserResponseDTO>> findById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.byId(id));
    }

    @PutMapping("/update")
    public HttpEntity<ApiResult<Boolean>> update(@RequestParam Integer id,@RequestBody UserAddDTO userAddDTO){
        return ResponseEntity.ok(userService.update(id,userAddDTO));
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','MODERATOR')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public HttpEntity<ApiResult<Boolean>> delete(@PathVariable Integer id){
        return ResponseEntity.ok(userService.delete(id));
    }


}
