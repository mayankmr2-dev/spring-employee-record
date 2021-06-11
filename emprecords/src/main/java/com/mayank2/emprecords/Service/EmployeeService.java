package com.mayank2.emprecords.Service;

import com.mayank2.emprecords.Model.Employee;
import com.mayank2.emprecords.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployees(){
        return this.employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){
        return this.employeeRepository.findById(id).orElse(null);
    }

    public Employee addEmployee(Employee employee){
        Optional<Employee> emp = Optional.ofNullable(this.employeeRepository.findByEmailId(employee.getEmail()));
        if(emp.isPresent()){
            throw new IllegalStateException("employee id already exists.");
        }
        return this.employeeRepository.save(employee);
    }

    public Employee updateEmployeeById(Employee employee,Long id){
        Employee emp = this.employeeRepository.findById(id).orElse(null);
        emp.setFirst(employee.getFirst());
        emp.setSecond(employee.getSecond());
        emp.setEmail(employee.getEmail());
        emp.setJobTitle(employee.getJobTitle());
        return this.employeeRepository.save(emp);
    }

    public void deleteEmployeeById(Long id){
        boolean exists = this.employeeRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("employee does not exist.");
        }
        this.employeeRepository.deleteById(id);
    }

}
