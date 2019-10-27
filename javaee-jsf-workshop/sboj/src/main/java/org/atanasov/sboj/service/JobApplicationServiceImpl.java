package org.atanasov.sboj.service;

import org.atanasov.sboj.domain.entities.JobApplication;
import org.atanasov.sboj.domain.models.service.JobAddServiceModel;
import org.atanasov.sboj.domain.models.view.JobApplicationViewModel;
import org.atanasov.sboj.repository.JobApplicationRepository;
import org.atanasov.sboj.util.ModelMapperWrapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapperWrapper modelMapper;

    @Inject
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository,
                                     ModelMapperWrapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<JobApplicationViewModel> getAllJobsApplications() {
        return jobApplicationRepository.findAll()
                .stream()
                .map(job -> modelMapper.map(job, JobApplicationViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addJobApplication(JobAddServiceModel model) {
        jobApplicationRepository.save(modelMapper.map(model, JobApplication.class));
    }

    @Override
    public JobApplicationViewModel findById(String id) {
        return modelMapper.map(jobApplicationRepository.find(id), JobApplicationViewModel.class);
    }

    @Override
    public void delete(String id) {
        Optional<JobApplication> application = jobApplicationRepository.findOptional(id);
        application.orElseThrow(() -> new IllegalArgumentException("Job Application not found"));
        jobApplicationRepository.remove(application.get());
    }
}
