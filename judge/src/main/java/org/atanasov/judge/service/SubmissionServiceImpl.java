package org.atanasov.judge.service;

import org.atanasov.judge.domain.entities.Problem;
import org.atanasov.judge.domain.entities.Submission;
import org.atanasov.judge.domain.entities.User;
import org.atanasov.judge.domain.models.service.SubmissionCreateServiceModel;
import org.atanasov.judge.domain.models.view.SubmissionDetailsViewModel;
import org.atanasov.judge.repository.ProblemRepository;
import org.atanasov.judge.repository.SubmissionRepository;
import org.atanasov.judge.repository.UserRepository;
import org.atanasov.judge.util.ModelMapperWrapper;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SubmissionServiceImpl implements SubmissionService {

    private static final Function<Integer, Integer> RANDOM_NUM
            = (num) -> new Random().ints(1, 0, num)
            .findFirst()
            .orElse(0);

    private final SubmissionRepository submissionRepository;
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;
    private final ModelMapperWrapper modelMapper;
    private final JavaScriptExecutorService executorService;

    @Inject
    public SubmissionServiceImpl(SubmissionRepository submissionRepository,
                                 ProblemRepository problemRepository,
                                 UserRepository userRepository,
                                 ModelMapperWrapper modelMapper,
                                 JavaScriptExecutorService executorService) {

        this.submissionRepository = submissionRepository;
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.executorService = executorService;
    }

    @Override
    public SubmissionDetailsViewModel findById(String id) {
        Submission submission = submissionRepository.find(id);
        return modelMapper.map(submission, SubmissionDetailsViewModel.class);
    }

    @Override
    public SubmissionDetailsViewModel createSubmission(SubmissionCreateServiceModel model) {
        List<String> code = parsCode(model.getCode());
        User user = userRepository.find(model.getUserId());
        Problem problem = problemRepository.find(model.getProblemId());

        Integer achievedResult = RANDOM_NUM.apply(problem.getPoints());

        try {
            String result = executorService.execute(model.getCode());
            if (result.equals(problem.getName())) {
                achievedResult = problem.getPoints();
            }
        } catch (IllegalArgumentException ignored) { }

        Submission submission = Submission.builder()
                .code(code)
                .user(user)
                .achievedResult(achievedResult)
                .problem(problem)
                .build();

        submissionRepository.save(submission);

        return modelMapper.map(submission, SubmissionDetailsViewModel.class);
    }


    private List<String> parsCode(String code) {
        return Arrays.stream(code.split("\n"))
                .collect(Collectors.toList());
    }
}
