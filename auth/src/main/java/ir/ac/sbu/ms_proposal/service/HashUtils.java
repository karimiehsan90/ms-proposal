package ir.ac.sbu.ms_proposal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashUtils {
    private Logger logger = LoggerFactory.getLogger(HashUtils.class);

    public String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger bigInteger = new BigInteger(1, digest);
            return bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            logger.warn("Algorithm not found", e);
        }
        return null;
    }
}
