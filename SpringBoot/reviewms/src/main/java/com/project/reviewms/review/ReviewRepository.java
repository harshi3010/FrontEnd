package com.project.reviewms.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByCompanyId(Long companyId);  //we don't need to define the implementation of this custom method of
    // finding reviews by Id because this is a interface just declaring overhear we are telling
    // jpa we need implementation of these on runtime --by these declaring spring-data-jpa automatically generates the implementation for these method at runtime
    //how does it know the functionality of these - so it breakdowns the method name
    //findBy   ---finded by
    //CompanyId ---have something in database  -- we need to find all the instances of Review Entity (all the records) by Company ID--company id is field in review --
    //we have already mappped the Company as ManytoOne in Review Class Entity
}
