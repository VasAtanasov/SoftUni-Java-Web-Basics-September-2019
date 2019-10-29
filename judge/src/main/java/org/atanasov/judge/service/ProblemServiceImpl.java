package org.atanasov.judge.service;

import org.atanasov.judge.domain.entities.Problem;
import org.atanasov.judge.domain.entities.Submission;
import org.atanasov.judge.domain.models.service.ProblemCreateServiceModel;
import org.atanasov.judge.domain.models.view.ProblemDetailsViewModel;
import org.atanasov.judge.domain.models.view.ProblemHomeViewModel;
import org.atanasov.judge.repository.ProblemRepository;
import org.atanasov.judge.util.ModelMapperWrapper;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

public class ProblemServiceImpl implements ProblemService {

    private static final String INVALID_PROBLEM_NAME = "Problem name must be with valid length of 2 chars or more.";
    private static final String INVALID_TOTAL_POINTS = "Problem total points must be between 50 adn 250.";

    private final ProblemRepository problemRepository;
    private final ModelMapperWrapper modelMapper;
    private final ProblemValidationService validationService;

    @Inject
    public ProblemServiceImpl(ProblemRepository problemRepository,
                              ModelMapperWrapper modelMapper,
                              ProblemValidationService validationService) {

        this.problemRepository = problemRepository;
        this.modelMapper = modelMapper;
        this.validationService = validationService;
    }

    @Override
    public List<ProblemHomeViewModel> getAllProblems(String id) {
        return problemRepository.findByNamedQuery(Problem.FIND_WITH_USER_SUBMISSIONS)
                .stream()
                .map(problem -> {
                    ProblemHomeViewModel model = modelMapper.map(problem, ProblemHomeViewModel.class);

                    int userAchievedResult = problem.getSubmissions()
                            .stream()
                            .filter(submission -> submission.getUser().getId().equals(id))
                            .max(Comparator.comparingInt(Submission::getAchievedResult))
                            .map(Submission::getAchievedResult)
                            .orElse(0);

                    double percent = (userAchievedResult * 100.0) / problem.getPoints();

                    model.setCompletion((int) Math.round(percent));

                    return model;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void createProblem(ProblemCreateServiceModel model) {
        if (!validationService.isProblemNameValid(model.getName())) {
            throw new IllegalArgumentException(INVALID_PROBLEM_NAME);
        }

        if (!validationService.areProblemTotalPointsValid(model.getPoints())) {
            throw new IllegalArgumentException(INVALID_TOTAL_POINTS);
        }

        problemRepository.save(modelMapper.map(model, Problem.class));
    }

    @Override
    public ProblemDetailsViewModel getProblemById(String id) {
        Map<String, Object> params = new LinkedHashMap<>() {{
            put("problem_id", id);
        }};

        return problemRepository.findByNamedQueryAndNamedParams(Problem.FIND_BY_ID_WITH_USER_SUBMISSIONS, params)
                .stream()
                .findFirst()
                .map(problem -> {
                    ProblemDetailsViewModel model = modelMapper.map(problem, ProblemDetailsViewModel.class);

                    model.getSubmissions().forEach(sub -> {
                        double percent = (sub.getAchievedResult() * 100.0) / problem.getPoints();
                        sub.setAchievedPercentage((int) Math.round(percent));
                    });

                    long totalMaxResult = model.getSubmissions()
                            .stream()
                            .filter(sub -> problem.getPoints().equals(sub.getAchievedResult()))
                            .count();

                    final int size = model.getSubmissions().size() == 0 ? 1 : model.getSubmissions().size();

                    double successRate = (totalMaxResult * 100.0) / size;

                    model.setSuccessRate(successRate);

                    return model;
                })
                .get();
    }
}
