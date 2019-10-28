package org.atanasov.exodia.web.beans.users;

import lombok.Getter;
import org.atanasov.exodia.domain.models.view.DocumentTitleViewModel;
import org.atanasov.exodia.service.DocumentService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model
@Getter
public class HomeBean {

    @Inject
    private DocumentService documentService;

    private List<DocumentTitleViewModel> titles;

    @PostConstruct
    private void init() {
        titles = new ArrayList<>();
        titles.addAll(documentService.getTitles());
    }
}
