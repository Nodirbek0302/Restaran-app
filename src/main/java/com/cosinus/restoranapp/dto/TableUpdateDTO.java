package com.cosinus.restoranapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableUpdateDTO {

    @NotBlank
    String tableName;

    List<Integer> stulIds;

}
