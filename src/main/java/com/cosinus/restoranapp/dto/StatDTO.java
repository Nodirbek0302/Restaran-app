package com.cosinus.restoranapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatDTO {

    @NotNull
    LocalDate startDate;

    @NotNull
    LocalDate endDate;
}
