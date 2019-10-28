package org.atanasov.judge.service;

public interface MessageService  {


    void addMessage(String message);

    void addMessage(String clientId, String message);
}
