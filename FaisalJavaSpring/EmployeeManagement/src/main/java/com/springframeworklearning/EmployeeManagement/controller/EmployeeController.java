package com.springframeworklearning.EmployeeManagement.controller;

import com.springframeworklearning.EmployeeManagement.entity.Employee;
import com.springframeworklearning.EmployeeManagement.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

//@Controller
//@ResponseBody
@RestController
public class EmployeeController {

    @Autowired
    EmployeesService employeesService;

    //GET ALL EMPLOYEE
    //@RequestMapping(value = "/employees",method = RequestMethod.GET)   //by default = it is taking get
    @GetMapping("/employees")
    public List<Employee> findAllEmployee(){
        return employeesService.getAllEmployees();
    }

    //GET EMPLOYEE BY ID
    //@RequestMapping("/employees/{id}")
    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id){    //above id will be known by below method
        return employeesService.getAnEmployee(id);
    }

    //POST
    //@RequestMapping(value = "/employees",method = RequestMethod.POST)
    @PostMapping("/employees")
    public void createEmployee(@RequestBody Employee employee){
        employeesService.createEmployee(employee);
    }

    //PUT
    //PathVariable = it will get through request URL.
    //@RequestBody  = it will get through raw data in json format from POSTMAN.
    //@RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    @PutMapping("employees/{id}")
    public void updateAnEmployee(@PathVariable int id,@RequestBody Employee employee){
        employeesService.updateEmployee(employee);
    }

    //DELETE
    //@RequestMapping(value = "/employees/{id}",method = RequestMethod.DELETE)
    @DeleteMapping("employees/{id}")
    public List<Employee> deleteAnEmployee(@PathVariable int id){
        employeesService.deleteEmployee(id);
        return employeesService.getAllEmployees();
    }

}
