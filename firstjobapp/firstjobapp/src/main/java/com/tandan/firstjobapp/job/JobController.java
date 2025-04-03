package com.tandan.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class JobController {
    //since we don't have database we manage everything with list
    //return all jobs
    //creating Service class object
     private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }
    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added Successfully",HttpStatus.CREATED);
    }
    @GetMapping("jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){

        Job job = jobService.getJobById(id);
        if(job!=null) {
            return new ResponseEntity<>(job,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted){
            return new ResponseEntity<>("Job deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/jobs/{id}")
 //   @RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updatedJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobService.updatedJob(id,updatedJob);
        if(updated){
            return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
