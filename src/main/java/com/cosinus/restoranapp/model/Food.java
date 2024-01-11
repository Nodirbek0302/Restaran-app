package com.cosinus.restoranapp.model;

import com.cosinus.restoranapp.enums.FoodState;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotBlank
    @Column(nullable = false)
    String name;

    @Min(value = 0,message = "iltimos qiymatni tug'ri kiriting")
    Double CurrentPrice;

    Integer discount; //foizda food uchun chegirma

}
