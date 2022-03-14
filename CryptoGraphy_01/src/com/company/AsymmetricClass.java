package com.company;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Scanner;

public class AsymmetricClass {

    private final String RSA = "RSA";
    private Scanner in;

    public KeyPair generateRSAKKeyPair() throws Exception
    {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);

        keyPairGenerator.initialize(2048, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }

    public byte[] rsaEncryption(String plainText, PrivateKey privateKey) throws Exception
    {
        Cipher cipher = Cipher.getInstance(RSA);

        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(plainText.getBytes());
    }

    public String rsaDecryption(byte[] cipherText, PublicKey publicKey) throws Exception
    {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(cipherText);
        return new String(result);
    }
}
