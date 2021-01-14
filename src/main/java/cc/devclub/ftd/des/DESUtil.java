package cc.devclub.ftd.des;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 对称加密算法 - DES/3DES
 * <p>
 * DES 共有五种工作模式-->>
 * <p>
 * ECB：电子密码本模式 -- [仅提供该模式实现]
 * CBC：加密分组链接模式
 * CFB：加密反馈模式
 * OFB：输出反馈模式
 * CTR：计数器模式
 * <p>
 *
 * @Author: 【我是开发者FTD】公众号 微信号：ForTheDevelopers
 * @Description: DES 算法实现
 */
public class DESUtil {

    private static final String DES_ALGORITHM = "DES";

    private static final String DES_CIPHER = "DES/ECB/NoPadding";

    /**
     * des 加密方法
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptDES2Base64Str(String data, String key) throws Exception {
        return Base64.encodeBase64String(encryptDES(data, key));
    }

    public static byte[] encryptDES(String data, String key) throws Exception {
        // 生成SecretKey对象
        SecretKey secretKey = desKeyGenerator(key);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES_CIPHER);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // 执行加密操作
        return cipher.doFinal(data.getBytes());
    }

    /**
     * des 解密方法
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptDESFromBase64Str(String data, String key) throws Exception {
        byte[] enbytes = Base64.decodeBase64(data);
        byte[] debytes = decryptDES(enbytes, key);
        return new String(debytes).trim();
    }

    public static byte[] decryptDES(byte[] data, String key) throws Exception {
        // 生成SecretKey对象
        SecretKey secretKey = desKeyGenerator(key);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES_CIPHER);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // 执行解密操作
        return cipher.doFinal(data);
    }

    /**
     * DES Key 生成方法
     *
     * @param key
     * @return
     */
    private static SecretKey desKeyGenerator(String key) {
        try {
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
            // 将DESKeySpec对象转换成SecretKey对象
            return keyFactory.generateSecret(desKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Nopadding填充时,明文不是8位的倍数时需补足
     *
     * @param b
     * @return
     */
    private static byte[] complementZero(byte[] b) {
        int len = b.length;

        //data不足8位以0补足8位
        if (b.length % 8 != 0) {
            len = b.length - b.length % 8 + 8;
        } else {
            return b;
        }
        byte[] needData = null;
        needData = new byte[len];
        for (int i = 0; i < len; i++) {
            needData[i] = 0x00;
        }
        System.arraycopy(b, 0, needData, 0, b.length);

        return needData;
    }

    public static void main(String[] args) throws Exception {
        String key = "12345678";
        String data = "欢迎关注【我是开发者FTD】公众号，微信号：ForTheDevelopers";

        System.out.println("des加密:");
        byte[] b = complementZero(data.getBytes());
        String enryptStr = encryptDES2Base64Str(new String(b), key);
        System.out.println(enryptStr);
        System.out.println("des解密:");
        String deStr = decryptDESFromBase64Str(enryptStr, key);
        System.out.println(deStr);
    }
}