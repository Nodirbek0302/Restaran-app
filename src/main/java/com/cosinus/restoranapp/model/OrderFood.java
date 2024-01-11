package com.cosinus.restoranapp.model;

import com.cosinus.restoranapp.enums.FoodState;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    Integer tableId;

    @Column(nullable = false)
    Integer foodId;

    @Column(nullable = false)
    Integer count;

    @Enumerated(EnumType.STRING)
    FoodState state;
}
