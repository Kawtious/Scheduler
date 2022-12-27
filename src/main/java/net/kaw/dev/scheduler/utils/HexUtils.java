/**
 * Copyright 2012, Z.Alem
 * License: http://opensource.org/licenses/bsd-license.php The BSD License
 */
package net.kaw.dev.scheduler.utils;

import java.math.BigInteger;

/**
 * Converts representation of binary data between byte arrays and hexadecimal char arrays.
 *
 * @author Z. Alem <info@alemcode.com> {@link http://alemcode.com}
 * @since HexEditor1.0
 */
public class HexUtils {

    /**
     * Stores charaters of the hexadecimal numeral system
     */
    private final static char[] hex_digits = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    /**
     * Returns hexdecimal equivalent of byte array
     * <p>
     * Creates a char array with 2:1 ratio to byte array ( 2 hex digits : 1 byte ) Masks (and shifts) to get value of left and right bit quartet in byte
     *
     * @param byte[]	bytes
     * @return char[]	Hexadecimal char array
     */
    public static char[] bytesToHex(byte[] bytes) {
        int bytes_length = bytes.length;
        char[] hex_form = new char[bytes_length * 2];

        for (int i = 0, j = 0; i < bytes_length; i++, j = i * 2) {
            hex_form[j] = hex_digits[(bytes[i] & 0xf0) >>> 4];
            hex_form[j + 1] = hex_digits[(bytes[i] & 0x0f)];
        }

        return hex_form;
    }

    /**
     * Turns a char array containing hex data into a byte array
     * <p>
     * Calculates hexadecimal pair's integer value (base 10), and type casts to byte
     *
     * @param char[]	hex_chars
     * @return byte[]	Hexadecimal char array
     */
    public static byte[] hexToBytes(char[] hex_chars) {
        int hex_length = hex_chars.length;
        int byte_length = hex_length / 2;
        byte[] byte_form = new byte[byte_length];

        for (int i = 0, j = 0; i < byte_length; i++, j = i * 2) {
            int most_sig = hexToDec(hex_chars[j], 1);
            int least_sig = hexToDec(hex_chars[j + 1], 0);
            byte_form[i] = (byte) (most_sig + least_sig);
        }
        return byte_form;
    }

    /**
     * Converts hex character to decimal (integer) value
     *
     * @param hex_char The hex character to convert
     * @param position Character position or distance from radix point. (e.g. for C3, C is at 1 and 3 is at 0 )
     */
    public static int hexToDec(char hex_char, int position) {
        return Character.digit(hex_char, 16) << position * 4;
    }

    public static int hexToInt(String hex) {
        return Integer.parseInt(hex, 16);
    }

    private static String toHexPadded(String str, int padding, boolean backPadding) {
        StringBuilder sb = new StringBuilder();

        if (!backPadding) {
            sb.append(str);

            for (int i = str.length(); i < padding; i++) {
                sb.append("0");
            }
        } else {
            for (int i = 0; i < padding - str.length(); i++) {
                sb.append("0");
            }

            sb.append(str);
        }

        return sb.toString();
    }

    public static String intToHex(int val, int padding) {
        String intString = Integer.toHexString(val);
        return toHexPadded(intString, padding, true);
    }

    public static String stringToHex(String str) {
        return String.format("%x", new BigInteger(1, str.getBytes(/*YOUR_CHARSET?*/))) + "00";
    }

    public static String stringToHex(String str, int padding) {
        // This behaves a little differently than the other stringToHex method
        String hexStr = stringToHex(str);
        return toHexPadded(hexStr, padding, false);
    }

    public static String booleanToHex(boolean value, int padding) {
        // true: 1, false: 0
        int intValue = value ? 1 : 0;
        return intToHex(intValue, padding);
    }

}
