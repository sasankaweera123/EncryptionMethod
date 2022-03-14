package com.company;

import javax.crypto.SecretKey;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyPair;

public class Main {

    public static void main(String[] args) throws Exception {



        //Symmetric Key
        SymmetricClass sym = new SymmetricClass();
        SecretKey secretKey = sym.createAESKey();
        System.out.println("The Symmetric Key is :" + DatatypeConverter.printHexBinary(secretKey.getEncoded()));
        byte[] initializationVector = sym.createInitializationVector();
        String plainText = "My name Is Sasanka  " + "This is what I To Encrypt.";
        byte[] cipherText = sym.aesEncryption(plainText, secretKey, initializationVector);
        System.out.println("The ciphertext or " + "Encrypted Message is: " + DatatypeConverter.printHexBinary(cipherText));
        String decryptedText = sym.aesDecryption(cipherText, secretKey, initializationVector);
        System.out.println("Your original message is: " + decryptedText);
        System.out.println("Done!");
        System.out.println();

        //Asymmetric Key
        AsymmetricClass asy = new AsymmetricClass();
        KeyPair keypair = asy.generateRSAKKeyPair();
        byte[] cipherTextRSA = asy.rsaEncryption(plainText, keypair.getPrivate());
        System.out.println("The Public Key is: " + DatatypeConverter.printHexBinary(keypair.getPublic().getEncoded()));
        System.out.println("The Private Key is: " + DatatypeConverter.printHexBinary(keypair.getPrivate().getEncoded()));
        System.out.print("The Encrypted Text is: ");
        System.out.println(DatatypeConverter.printHexBinary(cipherTextRSA));
        String decryptedTextRSA = asy.rsaDecryption(cipherTextRSA, keypair.getPublic());
        System.out.println("The decrypted text is: " + decryptedTextRSA);
        System.out.println("Done!");



        //GUI

        JFrame f=new JFrame("Button Example");
        JLabel l = new JLabel();
        l.setText("Enter Text");
        l.setBounds(50,50, 150,20);
        final JTextField tf=new JTextField();
        JLabel l2 = new JLabel();
        l2.setText("Encrypted Text");
        l2.setBounds(50,80, 150,20);
        JLabel l3 = new JLabel();
        l3.setText("Original Text: ");
        l3.setBounds(50,210, 150,20);
        JLabel l4 = new JLabel();
        l4.setText("decrypted text");
        l4.setBounds(150,210, 150,20);
        final  JTextField tf2=new  JTextField();
        tf.setBounds(150,50, 370,20);
        tf2.setBounds(150,80, 370,20);
        JButton aesb1=new JButton("AES Encrypt");
        aesb1.setBounds(50,120,95,30);
        aesb1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String plainText = tf.getText();
                SymmetricClass sym = new SymmetricClass();
                SecretKey secretKey = null;
                try {
                    secretKey = sym.createAESKey();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                byte[] cipherText = new byte[0];
                byte[] initializationVector = sym.createInitializationVector();
                try {
                    cipherText = sym.aesEncryption(plainText, secretKey, initializationVector);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                tf2.setText(DatatypeConverter.printHexBinary(cipherText));
            }
        });
        JButton aesb2=new JButton("AES Decrypt");
        aesb2.setBounds(170,120,95,30);
        aesb2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String plainText = tf.getText();
                SymmetricClass sym = new SymmetricClass();
                SecretKey secretKey = null;
                try {
                    secretKey = sym.createAESKey();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                byte[] cipherText = new byte[0];
                byte[] initializationVector = sym.createInitializationVector();
                try {
                    cipherText = sym.aesEncryption(plainText, secretKey, initializationVector);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    String decryptedText = sym.aesDecryption(cipherText, secretKey, initializationVector);
                    l4.setText(decryptedText);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        JButton rSAb1=new JButton("RSA Encrypt");
        rSAb1.setBounds(50,170,95,30);
        rSAb1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                String plainText = tf.getText();
                l4.setText(" ");
                AsymmetricClass asy = new AsymmetricClass();
                KeyPair keypair = null;
                try {
                    keypair = asy.generateRSAKKeyPair();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {
                    byte[] cipherTextRSA = asy.rsaEncryption(plainText, keypair.getPrivate());
                    tf2.setText(DatatypeConverter.printHexBinary(cipherTextRSA));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        JButton rSAb2=new JButton("RSA Decrypt");
        rSAb2.setBounds(170,170,95,30);
        rSAb2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String plainText = tf.getText();
                AsymmetricClass asy = new AsymmetricClass();
                KeyPair keypair = null;
                try {
                    keypair = asy.generateRSAKKeyPair();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {
                    byte[] cipherTextRSA = asy.rsaEncryption(plainText, keypair.getPrivate());
                    String decryptedTextRSA = asy.rsaDecryption(cipherTextRSA, keypair.getPublic());
                    l4.setText(decryptedTextRSA);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        f.add(aesb1); f.add(aesb2); f.add(rSAb1); f.add(rSAb2);f.add(tf);f.add(tf2);f.add(l);f.add(l2);f.add(l3);f.add(l4);
        f.setSize(600,400);
        f.setLayout(null);
        f.setVisible(true);

    }
}
