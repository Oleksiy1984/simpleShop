package com.alex.laptop;

import com.alex.dal.ManufacturerRepository;
import com.alex.entity.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository repository;

    public List<String> findAll() {
        List<String> list = new ArrayList<>();
        List<Manufacturer> manufacturers = repository.findAllByOrderByIdAsc();
        for (Manufacturer manufacturer : manufacturers
                ) {
            list.add(manufacturer.getName());
        }
        return list;
    }

    public Manufacturer findByName(String name) {
        return repository.findByName(name);
    }
}
