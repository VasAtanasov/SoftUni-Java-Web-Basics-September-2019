package org.atanasov.exodia.web.beans.documents;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atanasov.exodia.domain.models.view.DocumentViewModel;
import org.atanasov.exodia.service.DocumentService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.annotation.RequestParameterMap;
import javax.inject.Inject;
import java.util.Map;

@Model
@Getter
@Setter
@NoArgsConstructor
public class DocumentDetailsBean {

    @Inject
    private DocumentService documentService;

    private DocumentViewModel viewModel;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    @PostConstruct
    public void init() {
        String id = requestMap.get("id");
        viewModel = documentService.findById(id);
    }
}
