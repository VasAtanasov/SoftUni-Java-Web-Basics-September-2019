package org.atanasov.sboj.web.beans.jobs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atanasov.sboj.domain.models.view.JobApplicationViewModel;
import org.atanasov.sboj.service.JobApplicationService;
import org.atanasov.sboj.service.MessageService;
import org.atanasov.sboj.service.RedirectService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.annotation.RequestParameterMap;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

@Model
@Getter
@Setter
@NoArgsConstructor
public class JobDeleteBean {

    @Inject
    private JobApplicationService applicationService;

    @Inject
    private RedirectService redirectService;

    @Inject
    private MessageService messageService;

    private JobApplicationViewModel model;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    @PostConstruct
    public void init() {
        String id = requestMap.get("id");
        model = applicationService.findById(id);
    }

    public void deleteJob() throws IOException {
        try {
            applicationService.delete(requestMap.get("id"));
            redirectService.redirect("/home");
        } catch (IllegalArgumentException iae) {
            messageService.addMessage("Job Application delete failed. Please try again or contact support.");
        }
    }

}
