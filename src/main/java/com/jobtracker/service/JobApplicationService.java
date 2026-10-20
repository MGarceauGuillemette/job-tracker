package com.jobtracker.service;

import com.jobtracker.model.ApplicationStatus;
import com.jobtracker.model.JobApplication;
import com.jobtracker.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    public List<JobApplication> findAll() {
        return repository.findAll();
    }

    public List<JobApplication> findByStatus(ApplicationStatus status) {
        return repository.findByStatus(status);
    }

    public JobApplication findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Job application not found: " + id));
    }

    public JobApplication save(JobApplication application) {
        return repository.save(application);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public JobApplication create(JobApplication application) {
        return repository.save(application);
    }
}
