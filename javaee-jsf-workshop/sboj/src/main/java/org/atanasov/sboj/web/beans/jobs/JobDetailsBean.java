package org.atanasov.sboj.web.beans.jobs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atanasov.sboj.domain.models.view.JobApplicationViewModel;
import org.atanasov.sboj.service.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.annotation.RequestParameterMap;
import javax.inject.Inject;
import java.util.Map;

@Model
@Getter
@Setter
@NoArgsConstructor
public class JobDetailsBean {

    @Inject
    private JobApplicationService applicationService;

    private JobApplicationViewModel model;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    @PostConstruct
    public void init() {
        String id = requestMap.get("id");
        model = applicationService.findById(id);
    }
}
