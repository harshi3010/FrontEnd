
package com.embarkx.firstjobapp.job;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    private JobService jobService;
    @GetMapping("/jobs")
    public List<Job> findAll(){
        return jobService.findAll();
    }

    @PostMapping("/jobs")
    public String createJob(@RequestBody Job job){
        jobService.createJob(job);
        return "Job added successfully";
    }

    @GetMapping("/jobs/{id}")         //dynamic {id} variable name what we are accepting here
    public Job getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if(job !=null){
            return job;
        }
        return new Job(1L,"TestJob","TitleJob Description","20000","30000","Mumbai");
    }
}
/*

GET /jobs :Get all jobs
GET /jobs/{id} : get a specific job by ID
POST /jobs :Create a new job (request body should contain the job details)
DELETE /jobs/{id} :Delete a specific job by ID
PUT /jobs/{id} : Update a specific job by ID (request body should contain the updated job)

Example API Urls:
GET {base_url}/jobs
GET {base_url}/jobs/1
POST {base_url}/jobs
DELETE {base_url}/jobs/1
PUT {base_url}/jobs/1

*/
