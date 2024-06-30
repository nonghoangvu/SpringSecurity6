package com.vunh.service;

import com.vunh.entity.Employee;
import com.vunh.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = this.employeeRepository.finByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String[] roles = employee.getEmployeeRoles().stream().map(aut -> aut.getRole().getId()).toList().toArray(new String[0]);
//        for (String role : roles) {
//            System.out.println(role);
//        }
        return User.builder()
                .username(employee.getUsername())
                .password(employee.getPassword())
                .roles(roles)
                .build();
    }
}
