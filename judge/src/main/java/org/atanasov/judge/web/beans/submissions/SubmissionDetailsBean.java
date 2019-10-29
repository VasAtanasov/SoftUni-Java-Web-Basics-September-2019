package org.atanasov.judge.web.beans.submissions;

import lombok.Getter;
import org.atanasov.judge.domain.models.view.SubmissionDetailsViewModel;
import org.atanasov.judge.service.SubmissionService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.annotation.RequestParameterMap;
import javax.inject.Inject;
import java.util.Map;

@Getter
@Model
public class SubmissionDetailsBean {

    @Inject
    private SubmissionService submissionService;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    private SubmissionDetailsViewModel viewModel;

    @PostConstruct
    public void init() {
        String id = requestMap.get("submission_id");
        viewModel = submissionService.findById(id);
    }

}
