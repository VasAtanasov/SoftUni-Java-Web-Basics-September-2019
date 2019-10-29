package org.atanasov.judge.service;

import org.atanasov.judge.domain.models.service.SubmissionCreateServiceModel;
import org.atanasov.judge.domain.models.view.SubmissionDetailsViewModel;

public interface SubmissionService {

    SubmissionDetailsViewModel findById(String id);

    SubmissionDetailsViewModel createSubmission(SubmissionCreateServiceModel model);
}
