package org.atanasov.exodia.web.beans.documents;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atanasov.exodia.domain.models.service.DocumentScheduleServiceModel;
import org.atanasov.exodia.domain.models.view.DocumentViewModel;
import org.atanasov.exodia.service.DocumentService;
import org.atanasov.exodia.service.MessageService;
import org.atanasov.exodia.service.RedirectService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static org.atanasov.exodia.constants.Constants.DETAILS_DOCUMENTS_URL;

@Model
@Getter
@Setter
@NoArgsConstructor
public class DocumentScheduleBean {

    @NotNull
    @Size(min = 1, max = 255)
    private String title;

    @NotNull
    @Size(min = 1)
    private String content;

    @Inject
    private RedirectService redirectService;

    @Inject
    private DocumentService documentService;

    @Inject
    private MessageService messageService;

    public void schedule() {
        DocumentScheduleServiceModel model = DocumentScheduleServiceModel.builder()
                .title(title)
                .content(content)
                .build();

        try {
            DocumentViewModel viewModel = documentService.scheduleDocument(model);
            redirectService.redirect(DETAILS_DOCUMENTS_URL + "?id=" + viewModel.getId());
        } catch (Exception ex) {
            messageService.addMessage("Could not schedule document. Please try again.");
        }
    }
}
