package com.mesmer.book_network;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtBase64SecretGenerator {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[64]; // 64 octets (512 bits)
        secureRandom.nextBytes(key);
        String base64Key = Base64.getEncoder().encodeToString(key);
        System.out.println("Generated Base64 Secret Key: " + base64Key);
    }
}