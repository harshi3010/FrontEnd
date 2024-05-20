package com.love2code.cruddemo.dao;

import com.love2code.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student theStudent);

    Student findbyId(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String theLastName);


}
