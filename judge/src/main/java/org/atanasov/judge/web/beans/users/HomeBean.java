package org.atanasov.judge.web.beans.users;

import lombok.Getter;
import org.atanasov.judge.domain.models.view.ProblemHomeViewModel;
import org.atanasov.judge.service.JavaScriptExecutorService;
import org.atanasov.judge.service.ProblemService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.atanasov.judge.constants.Constants.USER_ID_ATTR;

@Model
@Getter
public class HomeBean {

    @Inject
    private ProblemService problemService;


    @Inject
    private HttpServletRequest request;

    private List<ProblemHomeViewModel> problems;

    @PostConstruct
    private void init() {
        String userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(USER_ID_ATTR);
        problems = new ArrayList<>();
        problems.addAll(problemService.getAllProblems(userId));
    }
}
