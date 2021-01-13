package cc.devclub.ftd.hmac;

import org.bouncycastle.crypto.digests.*;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * 消息摘要算法 - HMAC（Keyed-Hashing for Message Authentication）
 *
 * @Author: 【我是开发者FTD】公众号 微信号：ForTheDevelopers
 * @Description: Bouncy Castle 提供的 HMAC 算法实现
 */
public class BCHmacCoder {

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

    // HMAC 加密
    public static String encryptHmac(byte[] data, byte[] key, String algorithm) {
        HMac hmac = generateHmacByAlgorithm(algorithm);
        KeyParameter keyParameter = new KeyParameter(key);
        hmac.init(keyParameter);
        hmac.update(data, 0, data.length);
        byte[] rsData = new byte[hmac.getMacSize()];
        hmac.doFinal(rsData, 0);

        return Hex.toHexString(rsData);
    }

    private static HMac generateHmacByAlgorithm(String algorithm) {
        if (HMAC_MD5_ALGORITHM.equals(algorithm)) {
            return new HMac(new MD5Digest());
        } else if (HMAC_SHA1_ALGORITHM.equals(algorithm)) {
            return new HMac(new SHA1Digest());
        } else if (HMAC_SHA224_ALGORITHM.equals(algorithm)) {
            return new HMac(new SHA224Digest());
        } else if (HMAC_SHA256_ALGORITHM.equals(algorithm)) {
            return new HMac(new SHA256Digest());
        } else if (HMAC_SHA384_ALGORITHM.equals(algorithm)) {
            return new HMac(new SHA384Digest());
        } else if (HMAC_SHA512_ALGORITHM.equals(algorithm)) {
            return new HMac(new SHA512Digest());
        } else {
            throw new RuntimeException("do not support the algorithm!");
        }
    }

    public static void main(String[] args) {
        byte[] data = "欢迎关注【我是开发者FTD】公众号，微信号：ForTheDevelopers".getBytes();
        // MD5
        byte[] hmacMd5KeyBytes = getHmacKey(HMAC_MD5_ALGORITHM);
        String hexHamcMd5Key = Hex.toHexString(hmacMd5KeyBytes);
        System.out.println("HMAC Md5 密钥:" + hexHamcMd5Key);
        String hmacMd5Encrypt = encryptHmac(data, hmacMd5KeyBytes, HMAC_MD5_ALGORITHM);
        System.out.println("HMAC Md5 加密后:" + hmacMd5Encrypt);
        // SHA1
        byte[] hmacSha1KeyBytes = getHmacKey(HMAC_SHA1_ALGORITHM);
        String hexHamcSha1Key = Hex.toHexString(hmacSha1KeyBytes);
        System.out.println("HMAC SHA1 密钥:" + hexHamcSha1Key);
        String hmacSha1Encrypt = encryptHmac(data, hmacSha1KeyBytes, HMAC_SHA1_ALGORITHM);
        System.out.println("HMAC SHA1 加密后:" + hmacSha1Encrypt);
        // SHA224
        byte[] hmacSha224KeyBytes = getHmacKey(HMAC_SHA224_ALGORITHM);
        String hexHamcSha224Key = Hex.toHexString(hmacSha224KeyBytes);
        System.out.println("HMAC SHA224 密钥:" + hexHamcSha224Key);
        String hmacSha224Encrypt = encryptHmac(data, hmacSha224KeyBytes, HMAC_SHA224_ALGORITHM);
        System.out.println("HMAC SHA224 加密后:" + hmacSha224Encrypt);
        // SHA256
        byte[] hmacSha256KeyBytes = getHmacKey(HMAC_SHA256_ALGORITHM);
        String hexHamcSha256Key = Hex.toHexString(hmacSha256KeyBytes);
        System.out.println("HMAC SHA256 密钥:" + hexHamcSha256Key);
        String hmacSha256Encrypt = encryptHmac(data, hmacSha256KeyBytes, HMAC_SHA256_ALGORITHM);
        System.out.println("HMAC SHA256 加密后:" + hmacSha256Encrypt);
        // SHA384
        byte[] hmacSha384KeyBytes = getHmacKey(HMAC_SHA384_ALGORITHM);
        String hexHamcSha384Key = Hex.toHexString(hmacSha384KeyBytes);
        System.out.println("HMAC SHA384 密钥:" + hexHamcSha384Key);
        String hmacSha384Encrypt = encryptHmac(data, hmacSha384KeyBytes, HMAC_SHA384_ALGORITHM);
        System.out.println("HMAC SHA512 加密后:" + hmacSha384Encrypt);
        // SHA512
        byte[] hmacSha512KeyBytes = getHmacKey(HMAC_SHA512_ALGORITHM);
        String hexHamcSha512Key = Hex.toHexString(hmacSha512KeyBytes);
        System.out.println("HMAC SHA512 密钥:" + hexHamcSha512Key);
        String hmacSha512Encrypt = encryptHmac(data, hmacSha512KeyBytes, HMAC_SHA512_ALGORITHM);
        System.out.println("HMAC SHA512 加密后:" + hmacSha512Encrypt);
    }
}
