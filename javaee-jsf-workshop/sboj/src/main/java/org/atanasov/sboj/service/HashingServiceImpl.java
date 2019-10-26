package org.atanasov.sboj.service;

import org.apache.commons.codec.digest.DigestUtils;

public class HashingServiceImpl implements HashingService {

    @Override
    public String hash(String password) {
        return DigestUtils.sha256Hex(password);
    }
}
