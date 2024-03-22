package com.embarkx.Firstjobapp.job.impl;

import com.embarkx.Firstjobapp.job.Job;
import com.embarkx.Firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs= new ArrayList<>();
    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {

        for(Job job: jobs){
            if(job.getId().equals(id)){
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long id) {
        Iterator<Job> it= jobs.iterator();
        while(it.hasNext()){
            Job job= it.next();
            if(job.getId().equals(id)){
                it.remove();
                return true;
            }
        }

        return false;


    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
       for(Job job: jobs){
           if(job.getId().equals(id)){
               job.setDescription(updatedJob.getDescription());
               job.setLocation(updatedJob.getLocation());
               job.setTitle(updatedJob.getTitle());
               job.setMaxSalary(updatedJob.getMaxSalary());
               job.setMinSalary(updatedJob.getMinSalary());
               return true;
           }
       }
        return false;
    }
}
