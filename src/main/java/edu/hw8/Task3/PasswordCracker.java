package edu.hw8.Task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Map;

public interface PasswordCracker {
    String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";

    static String md5Hash(String plain) {
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plain.getBytes());
            final byte[] digest = md.digest();
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    String crackSingle(String hash);

    Map<String, String> crackDict(Map<String, String> hashDict);

}
