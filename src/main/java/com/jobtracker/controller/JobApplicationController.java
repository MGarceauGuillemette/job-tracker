package com.jobtracker.controller;

import com.jobtracker.dto.JobApplicationForm;
import com.jobtracker.model.ApplicationStatus;
import com.jobtracker.model.JobApplication;
import com.jobtracker.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String listApplications(Model model) {
        model.addAttribute("applications", service.findAll());
        return "applications";
    }

    @GetMapping("/applications/new")
    public String showCreateForm(Model model) {
        model.addAttribute("form", new JobApplicationForm());
        model.addAttribute("statuses", ApplicationStatus.values());
        return "application-form";
    }

    @GetMapping("/applications/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        JobApplication application = service.findById(id);

        JobApplicationForm form = new JobApplicationForm();
        form.setCompanyName(application.getCompanyName());
        form.setJobTitle(application.getJobTitle());
        form.setJobUrl(application.getJobUrl());
        form.setStatus(application.getStatus());
        form.setDateApplied(application.getDateApplied());
        form.setFollowUpDate(application.getFollowUpDate());
        form.setNotes(application.getNotes());

        model.addAttribute("form", form);
        model.addAttribute("statuses", ApplicationStatus.values());
        model.addAttribute("applicationId", id);

        return "application-form";
    }

    @GetMapping("/applications/{id}")
    public String viewApplication(@PathVariable Long id, Model model) {
        JobApplication application = service.findById(id);
        model.addAttribute("job", application);
        return "application-details";
    }

    @PostMapping("/applications")
    public String createApplication(
            @Valid @ModelAttribute("form") JobApplicationForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", ApplicationStatus.values());
            return "application-form";
        }

        JobApplication application = new JobApplication();
        application.setCompanyName(form.getCompanyName().trim());
        application.setJobTitle(form.getJobTitle().trim());
        application.setJobUrl(form.getJobUrl());
        application.setStatus(form.getStatus());
        application.setDateApplied(form.getDateApplied());
        application.setFollowUpDate(form.getFollowUpDate());
        application.setNotes(form.getNotes());

        service.create(application);

        return "redirect:/";
    }

    @PostMapping("/applications/{id}/edit")
    public String updateApplication(
            @PathVariable Long id,
            @Valid @ModelAttribute("form") JobApplicationForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", ApplicationStatus.values());
            model.addAttribute("applicationId", id);
            return "application-form";
        }

        JobApplication application = service.findById(id);

        application.setCompanyName(form.getCompanyName().trim());
        application.setJobTitle(form.getJobTitle().trim());
        application.setJobUrl(form.getJobUrl());
        application.setStatus(form.getStatus());
        application.setDateApplied(form.getDateApplied());
        application.setFollowUpDate(form.getFollowUpDate());
        application.setNotes(form.getNotes());

        service.save(application);

        return "redirect:/";
    }

    @PostMapping("/applications/{id}/delete")
    public String deleteApplication(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/";
    }
}