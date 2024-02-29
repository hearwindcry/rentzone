package com.julius.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class SignatureUtils {


    public static KeyPair keyPair() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);

        } catch (NoSuchAlgorithmException e) {
        }
        return keyPairGenerator.generateKeyPair();
    }

    public static void main(String[] args) {
        KeyPair keyPair = keyPair();
        System.out.println("private-->"+ keyPair.getPrivate());
        System.out.println("public-->"+ keyPair.getPublic());
    }
}
