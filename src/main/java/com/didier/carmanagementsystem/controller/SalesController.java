package com.didier.carmanagementsystem.controller;

import com.didier.carmanagementsystem.model.Category;
import com.didier.carmanagementsystem.model.Customer;
import com.didier.carmanagementsystem.model.Sale;
import com.didier.carmanagementsystem.model.User;
import com.didier.carmanagementsystem.service.CarService;
import com.didier.carmanagementsystem.service.CategoryService;
import com.didier.carmanagementsystem.service.CustomerService;
import com.didier.carmanagementsystem.service.SaleService;
import com.didier.carmanagementsystem.service.sec.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/salesperson")
public class SalesController {



    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CustomerService customerService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CarService carService;

    @Autowired
    SaleService saleService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        CustomerUserDetails userDetailss = (CustomerUserDetails) userDetailsService.loadUserByUsername(principal.getName());

        User user = userDetailss.getUser();




        model.addAttribute("salesToday", saleService.getSalesByDayForUser(user.getUserId()));
        model.addAttribute("salesWeek", saleService.getSalesByWeekForUser(user.getUserId()));
        model.addAttribute("salesMonth", saleService.getSalesByMonthForUser(user.getUserId()));
        model.addAttribute("salesYear", saleService.getSalesByYearForUser(user.getUserId()));

        return "salesperson/index";
    }
    @GetMapping("/customer")
    public String customer(Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        String status = "Active";

        model.addAttribute("customer", customerService.getCustomerByStatus(status));



        return "salesperson/customer";
    }

    @PostMapping("/customer/customer-save")
    public String customerSave(@ModelAttribute Customer customer) {

        customerService.save(customer);
        return "redirect:/salesperson/customer";

    }

    @GetMapping("/salesCarPage")
    public String car(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        String status = "Active";
        model.addAttribute("category", categoryService.getCategoryByStatus(status));


        model.addAttribute("cars",carService.getAllCars());
        return "salesperson/salesPage";
    }

    @GetMapping("/salesPage")
    public String salesPage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        CustomerUserDetails userDetailss = (CustomerUserDetails) userDetailsService.loadUserByUsername(principal.getName());

        User user = userDetailss.getUser();

        model.addAttribute("salesUser", saleService.getSalesByUserId(user.getUserId()));

        return "salesperson/salesPagesss";




    }

    @GetMapping("/selling/{car_id}")
    public String sell(@PathVariable("car_id") Long car_id ,Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        CustomerUserDetails userDetailss = (CustomerUserDetails) userDetailsService.loadUserByUsername(principal.getName());

        User user = userDetailss.getUser();

        model.addAttribute("userId", user);

        String status = "Active";

        model.addAttribute("car",carService.getById(car_id));


        model.addAttribute("customer", customerService.getCustomerByStatus(status));
        return "salesperson/selling";
    }

    @PostMapping("/save")
    public String saveSale(@ModelAttribute Sale sale, Model model) {
        Sale savedSale = saleService.save(sale);
        return "redirect:/salesperson/bill/" +  savedSale.getSaleId(); // Redirect to the sales list page or confirmation page
    }

    @GetMapping("/bill/{id}")
    public String generateBill(@PathVariable Long id, Model model) {
        Sale sale = saleService.getSale(id); // Create a method to fetch sale by ID
        model.addAttribute("sale", sale);
        return "salesperson/bill"; // View for displaying the bill
    }



}
