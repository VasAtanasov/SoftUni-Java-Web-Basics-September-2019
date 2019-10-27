package org.atanasov.sboj.web.beans.jobs;

import org.atanasov.sboj.domain.enums.Sector;

import javax.enterprise.inject.Model;

@Model
public class JobSectorBean {

    public Sector[] getSector() {
        return Sector.values();
    }

}
