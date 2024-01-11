package com.cosinus.restoranapp.model;

import com.cosinus.restoranapp.enums.TableState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotNull
    @Column(nullable = false)
    String tableName;

    @OneToMany(mappedBy = "table",cascade = CascadeType.ALL)
    List<Stul> stulList;

    @Enumerated(EnumType.STRING)
    TableState state;
}
