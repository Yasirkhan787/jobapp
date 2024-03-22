package com.yasir.jobapp.service;


import com.yasir.jobapp.entities.Job;

import java.util.ArrayList;
import java.util.List;

public interface JobService {

    void createJob(Job job);
    List<Job> findAll();
    Job findJobById(Long id);
    Boolean updateJobById(Long id, Job job);
    Boolean deleteJobById(Long id);

}
