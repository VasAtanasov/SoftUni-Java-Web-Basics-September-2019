package org.atanasov.sboj.web.beans.jobs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atanasov.sboj.domain.enums.Sector;
import org.atanasov.sboj.domain.models.service.JobAddServiceModel;
import org.atanasov.sboj.service.JobApplicationService;
import org.atanasov.sboj.service.MessageService;
import org.atanasov.sboj.service.RedirectService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.math.BigDecimal;

import static org.atanasov.sboj.constants.Constants.HOME_URL;

@Model
@Getter
@Setter
@NoArgsConstructor
public class JobAddBean {

    @NotNull
    private Sector sector;

    @NotNull
    @Size(min = 1, max = 32)
    private String profession;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal salary;

    private String description;

    @Inject
    private JobApplicationService applicationService;

    @Inject
    private RedirectService redirectService;

    @Inject
    private MessageService messageService;

    public void addJob() {
        JobAddServiceModel model = JobAddServiceModel.builder()
                .sector(sector)
                .profession(profession)
                .salary(salary)
                .description(description)
                .build();

        try {
            applicationService.addJobApplication(model);
            redirectService.redirect(HOME_URL);
        } catch (IllegalArgumentException | IOException iae) {
            messageService.addMessage(iae.getMessage());
        }
    }

}