package com.alex.laptop;

import com.alex.dal.LaptopRepository;
import com.alex.entity.Laptop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LaptopService {

    @Autowired
    private LaptopRepository repository;


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Laptop> findAllAvailable() {
        return repository.findAllAvailable(true);
    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Laptop> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    public void buyLaptop(Long id) {
        Laptop laptop=repository.findOne(id);
        if(laptop.getQuantity()>0){
            int quantity = laptop.getQuantity();
            laptop.setQuantity(quantity - 1);
            if(quantity==1){
                laptop.setStatus(false);
            }
        }
    }

    public void addLaptop(Long id) {
        Laptop laptop = repository.findOne(id);
        if (!laptop.isStatus()) {
            laptop.setStatus(true);
        }
        int quantity = laptop.getQuantity();
        laptop.setQuantity(quantity + 1);
    }

    public Laptop saveLaptop(Laptop laptop) {
        Laptop newLaptop = repository.save(laptop);
        return newLaptop;
    }

    public void deleteLaptop(Long id) {
        repository.delete(id);
    }


    public Laptop findLaptop(Long id){
        return repository.findOne(id);
    }

}

