package com.vunh.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "EmployeeRoles")
public class EmployeeRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Username", nullable = false)
    private Employee username;

    @ManyToOne
    @JoinColumn(name = "RoleId", nullable = false)
    private Role role;

}