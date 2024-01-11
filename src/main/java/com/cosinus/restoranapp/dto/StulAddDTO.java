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
public class StulAddDTO {

    @NotNull
    Integer tableId;

    @Min(value = 1,message = "iltimos stullar sonini tug'ri kiriting")
    Integer count;
}
