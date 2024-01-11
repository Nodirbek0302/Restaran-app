package com.cosinus.restoranapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BronTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Min(value = 1, message = "stullar sonini tug'ri kiriting")
    Integer stulNumber;

    @NotNull
    @Column(nullable = false)
    Integer tablesId;

    @NotNull
    @Column(nullable = false)
    Integer userId;

    @Column(nullable = false)
    @NotNull
    LocalDateTime startBronTime;

    LocalDateTime endBronTime;
}
