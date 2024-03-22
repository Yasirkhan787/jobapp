package com.yasir.jobapp.controller;

import com.yasir.jobapp.entities.Job;
import com.yasir.jobapp.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/jobs") // Base url
public class JobController {

    JobService jobService;


    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // Get and return all jobs.
    @GetMapping
    public ResponseEntity<List<Job>> findAllJobs(){
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

    // Get a specific job determined by id and return it.
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        if (jobService.findJobById(id) != null) {
            return new ResponseEntity<>(jobService.findJobById(id), HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new job.
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job Added Successfully", HttpStatus.CREATED);
    }

    // Update job by id
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id,
                                                @RequestBody Job job){
        Boolean update = jobService.updateJobById(id, job);
        if(update){
            return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // Delete a job by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        Boolean delete = jobService.deleteJobById(id);
        if (delete) {
            return ResponseEntity.ok("Job Deleted Successfully");
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

}
