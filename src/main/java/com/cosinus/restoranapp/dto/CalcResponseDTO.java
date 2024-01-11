package com.cosinus.restoranapp.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CalcResponseDTO {
    List<FoodPriceDTO> priceDTOList;
    Double totalPrice;
    Integer count;
}
