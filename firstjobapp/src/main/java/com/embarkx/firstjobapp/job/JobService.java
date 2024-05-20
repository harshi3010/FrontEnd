package com.embarkx.firstjobapp.job;

import java.util.List;

public interface JobService {

    //methods needs to define
    List<Job> findAll();
    void createJob(Job job);

    Job getJobById(Long id);
}
