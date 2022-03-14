package com.company;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Scanner;

public class SymmetricClass {

    private final String AES = "AES";

    private final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5PADDING";
    private Scanner in;

    protected SecretKey createAESKey() throws Exception{
        SecureRandom securerandom = new SecureRandom();
        KeyGenerator keygenerator = KeyGenerator.getInstance(AES);
        keygenerator.init(128, securerandom);
        SecretKey key = keygenerator.generateKey();
        return key;
    }
    public byte[] createInitializationVector()
    {
        byte[] initializationVector = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        return initializationVector;
    }

    public byte[] aesEncryption(String plainTxt , SecretKey secretKey, byte[] initializationVector) throws Exception{
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);

        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        return cipher.doFinal(plainTxt.getBytes());
    }
    public String aesDecryption(byte[] cipherText, SecretKey secretKey, byte[] initializationVector) throws  Exception{
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);

        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);

        cipher.init(Cipher.DECRYPT_MODE,secretKey,ivParameterSpec);

        byte[] result = cipher.doFinal(cipherText);

        return new String(result);
    }


}
