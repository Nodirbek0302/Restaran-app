package com.cosinus.restoranapp.repository;

import com.cosinus.restoranapp.model.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface OrderFoodRepository extends JpaRepository<OrderFood,Integer> {

    List<OrderFood> findByTableId(Integer tableId);
}
