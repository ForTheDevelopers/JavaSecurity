package cc.devclub.ftd.des;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * 对称加密算法 - DES/3DES
 * <p>
 * 3DES 共有五种工作模式-->>
 * <p>
 * ECB：电子密码本模式 -- [仅提供该模式实现]
 * CBC：加密分组链接模式
 * CFB：加密反馈模式
 * OFB：输出反馈模式
 * CTR：计数器模式
 * <p>
 * 3DES又称Triple encryptDES，是DES加密算法的一种模式
 * 它使用3条56位的密钥对，3DES更安全
 *
 * @Author: 【我是开发者FTD】公众号 微信号：ForTheDevelopers
 * @Description: 3DES 算法实现
 */
public class ThreeDESUtil {

    private static final String DESEDE_ALGORITHM = "DESede";

    private static final String DESEDE_CIPHER = "DESede/ECB/NoPadding";

    /**
     * 3des 加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt3DES2Base64Str(String data, String key) throws Exception {
        return Base64.encodeBase64String(encrypt3DES(data, key));
    }

    public static byte[] encrypt3DES(String data, String key) throws Exception {
        // 生成 SecretKey 对象
        SecretKey secretKey = desEdeKeyGenerator(key);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DESEDE_CIPHER);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // 执行加密操作
        return cipher.doFinal(data.getBytes());
    }

    /**
     * 3des 解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt3DESFromBase64Str(String data, String key) throws Exception {
        byte[] enbytes = Base64.decodeBase64(data);
        byte[] debytes = decrypt3DES(enbytes, key);
        return new String(debytes).trim();
    }

    public static byte[] decrypt3DES(byte[] data, String key) throws Exception {
        // 生成 SecretKey 对象
        SecretKey secretKey = desEdeKeyGenerator(key);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DESEDE_CIPHER);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // 执行解密操作
        return cipher.doFinal(data);
    }

    /**
     * 3DES Key 生成方法
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static SecretKey desEdeKeyGenerator(String key) throws Exception {
        // 根据传入的key和3des算法的要求构建新key
        byte[] b_key = build3DesKey(key);
        // 从原始密匙数据创建DESedeKeySpec对象
        DESedeKeySpec dks = new DESedeKeySpec(b_key);
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DESEDE_ALGORITHM);
        // 把DESedeKeySpec转换成一个SecretKey对象
        return keyFactory.generateSecret(dks);
    }

    /**
     * 3DES的密钥必须是24位的byte数组
     *
     * @param keyStr
     * @return
     */
    public static byte[] build3DesKey(String keyStr) {
        // 声明一个24位的字节数组，默认里面都是0
        byte[] key = new byte[24];
        // 将字符串转成字节数组
        byte[] temp = keyStr.getBytes();

        // 执行数组拷贝System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
        if (key.length > temp.length) {
            //如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, temp.length);
        } else {
            //如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, key.length);
        }

        return key;
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
        String key = "123456781234567812345678";
        String data = "欢迎关注【我是开发者FTD】公众号，微信号：ForTheDevelopers";

        System.out.println("3des加密:");
        byte[] b = complementZero(data.getBytes());
        String encryptStr = encrypt3DES2Base64Str(new String(b), key);
        System.out.println(encryptStr);
        System.out.println("3des解密:");
        String decryptStr = decrypt3DESFromBase64Str(encryptStr, key);
        System.out.println(decryptStr);
    }
}