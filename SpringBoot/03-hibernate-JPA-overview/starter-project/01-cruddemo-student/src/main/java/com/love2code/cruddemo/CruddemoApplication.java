
package com.love2code.cruddemo;

import com.love2code.cruddemo.dao.StudentDAO;
import com.love2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    //executed after the spring beans have been loaded..
    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            //createStudent(studentDAO);
            //createMultipleStudent(studentDAO);
      		//readStudent(studentDAO);
            //queryForStudent(studentDAO);
            queryForStudentByLastName(studentDAO);
        };//Lambda Expression. -- enhanced(Your custom code)
    }

    private void queryForStudentByLastName(StudentDAO studentDAO) {
        //get a list of students
        List<Student> theStudent = studentDAO.findByLastName("jain");

        //display list of students
        for(Student listOfStudentBylastName: theStudent){
            System.out.println(listOfStudentBylastName);
        }

    }

    private void queryForStudent(StudentDAO studentDAO) {
        //get a list of students
        List<Student> theStudent = studentDAO.findAll();

        //display list of students
    for (Student tempStudent : theStudent){
        System.out.println(tempStudent);
    }

    }

    private void readStudent(StudentDAO studentDAO) {

        //create a student object
        System.out.println("Creating new student object...");

        Student tempstudent = new Student("john", "roe", "johnroe@gmail.com");

        //save the student -- saving the java object to database
        System.out.println("Saving the student ...");
        studentDAO.save(tempstudent);

        //display id of the saved student
        int theId = tempstudent.getId();
        System.out.println("Saved student .Generated id: " + theId);

        //retrieve student based on the id - primary key  -- fetching from databases..
        System.out.println("Retrieving student with the id: " + theId);
        Student myStudent = studentDAO.findbyId(theId);

        //display student
        System.out.println("Fount the student : " + myStudent);
    }

    private void createMultipleStudent(StudentDAO studentDAO) {
        //create multiple student
        System.out.println("Creating multiple student (3) object...");

        //save the student objects
        Student tempStudent1 = new Student("Suyash", "jain", "suyashjain@gmail.com");
        Student tempStudent2 = new Student("chirag", "jain", "chiragjain@gmail.com");
        Student tempStudent3 = new Student("ansh", "jain", "anshjain@gmail.com");

        //save the student object
        System.out.println("Saving the students...");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);

        //displaying student details


    }
}
/*
	private void createStudent(StudentDAO studentDAO) {
	//StudentDAO studentDAO = passing the studentDAOImp int studentDAO object

		// create the student object

		//System.out.println("Creating new student object...");
		Student tempStudent = new Student("harshi","jain","harshijain@gmail.com");
		Student tempStudent1 = new Student("harshada","jain","harshadajain@gmail.com");

		//save the student object

		//studentDAO -- is a helper /dependencies with functionality
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);
		studentDAO.save(tempStudent1);

		//display id  of the saved student
		System.out.println("Saved student . Generated id: " + tempStudent.getId());
		System.out.println("Saved student . Generated id: " + tempStudent1.getId());
	}
*/







