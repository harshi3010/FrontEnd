package com.embark.JobApplication.job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")         //base url path for all the methods who are handling request for that particular controller
public class JobController {
     /*
    GET /jobs :Get all jobs
    GET /jobs/{id} : Get a specific job by ID
    POST /jobs: Created a new job (request body should contain the job details
    PUT /jobs/{id} : update a specific job by ID (request body should contain the updated job changes)
    DELETE /jobs/{id} : Delete a specific job by ID

     */
    private JobService jobService;

    //Constructor Injection
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    //@GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/{id}") //query parameter - dynamically changing based on ID
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        //HttpStatus - enumeration of all http code,eleminating and remembering the readibility of code.
        //ResponseEntity
        Job job = jobService.getJobById(id);
     //   return job;
        if(job!=null){
            return new ResponseEntity<>(job,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

    /* //without ResponseEntity
    @PostMapping("/jobs")
    public String createJob(@RequestBody Job job){
        jobService.createJob(job);
        return "Job added successfully";
    }
    */
    @PostMapping
    //@PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully",HttpStatus.OK);
    }

    //Delete request
    @DeleteMapping("/{id}")
    //@RequestMapping(value = "/job/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted)
            return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //@PutMapping("/jobs/{id}")
    @PutMapping("/{id}")
    //@RequestMapping(value = "/jobs/{id}",method = RequestMethod.PUT)
    public ResponseEntity<String> updateJobById(@PathVariable Long id,@RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(id,updatedJob);
        if (updated)
            return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
