package com.cosinus.restoranapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAddDTO {
    @NotBlank
    String name;

    @NotBlank
    @Email
    String email;

    @NotBlank
    String oldPassword;

    @NotBlank
    String password;
}
