package com.manyi.business.pay.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;

/**
 * Author: WangPengfei
 * Review:
 * Data: 2015/5/29.
 */
public class DESedeEncrypt {

    private static final Logger logger = LoggerFactory.getLogger(DESedeEncrypt.class);

    private static final String DEFAULT_ENCODING = "UTF-8";

    private static final int ONE_HUNDRED_SIXTY_EIGHT = 168;

    private static final int EIGHT = 8;

    private static final int ONE = 1;

    private static final int TWO = 2;

    private static final int TEN = 10;

    private static final int SIXTEEN = 16;

    public static final String KEY_ALGORITHM = "DESede";

    public static String generateKey(String seed)
            throws Exception {
        return generateKey(seed, "UTF-8");
    }

    public static String generateKey(String seed, String encoding)
            throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("DESede");
        generator.init(ONE_HUNDRED_SIXTY_EIGHT, new SecureRandom(seed.getBytes(encoding)));
        SecretKey key = generator.generateKey();
        byte[] buffer = key.getEncoded();
        return byte2hex(buffer).toUpperCase();
    }

    public static String encrypt(String strKey, String strIn) {
        byte[] bKey = hex2byte(strKey.getBytes());
        SecretKeySpec key = new SecretKeySpec(bKey, "DESede");
        try {
            Cipher encryptCipher = Cipher.getInstance("DESede/ECB/NoPadding");
            encryptCipher.init(ONE, key);

            if ((strIn == null) || ("".equals(strIn))) {
                return "";
            }

            byte[] bData = strIn.getBytes("UTF-8");

            int iLenOfSourceData = bData.length;
            int iMod = iLenOfSourceData % EIGHT;
            byte[] bCryptData = (byte[]) null;

            if (iMod == 0) {
                bCryptData = (byte[]) bData.clone();
            } else {
                bCryptData = new byte[iLenOfSourceData + EIGHT - iMod];
                System.arraycopy(bData, 0, bCryptData, 0, iLenOfSourceData);

                for (int i = 0; i < EIGHT - iMod; i++) {
                    bCryptData[(iLenOfSourceData + i)] = TEN;
                }
            }

            return byte2hex(encryptCipher.doFinal(bCryptData));
        } catch (NoSuchPaddingException e) {
            logger.error("DESCedeEncrypt encrypt is wrong, NoSuchPaddingException=" + e);
        } catch (BadPaddingException e) {
            logger.error("DESCedeEncrypt encrypt is wrong, BadPaddingException=" + e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("DESCedeEncrypt encrypt is wrong, NoSuchAlgorithmException=" + e);
        } catch (IllegalBlockSizeException e) {
            logger.error("DESCedeEncrypt encrypt is wrong, IllegalBlockSizeException=" + e);
        } catch (UnsupportedEncodingException e) {
            logger.error("DESCedeEncrypt encrypt is wrong, UnsupportedEncodingException=" + e);
        } catch (InvalidKeyException e) {
            logger.error("DESCedeEncrypt encrypt is wrong, InvalidKeyException=" + e);
        }
        return null;
    }

    public static String decrypt(String strKey, String strIn)
            throws Exception {
        byte[] bKey = hex2byte(strKey.getBytes());
        SecretKeySpec key = new SecretKeySpec(bKey, "DESede");
        Cipher decryptCipher = Cipher.getInstance("DESede/ECB/NoPadding");
        decryptCipher.init(TWO, key);
        return new String(decryptCipher.doFinal(hex2byte(strIn.getBytes())), "UTF-8").trim();
    }

    public static final String byte2hex(byte[] b) {
        StringBuilder sb = new StringBuilder();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1) {
                sb.append("0").append(stmp);
            } else {
                sb.append(stmp);
            }
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }

    public static final byte[] hex2byte(byte[] b) {
        if (b.length % TWO != 0) {
            throw new IllegalArgumentException("The byte Array's length is not even!");
        }
        byte[] b2 = new byte[b.length / TWO];
        for (int n = 0; n < b.length; n += TWO) {
            String item = new String(b, n, TWO);
            b2[(n / TWO)] = (byte) Integer.parseInt(item, SIXTEEN);
        }
        return b2;
    }

}
