package com.embark.JobApplication.company;

import com.embark.JobApplication.job.Job;
import com.embark.JobApplication.review.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    //every company has list of jobs
    @JsonIgnore //this annotation is used because we have made one to many and many to one, so while passing a request it is going in recursive loop
    @OneToMany(mappedBy = "company")   //this mappedBy parameter is used to not create seperate table with company_id and job_id, it will link to company defined as @ManyToOne in Job class company initiation
    private List<Job> jobs;

    //--------------------------------for One company has Many Jobs
    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
    //---------------------------------

    //one company has many reviews
    @OneToMany(mappedBy = "company")
    private List<Review> reviews;

    //---------------------------for One Company has Many Reviews
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    //--------------------
    //for jpa  to instantiate
    public Company() {
    }

    public Company(Long id, String name, String description, List<Job> jobs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.jobs = jobs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
