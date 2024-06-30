package com.vunh.controller;

import com.vunh.entity.Employee;
import com.vunh.entity.EmployeeRole;
import com.vunh.repository.EmployeeRepository;
import com.vunh.repository.EmployeeRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private EmployeeRoleRepository employeeRoleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @ModelAttribute("list")
    public List<EmployeeRole> getEmployeeRoles() {
        return this.employeeRoleRepository.findAll();
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
