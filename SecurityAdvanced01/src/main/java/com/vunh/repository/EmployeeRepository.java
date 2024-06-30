package com.vunh.repository;

import com.vunh.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("FROM Employee WHERE username = :username")
    Optional<Employee> finByUsername(@Param("username") String username);
}
