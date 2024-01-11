package com.cosinus.restoranapp.repository;

import com.cosinus.restoranapp.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface HistoryRepository extends JpaRepository<History,Integer> {

    @Query(nativeQuery = true , value = "select h.* from history h where date(h.history_time) <= :time1 or date(h.history_time) <= :time2")
    List<History> findByHistoryTimeAndIdBetween(LocalDate time1,LocalDate time2);
}
