package com.cosinus.restoranapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer Id;

    @NotNull
    @Column(nullable = false)
    Integer tableId;

    @NotNull
    @Column(nullable = false)
    Integer customerId;

    @NotNull
    @Column(nullable = false)
    Integer stulNumber;

    @NotNull
    @Column(nullable = false)
    Double totalPrice;

    @OneToMany
    List<Food> foodList;

    LocalDateTime historyTime;

}
