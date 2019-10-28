package org.atanasov.judge.service;

import org.atanasov.judge.domain.models.view.ProblemHomeViewModel;

import java.util.List;

public interface ProblemService {
    List<ProblemHomeViewModel> getAllProblems(String id);
}
