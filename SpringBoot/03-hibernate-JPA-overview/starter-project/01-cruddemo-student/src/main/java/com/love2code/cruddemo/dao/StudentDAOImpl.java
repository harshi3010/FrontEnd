package com.love2code.cruddemo.dao;

import com.love2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements  StudentDAO{
    @Override
    public List<Student> findByLastName(String theLastName) {
        //create a query
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student where lastName = :theData",Student.class);

        //set query parameter
        //:theData value -- assume this as a placeholder for the query
        theQuery.setParameter("theData",theLastName);

        //return query result
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findAll() {
        //create query
        TypedQuery theStudentQuery = entityManager.createQuery("From Student",Student.class);
        //from Stuent  ---JPA Entity name not a database table name

        //return query results
        return theStudentQuery.getResultList();
    }

    @Override
    public Student findbyId(Integer id) {
        return entityManager.find(Student.class,id);
    }

    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager ){
        this.entityManager = entityManager;
    }

    //implement save method -- saving and storing objects in database
    @Transactional  //using from spring framework , add because we are performing an update
    @Override
    public void save(Student theStudent) {
        entityManager.persist(theStudent); //saves the student in database.
    }
}
