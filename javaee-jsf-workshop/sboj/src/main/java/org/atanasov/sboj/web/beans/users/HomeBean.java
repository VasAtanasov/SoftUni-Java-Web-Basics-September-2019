package org.atanasov.sboj.web.beans.users;

import lombok.Getter;
import org.atanasov.sboj.domain.models.view.JobApplicationViewModel;
import org.atanasov.sboj.service.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model
@Getter
public class HomeBean {

    @Inject
    private JobApplicationService service;

    private List<JobApplicationViewModel> jobs;

    @PostConstruct
    private void init() {
        jobs = new ArrayList<>();
        jobs.addAll(service.getAllJobsApplications());
    }
}
