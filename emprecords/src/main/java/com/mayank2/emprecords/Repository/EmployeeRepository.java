package com.mayank2.emprecords.Repository;

import com.mayank2.emprecords.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    public Employee findByEmailId(String email);
}
