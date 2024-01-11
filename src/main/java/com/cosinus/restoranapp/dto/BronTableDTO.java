package com.cosinus.restoranapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BronTableDTO {

    @NotNull
    @Min(value = 1, message = "Eng kamida bitta odamga joy buyurtirishingiz mumkin")
    Integer stulNumber;

    @NotNull
    @Min(value = 1, message = "Iltmos mavjud stollarni tanlashingiz surab qolamiz")
    Integer tableNumber;

    @NotNull
    LocalDateTime startBronTime;

    @NotNull
    LocalDateTime endBronTime;
}
