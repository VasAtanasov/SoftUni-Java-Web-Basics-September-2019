package org.atanasov.judge.service;

import org.atanasov.judge.domain.entities.Problem;
import org.atanasov.judge.domain.entities.Submission;
import org.atanasov.judge.domain.models.view.ProblemHomeViewModel;
import org.atanasov.judge.repository.ProblemRepository;
import org.atanasov.judge.util.ModelMapperWrapper;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProblemServiceImpl implements ProblemService {


    private final ProblemRepository problemRepository;
    private final ModelMapperWrapper modelMapper;

    @Inject
    public ProblemServiceImpl(ProblemRepository problemRepository, ModelMapperWrapper modelMapper) {
        this.problemRepository = problemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProblemHomeViewModel> getAllProblems(String id) {
        Map<String, Object> params = new LinkedHashMap<>() {{
            put("user_id", id);
        }};

        return problemRepository.findByNamedQueryAndNamedParams(Problem.FIND_WITH_USER_SUBMISSIONS, params)
                .stream()
                .map(problem -> {
                    ProblemHomeViewModel model = modelMapper.map(problem, ProblemHomeViewModel.class);

                    int userAchievedResult = problem.getSubmissions()
                            .stream()
                            .filter(submission -> submission.getUser().getId().equals(id))
                            .min((p1, p2) -> Integer.compare(p2.getAchievedResult(), p1.getAchievedResult()))
                            .map(Submission::getAchievedResult)
                            .get();

                    model.setCompletion((userAchievedResult * 100) / problem.getPoints());
                    return model;
                })
                .collect(Collectors.toList());
    }
}
