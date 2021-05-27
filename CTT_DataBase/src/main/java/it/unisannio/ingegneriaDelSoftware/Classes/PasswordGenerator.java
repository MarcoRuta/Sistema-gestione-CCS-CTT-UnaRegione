package it.unisannio.ingegneriaDelSoftware.Classes;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class PasswordGenerator {
    public static String getPassword() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[8];
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes)
                .replace('=',(char)( new Random().nextInt(9)+48));
    }
}
