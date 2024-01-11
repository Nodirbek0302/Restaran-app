package com.cosinus.restoranapp.repository;

import com.cosinus.restoranapp.model.Tables;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<Tables, Integer> {

    Optional<Tables> findByTableName(@NotNull String tableName);
}
