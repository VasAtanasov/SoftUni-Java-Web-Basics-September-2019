package org.atanasov.judge.service;

public interface ProblemValidationService {

    boolean isProblemNameValid(String name);

    boolean areProblemTotalPointsValid(Integer points);

}
