package cc.devclub.ftd.hmac;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

/**
 * @Author: 【我是开发者FTD】公众号 微信号：ForTheDevelopers
 * @Description: Commons Codec 提供的 HMAC 算法实现
 */
public class CCHmacCoder {

    // 获取 HMAC Key
    public static byte[] getHmacKey(String algorithm) {
        try {
            // 1、创建密钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            // 2、产生密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 3、获取密钥
            byte[] key = secretKey.getEncoded();

            return key;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // HMAC 加密
    public static String encryptHmac(byte[] data, byte[] key, String algorithm) {
        Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_MD5, key);
        return Hex.encodeHexString(mac.doFinal(data));
    }

    public static void main(String[] args) {
        byte[] data = "欢迎关注【我是开发者FTD】公众号".getBytes();
        // MD5
        byte[] hmacMd5KeyBytes = getHmacKey(HmacAlgorithms.HMAC_MD5.getName());
        String hexHamcMd5Key = Hex.encodeHexString(hmacMd5KeyBytes);
        System.out.println("HMAC Md5 密钥:" + hexHamcMd5Key);
        String hmacMd5Encrypt = encryptHmac(data, hmacMd5KeyBytes, HmacAlgorithms.HMAC_MD5.getName());
        System.out.println("HMAC Md5 加密:" + hmacMd5Encrypt);
        // SHA1
        byte[] hmacSha1KeyBytes = getHmacKey(HmacAlgorithms.HMAC_SHA_1.getName());
        String hexHamcSha1Key = Hex.encodeHexString(hmacSha1KeyBytes);
        System.out.println("HMAC SHA1 密钥:" + hexHamcSha1Key);
        String hmacSha1Encrypt = encryptHmac(data, hmacSha1KeyBytes, HmacAlgorithms.HMAC_SHA_1.getName());
        System.out.println("HMAC SHA1 加密:" + hmacSha1Encrypt);
        // SHA224
        byte[] hmacSha224KeyBytes = getHmacKey(HmacAlgorithms.HMAC_SHA_224.getName());
        String hexHamcSha224Key = Hex.encodeHexString(hmacSha224KeyBytes);
        System.out.println("HMAC SHA224 密钥:" + hexHamcSha224Key);
        String hmacSha224Encrypt = encryptHmac(data, hmacSha224KeyBytes, HmacAlgorithms.HMAC_SHA_224.getName());
        System.out.println("HMAC SHA224 加密:" + hmacSha224Encrypt);
        // SHA256
        byte[] hmacSha256KeyBytes = getHmacKey(HmacAlgorithms.HMAC_SHA_256.getName());
        String hexHamcSha256Key = Hex.encodeHexString(hmacSha256KeyBytes);
        System.out.println("HMAC SHA256 密钥:" + hexHamcSha256Key);
        String hmacSha256Encrypt = encryptHmac(data, hmacSha256KeyBytes, HmacAlgorithms.HMAC_SHA_256.getName());
        System.out.println("HMAC SHA256 加密:" + hmacSha256Encrypt);
        // SHA384
        byte[] hmacSha384KeyBytes = getHmacKey(HmacAlgorithms.HMAC_SHA_384.getName());
        String hexHamcSha384Key = Hex.encodeHexString(hmacSha384KeyBytes);
        System.out.println("HMAC SHA384 密钥:" + hexHamcSha384Key);
        String hmacSha384Encrypt = encryptHmac(data, hmacSha384KeyBytes, HmacAlgorithms.HMAC_SHA_384.getName());
        System.out.println("HMAC SHA384 加密:" + hmacSha384Encrypt);
        // SHA512
        byte[] hmacSha512KeyBytes = getHmacKey(HmacAlgorithms.HMAC_SHA_512.getName());
        String hexHamcSha512Key = Hex.encodeHexString(hmacSha512KeyBytes);
        System.out.println("HMAC SHA512 密钥:" + hexHamcSha512Key);
        String hmacSha512Encrypt = encryptHmac(data, hmacSha512KeyBytes, HmacAlgorithms.HMAC_SHA_512.getName());
        System.out.println("HMAC SHA512 加密:" + hmacSha512Encrypt);
    }
}
