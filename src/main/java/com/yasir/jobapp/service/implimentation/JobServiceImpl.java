package com.yasir.jobapp.service.implimentation;

import com.yasir.jobapp.entities.Job;
import com.yasir.jobapp.repository.CompanyRepository;
import com.yasir.jobapp.repository.JobRepository;
import com.yasir.jobapp.service.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;
    CompanyRepository companyRepository;

    public JobServiceImpl(JobRepository jobRepository, CompanyRepository companyRepository) {

        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    // Find all jobs
    @Override
    public List<Job> findAll() {

        return jobRepository.findAll();
    }

    // Find job by id
    @Override
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    // Update Job Method
    @Override
    public Boolean updateJobById(Long id, Job updatedJob) {

        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent())
        {
            Job job = jobOptional.get(); // If the Object(job) present get the Object(job) and assign to job variable
            job.setJobTitle(updatedJob.getJobTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            job.setCompany(updatedJob.getCompany());
            jobRepository.save(job);
            return true;
        }
        return false;
    }

    // Delete Job By Id
    @Override
    public Boolean deleteJobById(Long id) {
        try {
            if(jobRepository.existsById(id)){
                jobRepository.deleteById(id);
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
