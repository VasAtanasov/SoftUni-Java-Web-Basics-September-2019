package org.atanasov.exodia.service;

import org.atanasov.exodia.domain.models.service.DocumentScheduleServiceModel;
import org.atanasov.exodia.domain.models.view.DocumentTitleViewModel;
import org.atanasov.exodia.domain.models.view.DocumentViewModel;

import java.util.List;

public interface DocumentService {
    List<DocumentTitleViewModel> getTitles();

    DocumentViewModel scheduleDocument(DocumentScheduleServiceModel model);
}
