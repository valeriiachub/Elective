package ua.nure.chub.Elective.server.other;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Author Lera
 * created 07.09.2017.
 */
public class Hash {
    public static String encode(String msg) throws NoSuchAlgorithmException {
        msg = msg == null ? "" : msg;
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        digest.update(msg.getBytes());
        return hash2str(digest.digest());
    }

    public static String hash2str(byte[] hash) {
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%1$02X", b & 0xFF));
        }
        return sb.toString().toUpperCase();
    }
}
