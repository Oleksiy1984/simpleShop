package com.alex.dal;


import com.alex.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    List<Laptop> findAllByOrderByIdAsc();

    @Query("FROM Laptop where status = ?1 ORDER BY id ASC")
    List<Laptop> findAll(boolean status);
}
