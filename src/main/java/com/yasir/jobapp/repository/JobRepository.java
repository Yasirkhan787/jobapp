package com.yasir.jobapp.repository;

import com.yasir.jobapp.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
