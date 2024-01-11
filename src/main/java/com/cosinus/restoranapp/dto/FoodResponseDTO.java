package com.cosinus.restoranapp.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodResponseDTO {
    Integer id;
    String foodName;
    Double price;
    Integer discount;
}
