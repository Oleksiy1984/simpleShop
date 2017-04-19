package com.alex.dal;

import com.alex.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    List<Laptop> findAllByOrderByIdAsc();
    List<Laptop> findAllAvailable(boolean status);
}
