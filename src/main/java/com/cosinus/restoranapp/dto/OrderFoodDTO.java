package com.cosinus.restoranapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderFoodDTO {
    @NotNull
    Integer tableId;

    @NotNull
    Integer foodId;

    @NotNull
    @Min(value = 1, message = "Eng kamidad bitta buyurtma qilishingiz kerak")
    Integer count;
}
