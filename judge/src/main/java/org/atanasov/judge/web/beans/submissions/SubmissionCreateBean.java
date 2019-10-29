package org.atanasov.judge.web.beans.submissions;

import lombok.Getter;
import lombok.Setter;
import org.atanasov.judge.domain.models.service.SubmissionCreateServiceModel;
import org.atanasov.judge.domain.models.view.SubmissionDetailsViewModel;
import org.atanasov.judge.service.MessageService;
import org.atanasov.judge.service.ProblemService;
import org.atanasov.judge.service.RedirectService;
import org.atanasov.judge.service.SubmissionService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Map;

import static org.atanasov.judge.constants.Constants.SUBMISSION_DETAILS_URL;

@Getter
@Model
public class SubmissionCreateBean {

    private static final String REDIRECT_ERROR = "Something went wrong please try again.";

    @NotNull(message = "Code must be of 5 characters or more.")
    @Setter
    private String code;

    @Inject
    private SubmissionService submissionService;

    @Inject
    private ProblemService problemService;

    @Inject
    private RedirectService redirectService;

    @Inject
    private MessageService messageService;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    private String problemName;

    @PostConstruct
    public void init() {
        problemName = requestMap.get("problem_name");
    }

    public void creat() {
        String problemId = requestMap.get("problem_id");

        String userId = (String) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("userId");

        SubmissionCreateServiceModel model = SubmissionCreateServiceModel.builder()
                .userId(userId)
                .problemId(problemId)
                .code(code)
                .build();

        try {
            SubmissionDetailsViewModel submission = submissionService.createSubmission(model);
            redirectService.redirect(SUBMISSION_DETAILS_URL + "?submission_id=" + submission.getId());
        } catch (IllegalArgumentException iae) {
            messageService.addMessage(iae.getMessage());
        } catch (IOException io) {
            messageService.addMessage(REDIRECT_ERROR);
        }
    }
}
