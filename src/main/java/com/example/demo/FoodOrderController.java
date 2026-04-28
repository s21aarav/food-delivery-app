package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class FoodOrderController {
    
    @Autowired
    private OrderRepository repository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("orders", repository.findAll());
        return "index"; 
    }

    @PostMapping("/add-order")
    public String addOrder(@ModelAttribute FoodOrder order) {
        repository.forceInsert(
            order.getOrderId(), 
            order.getCustomerName(), 
            order.getRestaurant(),
            order.getAmount(),
            order.getStatus()
        );
        return "redirect:/"; 
    }
    @GetMapping("/sort")
    public String sortOrders(Model model) {
        model.addAttribute("orders", repository.findAllSortedByAmount());
        return "index";
    }

    @GetMapping("/premium")
    public String premiumOrders(Model model) {
        model.addAttribute("orders", repository.findPremiumOrders());
        return "index";
    }

    @GetMapping("/sort-id")
    public String sortById(Model model) {
        model.addAttribute("orders", repository.findAllSortedById());
        return "index";
    }

    // We use PostMapping for deleting because it changes data (safer than GetMapping)
    @PostMapping("/clear-all")
    public String clearAll() {
        repository.deleteAll();
        return "redirect:/";
    }
}