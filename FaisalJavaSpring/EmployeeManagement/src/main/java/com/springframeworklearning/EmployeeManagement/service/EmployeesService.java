package com.springframeworklearning.EmployeeManagement.service;

import com.springframeworklearning.EmployeeManagement.entity.Employee;
import com.springframeworklearning.EmployeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Business service - singleton - spring create a instance of this class and register it.
@Service
public class EmployeesService {

    List<Employee> employeeList = new ArrayList<>(Arrays.asList(
            new Employee(1,"Krishna","Mumbai"),
            new Employee(2,"Gopi","Benguluru")
    ));

    //to save java object into database - Data Access Layer Path
    @Autowired
    EmployeeRepository employeeRepository;

    //why we used arrayList because it is mutable and we can expand them as much as possible.. also delete an element inside them

    //getEmployee
    public List<Employee> getAllEmployees(){
      return employeeList;
    }

    //getEmployee by 1
    public Employee getAnEmployee(int id){
        return employeeList.stream().filter(e -> (
                e.getEmployeeId() == id)).findFirst().get();
    }

    //post employee - create something on server / create employee in database or post data
    public void createEmployee(Employee employee){
        //employeeList.add(employee);
        employeeRepository.save(employee);
    }

    //put request - for updating employee
    public void updateEmployee(Employee employee) {
        /*
        List<Employee> tempEmployee = new ArrayList<>();
        //iterate over existing arrayList to see parameter employee id is equal to id present in existing arraylist - if it matches .. update that element
        for (Employee emp : employeeList) {
            if (emp.getEmployeeId() == employee.getEmployeeId()) {
                //if employee matches the employee which we want to update then replace the changes
                emp.setEmployeeName(employee.getEmployeeName());
                emp.setEmployeeCity(employee.getEmployeeCity());
            }
            tempEmployee.add(emp); //after update add the changes into our temporary list
        }
        this.employeeList = tempEmployee;//replace the list with updated information of list to the current list (employeeList)

*/
        employeeRepository.save(employee);
    }
        //delete an employee
    public void deleteEmployee(int id){
        List<Employee> tempEmptodelete = new ArrayList<>();
        for(Employee emp : employeeList){
            if(emp.getEmployeeId() == id){
                //logic behind this is - if our existing list have same id which we want to delete then it will not add to temp and temp list will get all the existing list element without the one which we want to delete
                continue;
            }
            tempEmptodelete.add(emp);
        }
        //then it will copied into existing list...
        this.employeeList = tempEmptodelete;
    }


}
