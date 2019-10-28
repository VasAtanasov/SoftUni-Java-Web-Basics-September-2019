package org.atanasov.judge.service;

public class ProblemValidationServiceImpl implements ProblemValidationService {

    @Override
    public boolean isProblemNameValid(String name) {
        return name != null && name.length() >= 2 && name.length() < 255;
    }

    @Override
    public boolean areProblemTotalPointsValid(Integer points) {
        return points != null && points >= 50 && points <= 250;
    }
}
