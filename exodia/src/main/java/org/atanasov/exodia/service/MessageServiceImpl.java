package org.atanasov.exodia.service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageServiceImpl implements MessageService {

    @Override
    public void addMessage(String message) {
        addMessage(null, message);
    }

    @Override
    public void addMessage(String clientId, String message) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(message));
    }
}
