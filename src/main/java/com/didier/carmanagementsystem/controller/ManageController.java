package com.didier.carmanagementsystem.controller;

import com.didier.carmanagementsystem.model.Car;
import com.didier.carmanagementsystem.model.Category;
import com.didier.carmanagementsystem.model.Employee;
import com.didier.carmanagementsystem.model.User;
import com.didier.carmanagementsystem.service.CarService;
import com.didier.carmanagementsystem.service.CategoryService;
import com.didier.carmanagementsystem.service.EmployeeService;
import com.didier.carmanagementsystem.service.sec.CustomerUserDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
            model.addAttribute("user", userDetails);

        return "manage/index";
    }

    @GetMapping("/category")
    public String category(Category category,Model model,Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        String status = "Active";

        model.addAttribute("category", categoryService.getCategoryByStatus(status));
    return "manage/category";
    }
    @GetMapping("/salePerson")
    public String saleParson(Employee category, Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        String position = "Salesperson";

        model.addAttribute("salesperson",employeeService.getEmployeesByPosition(position));
        return "manage/salePerson";
    }

    @PostMapping("/category/save-category")
    public String saveCategory(@ModelAttribute Category category,Model model){
        categoryService.saveCategory(category);
        return "redirect:/manage/category";
    }

    @PostMapping("/category/update-category")
    public String updateCategory(@ModelAttribute Category category,Model model){
        categoryService.updateCategory(category);
        return "redirect:/manage/category";
    }

    @GetMapping("/car")
    public String car(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);


        CustomerUserDetails userDetailss = (CustomerUserDetails) userDetailsService.loadUserByUsername(principal.getName());

        User user = userDetailss.getUser();

        model.addAttribute("userId", user);


        String status = "Active";
        model.addAttribute("category", categoryService.getCategoryByStatus(status));


        model.addAttribute("cars",carService.getAllCars());
    return "manage/cars";
    }

    @Autowired
    private CarService carService;



    @PostMapping("/car/save-car")
    public String saveCar(
            @RequestParam("category") Category category,
            @RequestParam("user") User user,
            @RequestParam("brand") String brand,
            @RequestParam("model") String model,
            @RequestParam("type") String type,
            @RequestParam("year") int year,
            @RequestParam("price") int price,
            @RequestParam("quantity") int quantity,
            @RequestParam("totalPrice") double totalPrice,
            @RequestParam("status") String status,
            @RequestParam("carImage") MultipartFile carImage) {

        // Create a new Car object
        Car car = new Car();

        // Manually set each field
        car.setCategory(category);
        car.setUser(user);
        car.setBrand(brand);
        car.setModel(model);
        car.setType(type);
        car.setYear(year);
        car.setPrice(price);
        car.setQuantity(quantity);
        car.setTotalPrice(totalPrice);
        car.setStatus(status);

        // Check if an image is uploaded
        if (!carImage.isEmpty()) {
            // Set the carImage field to the file's original filename
            car.setCarImage(carImage.getOriginalFilename());

            // Save the car object to the database
            Car savedCar = carService.save(car);

            if (savedCar != null) {
                try {
                    // Define the path where the file will be saved
                    File saveFile = new ClassPathResource("static/img").getFile();
                    Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + carImage.getOriginalFilename());
                    System.out.println(path);
                    // Save the uploaded file to the defined location
                    Files.copy(carImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    e.printStackTrace();
                }




            }
        } else {
            // Handle the case where no file is uploaded
            car.setCarImage("default.jpg"); // Or handle it as needed
            carService.save(car);
        }

        return "redirect:/manage/car";
    }


    @PostMapping("/car/update-car")
    public String updateCar(
            @RequestParam("carId") Long carId,
            @RequestParam("category") Category category,
            @RequestParam("user") User user,
            @RequestParam("brand") String brand,
            @RequestParam("model") String model,
            @RequestParam("type") String type,
            @RequestParam("year") int year,
            @RequestParam("price") int price,
            @RequestParam("quantity") int quantity,
            @RequestParam("totalPrice") double totalPrice,
            @RequestParam("status") String status,
            @RequestParam("carImage") MultipartFile carImage) {

        Optional<Car> optionalCar = carService.getCarById(carId);
        if (!optionalCar.isPresent()) {
            return "redirect:/manage/car"; // No feedback or message
        }

        Car car = optionalCar.get();
        car.setCategory(category);
        car.setUser(user);

        car.setBrand(brand);
        car.setModel(model);
        car.setType(type);
        car.setYear(year);
        car.setPrice(price);
        car.setQuantity(quantity);
        car.setTotalPrice(totalPrice);
        car.setStatus(status);

        if (!carImage.isEmpty()) {
            car.setCarImage(carImage.getOriginalFilename());
            try {
                File saveFile = new ClassPathResource("static/img").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + carImage.getOriginalFilename());
                Files.copy(carImage.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                e.printStackTrace();
                // Handle the error (e.g., log it)
            }
        }

        carService.updateCars(car);

        return "redirect:/manage/car"; // Redirect without feedback
    }






}
