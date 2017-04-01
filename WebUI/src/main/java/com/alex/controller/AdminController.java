package com.alex.controller;
import com.alex.config.FileBucket;
import com.alex.entity.Laptop;
import com.alex.entity.View;
import com.alex.laptop.LaptopService;
import com.alex.laptop.ManufacturerService;
import com.alex.validator.FileValidator;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes("laptop")
public class AdminController {

    @Autowired
    private LaptopService service;

    @Autowired
    private ManufacturerService serviceMan;

    @Autowired
    private Validator fileValidator;

    private static String UPLOAD_LOCATION="C:/images/";

    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }


    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model) throws IOException {
        model.addAttribute("page", service.findAll());
        return "admin";
    }

    @RequestMapping(value="/buy/{item.id}")
    public String buyLaptop(@PathVariable("item.id") Long id){
        service.buyLaptop(id);
        return "redirect:/admin";
    }

    @RequestMapping(value="/add/{item.id}")
    public String addLaptop(@PathVariable("item.id") Long id){
        service.addLaptop(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("laptop",new Laptop());
        model.addAttribute("typeOptions",getTypes());
        model.addAttribute("status",getStatus());
        return "laptop_add";
    }

    @RequestMapping( value="/upload", method = RequestMethod.POST)
    public String fileUpload(@ModelAttribute @Valid FileBucket fileBucket, BindingResult result) throws IOException {

        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "uploader";
        } else {
            MultipartFile multipartFile = fileBucket.getFile();

            // Creating the directory to store file
            File dir = new File(UPLOAD_LOCATION);
            if (!dir.exists())
                dir.mkdirs();

            // Create the file on server
            FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
            String fileName = "/images/" + multipartFile.getOriginalFilename();
            Laptop laptop = service.findLaptop(fileBucket.getId());
            File fileToDelete = new File(UPLOAD_LOCATION.substring(0,2) + laptop.getImage());
            fileToDelete.delete();
            laptop.setImage(fileName);
            service.saveLaptop(laptop);
            return "redirect:/admin";
        }
    }
    @RequestMapping(value="/upload/{item.id}", method = RequestMethod.GET)
    public String getSingleUploadPage(@PathVariable("item.id") Long id,ModelMap model) {
        FileBucket fileModel = new FileBucket();
        fileModel.setId(id);
        model.addAttribute("fileBucket", fileModel);
        return "uploader";
    }
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String save(@ModelAttribute @Valid Laptop laptop,BindingResult bindingResult,
                       SessionStatus status,Model model) {

        if (!bindingResult.hasErrors()) {
            String nameManufacturer = laptop.getManufacturer().getName();
            laptop.getManufacturer().setId(serviceMan.findByName(nameManufacturer).getId());
            service.saveLaptop(laptop);
            status.setComplete();
            return "redirect:/admin";
        } else {
            if (laptop.getId() == null) {
                return "laptop_add";
            } else {
                model.addAttribute("laptop", laptop);
                return "edit";
            }

        }
    }


    @RequestMapping(value = "/delete/{item.id}", method = RequestMethod.GET)
    public String delete(@PathVariable("item.id") Long id) {
        service.deleteLaptop(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/edit/{item.id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("item.id") Long id) {
        Laptop laptop=service.findLaptop(id);
        model.addAttribute("laptop",laptop);
        model.addAttribute("typeOptions",getTypes());
        model.addAttribute("status",getStatus());
        return "edit";
    }

    private List<String> getTypes(){
        return serviceMan.findAll();
    }

    private List<String> getStatus(){
        List<String> list =new LinkedList<>();
        list.add("false");
        list.add("true");
        return list;
    }

    @JsonView(View.Summary.class)
    @RequestMapping(value="/buy",method=RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    public @ResponseBody Laptop buyLaptopAjax(@RequestBody Long id) {
        service.buyLaptop(id);
        return service.findLaptop(id);
    }

    @JsonView(View.Summary.class)
    @RequestMapping(value="/add",method=RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    public @ResponseBody Laptop addLaptopAjax(@RequestBody Long id) {
        service.addLaptop(id);
        return service.findLaptop(id);
    }

    @JsonView(View.Summary.class)
    @RequestMapping("/test")
    @ResponseBody
    public Laptop findResource(){
        if(true){
            throw new RuntimeException("There was an error");
        }
        return service.findLaptop(18L);
    }

}