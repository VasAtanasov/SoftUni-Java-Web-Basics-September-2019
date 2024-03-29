package org.atanasov.sboj.service;

import javax.faces.context.FacesContext;
import java.io.IOException;

public class RedirectServiceImpl implements RedirectService {
    @Override
    public void redirect(String path) throws IOException {
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect(path);
    }
}

