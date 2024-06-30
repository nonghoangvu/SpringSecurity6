package com.vunh.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @Column(name = "Username", nullable = false, length = 20)
    private String username;

    @Nationalized
    @Column(name = "Password", nullable = false, length = 100)
    private String password;

    @Nationalized
    @Column(name = "Fullname", nullable = false, length = 50)
    private String fullname;

    @Nationalized
    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "username", fetch = FetchType.EAGER)
    private List<EmployeeRole> employeeRoles;

}