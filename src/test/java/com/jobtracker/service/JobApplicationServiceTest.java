package com.jobtracker.service;

import com.jobtracker.model.ApplicationStatus;
import com.jobtracker.model.JobApplication;
import com.jobtracker.repository.JobApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JobApplicationServiceTest {

    @Mock
    private JobApplicationRepository repository;

    @InjectMocks
    private JobApplicationService service;

    @Test
    void findAllShouldReturnApplications() {

        // Arrange
        JobApplication application = new JobApplication();
        application.setCompanyName("Google");
        application.setJobTitle("Software Developer");

        when(repository.findAll())
                .thenReturn(List.of(application));

        // Act
        List<JobApplication> result = service.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Google", result.get(0).getCompanyName());
        assertEquals("Software Developer", result.get(0).getJobTitle());
    }


    @Test
    void findByStatusShouldReturnMatchingApplications() {

        // Arrange
        JobApplication application = new JobApplication();
        application.setCompanyName("Microsoft");
        application.setJobTitle("Backend Developer");
        application.setStatus(ApplicationStatus.INTERVIEW);

        when(repository.findByStatus(ApplicationStatus.INTERVIEW))
                .thenReturn(List.of(application));

        // Act
        List<JobApplication> result =
                service.findByStatus(ApplicationStatus.INTERVIEW);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Microsoft", result.get(0).getCompanyName());
        assertEquals(ApplicationStatus.INTERVIEW, result.get(0).getStatus());
    }

    @Test
    void saveShouldCallRepositoryAndReturnSavedApplication() {

        // Arrange
        JobApplication application = new JobApplication();
        application.setCompanyName("Amazon");
        application.setJobTitle("Java Developer");
        application.setStatus(ApplicationStatus.APPLIED);

        when(repository.save(application))
                .thenReturn(application);

        // Act
        JobApplication result = service.save(application);

        // Assert
        assertEquals("Amazon", result.getCompanyName());
        assertEquals("Java Developer", result.getJobTitle());

        verify(repository).save(application);
    }

    @Test
    void deleteByIdShouldCallRepository() {

        // Arrange
        Long applicationId = 1L;

        // Act
        service.deleteById(applicationId);

        // Assert
        verify(repository).deleteById(applicationId);
    }

    @Test
    void countAllShouldReturnTotalApplications() {

        // Arrange
        when(repository.count()).thenReturn(10L);

        // Act
        long result = service.countAll();

        // Assert
        assertEquals(10L, result);
        verify(repository).count();
    }

    @Test
    void countByStatusShouldReturnMatchingCount() {

        // Arrange
        when(repository.countByStatus(ApplicationStatus.INTERVIEW))
                .thenReturn(3L);

        // Act
        long result = service.countByStatus(ApplicationStatus.INTERVIEW);

        // Assert
        assertEquals(3L, result);

        verify(repository).countByStatus(ApplicationStatus.INTERVIEW);
    }

}