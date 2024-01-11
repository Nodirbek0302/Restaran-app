package com.cosinus.restoranapp.repository;

import com.cosinus.restoranapp.model.Stul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StulRepository extends JpaRepository<Stul,Integer> {

}
