package com.alex.laptop;

import com.alex.config.BlConfiguration;
import com.alex.dal.LaptopRepository;
import com.alex.entity.Laptop;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BlConfiguration.class)
@ActiveProfiles("test")
@Transactional
public class TestLaptopService {

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private LaptopRepository laptopRepository;

    @Test
    public void testFindAllAvailable() {
        Assert.assertEquals(3, laptopService.findAllAvailable().size());
    }

    @Test
    public void testBuyLaptop() {
        laptopService.buyLaptop(2L);
        Laptop laptop = laptopRepository.findOne(2L);
        Assert.assertEquals(5, laptop.getQuantity().intValue());
    }

    @Test
    public void testAddLaptop() {
        laptopService.addLaptop(1L);
        Laptop laptop = laptopRepository.findOne(1L);
        Assert.assertEquals(1, laptop.getQuantity().intValue());
    }

}