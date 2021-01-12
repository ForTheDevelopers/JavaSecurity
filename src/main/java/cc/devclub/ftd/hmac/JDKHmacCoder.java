package cc.devclub.ftd.hmac;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author: 【我是开发者FTD】公众号 微信号：ForTheDevelopers
 * @Description: JDK 提供的 HMAC 算法实现
 */
public class JDKHmacCoder {

    private static final String HMAC_MD5_ALGORITHM = "HmacMD5";
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private static final String HMAC_SHA224_ALGORITHM = "HmacSHA224";
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    private static final String HMAC_SHA384_ALGORITHM = "HmacSHA384";
    private static final String HMAC_SHA512_ALGORITHM = "HmacSHA512";

    // 获取 HMAC Key
    public static byte[] getHmacKey(String algorithm) {
        try {
            // 1、创建密钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            // 2、产生密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 3、获取密钥
            byte[] key = secretKey.getEncoded();
            // 4、返回密钥
            return key;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 基础MAC 算法
    public static String encryptHmac(byte[] data, byte[] key, String algorithm) {
        try {
            // 1、还原密钥
            SecretKey secretKey = new SecretKeySpec(key, algorithm);
            // 2、创建MAC对象
            Mac mac = Mac.getInstance(algorithm);
            // 3、设置密钥
            mac.init(secretKey);
            // 4、数据加密
            byte[] bytes = mac.doFinal(data);
            // 5、生成数据
            String rs = encodeHex(bytes);
            // 6、返回十六进制加密数据
            return rs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 数据准16进制编码
    public static String encodeHex(final byte[] data) {
        return encodeHex(data, true);
    }

    // 数据转16进制编码
    public static String encodeHex(final byte[] data, final boolean toLowerCase) {
        final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        final char[] toDigits = toLowerCase ? DIGITS_LOWER : DIGITS_UPPER;
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }

        return new String(out);
    }

    public static void main(String[] args) {
        byte[] data = "欢迎关注【我是开发者FTD】公众号".getBytes();
        // MD5
        byte[] hmacMd5KeyBytes = getHmacKey(HMAC_MD5_ALGORITHM);
        String hexHamcMd5Key = encodeHex(hmacMd5KeyBytes);
        System.out.println("HMAC Md5 密钥:" + hexHamcMd5Key);
        String hmacMd5Encrypt = encryptHmac(data, hmacMd5KeyBytes, HMAC_MD5_ALGORITHM);
        System.out.println("HMAC Md5 加密后:" + hmacMd5Encrypt);
        // SHA1
        byte[] hmacSha1KeyBytes = getHmacKey(HMAC_SHA1_ALGORITHM);
        String hexHamcSha1Key = encodeHex(hmacSha1KeyBytes);
        System.out.println("HMAC SHA1 密钥:" + hexHamcSha1Key);
        String hmacSha1Encrypt = encryptHmac(data, hmacSha1KeyBytes, HMAC_SHA1_ALGORITHM);
        System.out.println("HMAC SHA1 加密后:" + hmacSha1Encrypt);
        // SHA224
        byte[] hmacSha224KeyBytes = getHmacKey(HMAC_SHA224_ALGORITHM);
        String hexHamcSha224Key = encodeHex(hmacSha224KeyBytes);
        System.out.println("HMAC SHA224 密钥:" + hexHamcSha224Key);
        String hmacSha224Encrypt = encryptHmac(data, hmacSha224KeyBytes, HMAC_SHA224_ALGORITHM);
        System.out.println("HMAC SHA224 加密后:" + hmacSha224Encrypt);
        // SHA256
        byte[] hmacSha256KeyBytes = getHmacKey(HMAC_SHA256_ALGORITHM);
        String hexHamcSha256Key = encodeHex(hmacSha256KeyBytes);
        System.out.println("HMAC SHA256 密钥:" + hexHamcSha256Key);
        String hmacSha256Encrypt = encryptHmac(data, hmacSha256KeyBytes, HMAC_SHA256_ALGORITHM);
        System.out.println("HMAC SHA256 加密后:" + hmacSha256Encrypt);
        // SHA384
        byte[] hmacSha384KeyBytes = getHmacKey(HMAC_SHA384_ALGORITHM);
        String hexHamcSha384Key = encodeHex(hmacSha384KeyBytes);
        System.out.println("HMAC SHA384 密钥:" + hexHamcSha384Key);
        String hmacSha384Encrypt = encryptHmac(data, hmacSha384KeyBytes, HMAC_SHA384_ALGORITHM);
        System.out.println("HMAC SHA384 加密后:" + hmacSha384Encrypt);
        // SHA512
        byte[] hmacSha512KeyBytes = getHmacKey(HMAC_SHA512_ALGORITHM);
        String hexHamcSha512Key = encodeHex(hmacSha512KeyBytes);
        System.out.println("HMAC SHA512 密钥:" + hexHamcSha512Key);
        String hmacSha512Encrypt = encryptHmac(data, hmacSha512KeyBytes, HMAC_SHA512_ALGORITHM);
        System.out.println("HMAC SHA512 加密后:" + hmacSha512Encrypt);
    }
}

