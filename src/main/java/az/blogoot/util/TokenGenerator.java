package az.blogoot.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

public class TokenGenerator {
    private static String getSHA(String text) {
        StringBuffer hexString = new StringBuffer();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(text.getBytes(StandardCharsets.UTF_8));

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hexString.toString();

    }

    public static String generateToken(){
        UUID uuid = UUID.randomUUID();
        return getSHA(uuid.toString());
    }

}