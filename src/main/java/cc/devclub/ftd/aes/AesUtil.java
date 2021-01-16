package cc.devclub.ftd.aes;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 对称加密算法 - AES
 *
 * @Author: 【我是开发者FTD】公众号 微信号：ForTheDevelopers
 * @Description: AES128 算法，加密模式为ECB，填充模式为pkcs5
 */
public class AesUtil {

    /**
     * 加密算法AES
     */
    public static final String AES_ALGORITHM = "AES";

    public static final String AES_CIPTHER = "AES/ECB/PKCS5Padding";

    private static final String CHAR_ENCODING = "UTF-8";

    /**
     * AES 加密
     *
     * @param data 待加密密内容
     * @param key  加密密钥
     * @return
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        if (key.length != 16) {
            throw new RuntimeException("Invalid AES key length (must be 16 bytes)");
        }
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, AES_ALGORITHM);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(AES_CIPTHER);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] result = cipher.doFinal(data);

            return result;
        } catch (Exception e) {
            throw new RuntimeException("encrypt fail!", e);
        }
    }

    /**
     * AES 解密
     *
     * @param data 待解密内容
     * @param key  解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        if (key.length != 16) {
            throw new RuntimeException("Invalid AES key length (must be 16 bytes)");
        }
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, AES_ALGORITHM);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(AES_CIPTHER);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] result = cipher.doFinal(data);

            return result;
        } catch (Exception e) {
            throw new RuntimeException("decrypt fail!", e);
        }
    }

    /**
     * AES 加密
     *
     * @param data 待加密数据
     * @param key  base64后的密钥
     * @return
     */
    public static String encrypt2Base64Str(String data, String key) {
        try {
            byte[] valueByte = encrypt(data.getBytes(CHAR_ENCODING), Base64.decodeBase64(key));
            return Base64.encodeBase64String(valueByte);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("AES Encrypt Fail!", e);
        }
    }

    /**
     * AES 解密
     *
     * @param data base64后的加密数据
     * @param key  base64后的密钥
     * @return
     */
    public static String decryptFromBase64Str(String data, String key) {
        try {
            byte[] originalData = Base64.decodeBase64(data);
            byte[] valueByte = decrypt(originalData, Base64.decodeBase64(key));
            return new String(valueByte, CHAR_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("AES Decrypt Fail!", e);
        }
    }

    /**
     * 生成随机AES密钥,密钥长度128位
     *
     * @return AES明文密钥Base64字符串
     */
    public static String genAesRandomKey2Base64Str() {
        return Base64.encodeBase64String(genAesRandomKey());
    }

    /**
     * 生成随机AES密钥,密钥长度128位
     *
     * @return AES明文密钥字节数组
     */
    public static byte[] genAesRandomKey() {
        KeyGenerator keygen = null;
        try {
            keygen = KeyGenerator.getInstance(AES_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("genarateRandomKey fail!", e);
        }
        SecureRandom random = new SecureRandom();
        keygen.init(random);
        Key key = keygen.generateKey();

        return key.getEncoded();
    }

    public static void main(String[] args) {
        String data = "欢迎关注【我是开发者FTD】公众号，微信号：ForTheDevelopers";
        String key = genAesRandomKey2Base64Str();

        String eb = encrypt2Base64Str(data, key);
        System.out.println("加密后的数据:" + eb);
        String db = decryptFromBase64Str(eb, key);
        System.out.println("解密后的数据:" + db);
    }
}
