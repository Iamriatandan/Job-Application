package com.tandan.firstjobapp.job;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job getJobById(Long id);
     boolean deleteJobById(Long id);

    boolean updatedJob(Long id,Job updatedJob);
}
