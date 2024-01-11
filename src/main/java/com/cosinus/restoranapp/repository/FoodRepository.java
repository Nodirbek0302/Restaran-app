package com.cosinus.restoranapp.repository;

import com.cosinus.restoranapp.model.Food;
import com.cosinus.restoranapp.model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
}
