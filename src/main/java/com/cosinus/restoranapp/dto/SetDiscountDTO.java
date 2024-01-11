package com.cosinus.restoranapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SetDiscountDTO {
    @NotNull
    Integer foodId;

    @NotNull
    @Min(value = 0,message = "iltimos chegirma foizini musbat sonda kiriting")
    Integer setDiscount;
}
