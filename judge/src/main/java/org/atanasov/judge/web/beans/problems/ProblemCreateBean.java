package org.atanasov.judge.web.beans.problems;

import antlr.ASdebug.IASDebugStream;
import lombok.Getter;
import lombok.Setter;
import org.atanasov.judge.domain.models.service.ProblemCreateServiceModel;
import org.atanasov.judge.service.MessageService;
import org.atanasov.judge.service.ProblemService;
import org.atanasov.judge.service.RedirectService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.logging.Logger;

import static org.atanasov.judge.constants.Constants.HOME_URL;

@Model
@Setter
@Getter
public class ProblemCreateBean {

    private static final String COULD_NOT_CREATE_PROBLEM = "Could not create problem or problem already exists.";
    private static final String INVALID_MODEL = "Invalid problem model.";

    private Logger logger = Logger.getLogger(ProblemCreateBean.class.getName());

    @Inject
    private ProblemService problemService;

    @Inject
    private RedirectService redirectService;

    @Inject
    private MessageService messageService;

    @NotNull
    private String name;

    @NotNull
    @Min(value = 50)
    @Max(value = 250)
    private Integer points;

    public void create() {
        ProblemCreateServiceModel model = ProblemCreateServiceModel.builder()
                .name(name)
                .points(points)
                .build();

        try {
            problemService.createProblem(model);
            redirectService.redirect(HOME_URL);
        } catch (IllegalArgumentException iae) {
            messageService.addMessage(iae.getMessage());
            logger.severe(INVALID_MODEL);
        } catch (Exception ex) {
            messageService.addMessage(COULD_NOT_CREATE_PROBLEM);
            logger.severe(COULD_NOT_CREATE_PROBLEM);
        }
    }
}
