package com.springframeworklearning.EmployeeManagement.repository;

import com.springframeworklearning.EmployeeManagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    /* Operation
    C-Creating
    R-Read
    U-Update
    D-Delete
    -getAllEmployee()
    -getANEmployee(id);
    -updateAnEmployee(Employee employee)
    -deleteAnEmployee(id)

    -Create a interface
    and extend JPARepository class
    -need a dependency to add in pom.xml
    which is
 */


}