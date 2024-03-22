package com.embarkx.Firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

//@RequestMapping("/jobs")
// we can use above line to make /jobs as base url
// which decreases the handly step of writing /jobs in every mapping
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService){
        this.jobService=jobService;
    }
   @GetMapping("/jobs")
    public ResponseEntity<List<Job>>findAll(){
        return ResponseEntity.ok(jobService.findAll())  ;

   }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
       jobService.createJob(job);
       return new ResponseEntity<>("job added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job=jobService.getJobById(id);
        if(job!=null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String>deleteJob(@PathVariable Long id){
        boolean deleted=jobService.deleteJobById(id);
        if(deleted)
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);

            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
    }


   @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated=jobService.updateJob(id,updatedJob);
           if(updated){
               return new  ResponseEntity<>("Job updated successfully", HttpStatus.OK);

           }

           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
