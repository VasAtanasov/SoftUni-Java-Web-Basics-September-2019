package org.atanasov.judge.service;

import org.atanasov.judge.domain.models.service.ProblemCreateServiceModel;
import org.atanasov.judge.domain.models.view.ProblemDetailsViewModel;
import org.atanasov.judge.domain.models.view.ProblemHomeViewModel;

import java.util.List;

public interface ProblemService {
    List<ProblemHomeViewModel> getAllProblems(String id);

    void createProblem(ProblemCreateServiceModel model);

    ProblemDetailsViewModel getProblemById(String id);
}
