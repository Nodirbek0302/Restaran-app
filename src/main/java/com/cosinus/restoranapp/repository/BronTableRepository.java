package com.cosinus.restoranapp.repository;

import com.cosinus.restoranapp.model.BronTable;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BronTableRepository extends JpaRepository<BronTable, Integer> {

    List<BronTable> findByTablesId(@NotNull Integer tablesId);
}
