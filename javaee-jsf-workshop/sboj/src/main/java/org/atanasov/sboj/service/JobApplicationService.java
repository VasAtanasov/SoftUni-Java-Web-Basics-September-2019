package org.atanasov.sboj.service;

import org.atanasov.sboj.domain.models.service.JobAddServiceModel;
import org.atanasov.sboj.domain.models.view.JobApplicationViewModel;

import java.util.List;

public interface JobApplicationService {
    List<JobApplicationViewModel> getAllJobsApplications();

    void addJobApplication(JobAddServiceModel model);
}
