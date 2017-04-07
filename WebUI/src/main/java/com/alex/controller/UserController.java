package com.alex.controller;


import com.alex.entity.Laptop;
import com.alex.entity.View;
import com.alex.laptop.LaptopService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")

public class UserController {

    private LaptopService service;

    @Autowired
    public UserController(LaptopService service) {
        this.service=service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model) {
        model.addAttribute("page", service.findAllAvailable());
        return "index";
    }

    @RequestMapping(value="/buy/{item.id}")
    public String buyLaptop(Model model, @PathVariable("item.id") Long id){
        service.buyLaptop(id);
        model.addAttribute("page", service.findAllAvailable());
        return "index";
    }

    @JsonView(View.Summary.class)
    @RequestMapping(value="/buy",method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Laptop buyLaptopAjax(@RequestBody Laptop laptop) {
        service.buyLaptop(laptop.getId());
        return service.findLaptop(laptop.getId());
    }

    @JsonView(View.Summary.class)
    @RequestMapping(value="/add",method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Laptop addLaptopAjax(@RequestBody Laptop laptop) {
        service.addLaptop(laptop.getId());
        return service.findLaptop(laptop.getId());
    }
}
