package io.github.motolies.util.base64;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


/**
 * The type Base 64 web safe util.
 */
public class Base64WebSafeUtil {

    private static final Base64.Encoder bEncode = java.util.Base64.getEncoder();

    /**
     * String to WebSafe Base64 String
     *
     * @param message String
     * @return byte[] string
     */
    public static String encode(String message) {
        return encode(message.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Byte array to WebSafe Base64 String
     *
     * @param message byte[]
     * @return String
     */
    public static String encode(byte[] message) {
        // https://gist.github.com/geraintluff/21beb1066fc5239304aa
        String enc = bEncode.encodeToString(message);
        return StringUtils.stripEnd(enc, "=").replaceAll("\\+", "-").replaceAll("/", "_");
    }

    /**
     * WebSafe Base64 String to byte array
     *
     * @param message WebSafe Base64 String
     * @return byte[]
     */
    public static byte[] decodeToBytes(String message) {
        return Base64.getDecoder().decode(decodeToNormal(message));
    }

    /**
     * WebSafe Base64 String to normal Base64 String
     *
     * @param message WebSafe Base64 String
     * @return String
     */
    public static String decodeToNormal(String message) {
        return message.replaceAll("-", "+").replaceAll("_", "/") + "==".substring(0, (message.length() * 3) % 4);
    }


}