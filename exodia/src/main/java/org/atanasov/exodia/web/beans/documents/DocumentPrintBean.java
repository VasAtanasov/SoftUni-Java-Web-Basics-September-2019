package org.atanasov.exodia.web.beans.documents;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atanasov.exodia.domain.models.view.DocumentViewModel;
import org.atanasov.exodia.service.DocumentService;
import org.atanasov.exodia.service.MessageService;
import org.atanasov.exodia.service.RedirectService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.annotation.RequestParameterMap;
import javax.inject.Inject;
import java.util.Map;

import static org.atanasov.exodia.constants.Constants.HOME_URL;

@Model
@Getter
@Setter
@NoArgsConstructor
public class DocumentPrintBean {

    @Inject
    private RedirectService redirectService;

    @Inject
    private DocumentService documentService;

    @Inject
    private MessageService messageService;
    private DocumentViewModel viewModel;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    @PostConstruct
    public void init() {
        String id = requestMap.get("id");
        viewModel = documentService.findById(id);
    }

    public void print() {
        String id = requestMap.get("id");
        try {
            documentService.print(id);
            redirectService.redirect(HOME_URL);
        } catch (Exception  e) {
            messageService.addMessage("Printing failed. Please retry or contact support.");
        }
    }
}
