package com.springframeworklearning.EmployeeManagement.entity;
import jakarta.persistence.*;

@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto generated
    @Column
    int employeeId;

    @Column
    String employeeName;

    @Column
    String employeeCity;

    @OneToOne
    @JoinColumn(name = "fk_spouse")
    private Spouse spouse;

    public Employee(){

    }

    //constructor
    public Employee(int employeeId,String employeeName, String employeeCity) {
        this.employeeId  = employeeId;
        this.employeeName = employeeName;
        this.employeeCity = employeeCity;
    }

    //getter and setter

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeCity() {
        return employeeCity;
    }

    public void setEmployeeCity(String employeeCity) {
        this.employeeCity = employeeCity;
    }

    //toString
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeCity='" + employeeCity + '\'' +
                '}';
    }

    public Employee(Spouse spouse) {
        this.spouse = spouse;
    }
}
