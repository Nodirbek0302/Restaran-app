package com.cosinus.restoranapp.dto;

import com.cosinus.restoranapp.model.History;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HistoryResponseDTO {

    List<History> historyList;

    Double totalPrice;
}
