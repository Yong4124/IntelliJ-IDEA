package com.du.reservation0918.controller;


import com.du.reservation0918.model.MyService;
import com.du.reservation0918.service.MyServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/services")
public class MyServiceController {
    @Autowired
    private MyServiceService serviceService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("services", serviceService.getAllServices());
        return "service_list";
    }

    @GetMapping("/new")
    public String createForm() {
        return "service_form";
    }

    @PostMapping
    public String create(MyService service) {
        serviceService.addService(service);
        return "redirect:/services";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        serviceService.deleteService(id);
        return "redirect:/services";
    }
}

