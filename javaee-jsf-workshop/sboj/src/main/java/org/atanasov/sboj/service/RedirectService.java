package org.atanasov.sboj.service;

import java.io.IOException;

public interface RedirectService {
    void redirect(String path) throws IOException;
}
