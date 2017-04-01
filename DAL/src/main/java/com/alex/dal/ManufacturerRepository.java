package com.alex.dal;

import com.alex.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    List<Manufacturer> findAllByOrderByIdAsc();
    Manufacturer findByName(String name);
}
