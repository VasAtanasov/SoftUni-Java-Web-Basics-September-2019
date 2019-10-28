package org.atanasov.judge.web.beans.problems;

import lombok.Getter;
import org.atanasov.judge.domain.models.view.ProblemDetailsViewModel;
import org.atanasov.judge.service.ProblemService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.annotation.RequestParameterMap;
import javax.inject.Inject;
import java.util.Map;

@Model
@Getter
public class ProblemDetailsBean {

    @Inject
    private ProblemService problemService;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    private ProblemDetailsViewModel viewModel;

    @PostConstruct
    public void init() {
        String id = requestMap.get("id");
        viewModel = problemService.getProblemById(id);
    }
}
