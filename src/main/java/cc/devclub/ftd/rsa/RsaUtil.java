package cc.devclub.ftd.rsa;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 非对称加密算法 - RSA
 *
 * @Author: 【我是开发者FTD】公众号 微信号：ForTheDevelopers
 * @Description: RSA 算法实现
 */
public class RsaUtil {

    /**
     * 加密算法RSA
     */
    private static final String RSA_ALGORITHM = "RSA";

    /**
     * 加密模式
     */
    private static final String RSA_CIPTHER = "RSA/ECB/PKCS1Padding";

    /**
     * 签名算法
     */
    private static final String SIGNATURE_ALGORITHM = "SHA256WithRSA";

    /**
     * 指定RSA Key的大小, 当前使用的密钥长度为2048位
     */
    private static int RSA_KEY_SIZE = 2048;


    /********************************* RSA 加密相关方法 ******************************/

    /**
     * RSA加密,通过【公钥】加密
     *
     * @param pubKey base64后的公钥
     * @param data   明文数据
     * @return 经过Base64编码后的字符串
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt2Base64ByPubKey(String pubKey, String data)
            throws Exception {
        if (StringUtils.isBlank(pubKey)) {
            throw new Exception("加密公钥不能为空!");
        }

        // 对公钥Base64解码
        byte[] key = Base64.decodeBase64(pubKey);

        // 加密
        byte[] encryptData = encryptByPubKey(key, data.getBytes());

        // 返回base64编码后的密文
        return Base64.encodeBase64String(encryptData);
    }

    /**
     * RSA加密,通过【私钥】加密
     *
     * @param priKey base64后的私钥
     * @param data   明文数据
     * @return 经过Base64编码后的字符串
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt2Base64ByPriKey(String priKey, String data)
            throws Exception {
        if (StringUtils.isBlank(priKey)) {
            throw new Exception("加密私钥不能为空!");
        }

        // base64解码
        byte[] key = Base64.decodeBase64(priKey);

        // 加密
        byte[] encryptData = encryptByPriKey(key, data.getBytes());

        // 返回base64编码后的密文
        return Base64.encodeBase64String(encryptData);
    }

    /**
     * RSA 通过【公钥】加密
     *
     * @param pubKey
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPubKey(byte[] pubKey, byte[] data) throws Exception {
        // 构建key
        Key rsaPubKey = buildPubKey(pubKey);
        // 加密
        return encrypt(rsaPubKey, data);
    }

    /**
     * RSA 通过【私钥】加密
     *
     * @param priKey
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPriKey(byte[] priKey, byte[] data) throws Exception {
        // 构建key
        Key rsaPriKey = buildPriKey(priKey);
        // 加密
        return encrypt(rsaPriKey, data);
    }

    private static byte[] encrypt(Key key, byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_CIPTHER);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptData = cipher.doFinal(data);

        return encryptData;
    }

    /********************************* RSA 解密相关方法 ******************************/

    /**
     * RSA解密,通过【公钥】解密
     *
     * @param pubKey      base64后的公钥密钥
     * @param encryptData 密文数据
     * @return 明文数据
     * @throws Exception 解密过程中的异常信息
     */
    public static String decryptFromBase64ByPubKey(String pubKey, String encryptData)
            throws Exception {
        if (StringUtils.isBlank(pubKey)) {
            throw new Exception("解密公钥为空, 请设置");
        }

        // 对公钥解码
        byte[] key = Base64.decodeBase64(pubKey);
        byte[] data = Base64.decodeBase64(encryptData);

        // 解密获得明文字节数组
        byte[] decryptData = decryptByPubKey(key, data);

        // 返回解密后明文
        return new String(decryptData);
    }

    /**
     * RSA解密,通过【私钥】解密
     *
     * @param priKey      base64编码后的私钥
     * @param encryptData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decryptFromBase64ByPriKey(String priKey, String encryptData)
            throws Exception {
        if (StringUtils.isBlank(priKey)) {
            throw new Exception("解密密钥为空, 请设置");
        }

        byte[] key = Base64.decodeBase64(priKey);
        byte[] data = Base64.decodeBase64(encryptData);

        // 解密获得明文字节数组
        byte[] decryptData = decryptByPriKey(key, data);

        // 返回解码后明文
        return new String(decryptData);
    }

    /**
     * RSA 使用【公钥】解密
     *
     * @param pubKey      密钥
     * @param encryptData 密文数据
     * @return 明文字节数组
     * @throws Exception 解密过程中的异常信息
     */
    public static byte[] decryptByPubKey(byte[] pubKey, byte[] encryptData)
            throws Exception {
        Key rsaPubKey = buildPubKey(pubKey);

        // 返回解密数据
        return decrypt(rsaPubKey, encryptData);
    }

    /**
     * RSA 使用【私钥】解密
     *
     * @param priKey      密钥
     * @param encryptData 密文数据
     * @return 明文字节数组
     * @throws Exception 解密过程中的异常信息
     */
    public static byte[] decryptByPriKey(byte[] priKey, byte[] encryptData)
            throws Exception {

        Key rsaPriKey = buildPriKey(priKey);

        // 返回解密数据
        return decrypt(rsaPriKey, encryptData);
    }

    private static byte[] decrypt(Key key, byte[] encryptData) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_CIPTHER);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptData = cipher.doFinal(encryptData);

        // 返回解密数据
        return decryptData;
    }

    private static Key buildPubKey(byte[] pubKey) throws Exception {
        // 创建KeySpec
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);

        return keyFactory.generatePublic(keySpec);
    }

    private static Key buildPriKey(byte[] priKey) throws Exception {
        // 创建KeySpec
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(priKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);

        return keyFactory.generatePrivate(keySpec);
    }


    /********************************* RSA 密钥生成相关方法 ******************************/
    /**
     * 生成RSA 公私钥,并存储到字符串数组中
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String[] genRSAKeys2Str() throws NoSuchAlgorithmException {
        // 生成密匙对
        KeyPair kp = generateKeyPairs();
        //得到公钥
        Key publicKey = kp.getPublic();
        // 得到私钥
        Key privateKey = kp.getPrivate();
        // 得到公私钥字节数组
        byte[] publicKeyBytes = publicKey.getEncoded();
        byte[] privateKeyBytes = privateKey.getEncoded();
        // 得到base64编码后的公私钥字符串
        String publicKeyStr = Base64.encodeBase64String(publicKeyBytes);
        String privateKeyStr = Base64.encodeBase64String(privateKeyBytes);

        String[] rsaKeys = new String[2];
        rsaKeys[0] = publicKeyStr;
        rsaKeys[1] = privateKeyStr;

        return rsaKeys;
    }

    /**
     * 生成RSA 公私钥,并存储到map中
     *
     * @return
     */
    public static Map<String, String> genRSAKey2Map() throws NoSuchAlgorithmException {
        // 生成密匙对
        KeyPair keyPair = generateKeyPairs();
        // 得到公钥
        String publicKeyStr = Base64.encodeBase64String(keyPair.getPublic().getEncoded());
        // 得到私钥
        String privateKeyStr = Base64.encodeBase64String(keyPair.getPrivate().getEncoded());
        Map<String, String> keyPairMap = new HashMap<>(2);
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

    /**
     * 生成RSA 公私钥,并存储到指定路径的文件中
     *
     * @param file 例如:/opt/export/app/publicKey.keystore
     * @throws NoSuchAlgorithmException
     */
    public static void genRSAKeys2File(String file) throws NoSuchAlgorithmException {
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = generateKeyPairs();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        try {
            FileWriter pubFileWriter = new FileWriter(file);
            FileWriter priFileWriter = new FileWriter(file);
            BufferedWriter pubBufferedWriter = new BufferedWriter(pubFileWriter);
            BufferedWriter priBufferedWriter = new BufferedWriter(priFileWriter);
            // 得到公钥字符串
            String publicKeyString = Base64.encodeBase64String(publicKey.getEncoded());
            // 得到私钥字符串
            String privateKeyString = Base64.encodeBase64String(privateKey.getEncoded());
            // 将密钥对写入到文件
            pubBufferedWriter.write(publicKeyString);
            priBufferedWriter.write(privateKeyString);
            pubBufferedWriter.flush();
            priBufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成密匙对 KeyPairs
     *
     * @return KeyPair keyPair
     * @throws NoSuchAlgorithmException
     */
    private static KeyPair generateKeyPairs() throws NoSuchAlgorithmException {
        // RSA算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        // 利用上面的随机数据源初始化这个KeyPairGenerator对象
        kpg.initialize(RSA_KEY_SIZE, sr);

        return kpg.generateKeyPair();
    }

    /********************************* RSA 签名验签相关方法 ******************************/
    /**
     * 使用RSA 私钥签名（经过Base64编码）
     *
     * @param priKey base64编码过的私钥
     * @param data   明文数据
     * @return base64编码后的字符串
     */
    public static String sign(String priKey, String data) throws Exception {
        try {
            // Base64 解码
            byte[] priKeys = Base64.decodeBase64(priKey);

            RSAPrivateKey privateKey = (RSAPrivateKey) buildPriKey(priKeys);
            Signature sigEng = Signature.getInstance(SIGNATURE_ALGORITHM);
            sigEng.initSign(privateKey);
            sigEng.update(data.getBytes());
            byte[] signature = sigEng.sign();

            // 返回base64编码后的签名串
            return Base64.encodeBase64String(signature);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 使用RSA 公钥验证签名
     *
     * @param pubKey   base64编码的公钥
     * @param signData 私钥签名过的base64编码的签名数据
     * @param data     明文数据
     * @return
     */
    public static boolean verifySign(String pubKey, String data, String signData) {
        try {
            byte[] key = Base64.decodeBase64(pubKey);

            RSAPublicKey rsaPubKey = (RSAPublicKey) buildPubKey(key);
            Signature sigEng = Signature.getInstance(SIGNATURE_ALGORITHM);
            sigEng.initVerify(rsaPubKey);
            sigEng.update(data.getBytes());

            return sigEng.verify(Base64.decodeBase64(signData));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
//        String[] str = genRSAKeys2Str();
//        System.out.println(str[0]);
//        System.out.println(str[1]);
        String data = "欢迎关注【我是开发者FTD】公众号，微信号：ForTheDevelopers";
        String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlU5NEISx+GZpdFYI1zvataubkJ3T/YcjBNur1b8pL/Z36k3I0CT/wQIOm+D6L7YUqQuRpeuly1dafVvnRi5/cctST7B9wM3FksSBhuQNakDTsXfMUtCip0Cj13Zh2/BegIBDc+OF16fz9jCKo28c/53DB2xbb5R2bucxKkjyBgHMI9Bi0XMhWm9iXEyFuGo5ab8KaMid3CmNvNlLHOBhJQ8x0gTMX8XNsBkAC/wxu1Qe4KuW4hw4j8VQVFiQrIPh/fuxyNl6mSTAx0LeTSwhcpA/UCvRW/c5WqHvuOkMCGZCG5ITL+4XleMdHYGB/lL9+wRm2kj6PRVLMnTps6kDHwIDAQAB";
        String priKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCVTk0QhLH4Zml0VgjXO9q1q5uQndP9hyME26vVvykv9nfqTcjQJP/BAg6b4PovthSpC5Gl66XLV1p9W+dGLn9xy1JPsH3AzcWSxIGG5A1qQNOxd8xS0KKnQKPXdmHb8F6AgENz44XXp/P2MIqjbxz/ncMHbFtvlHZu5zEqSPIGAcwj0GLRcyFab2JcTIW4ajlpvwpoyJ3cKY282Usc4GElDzHSBMxfxc2wGQAL/DG7VB7gq5biHDiPxVBUWJCsg+H9+7HI2XqZJMDHQt5NLCFykD9QK9Fb9zlaoe+46QwIZkIbkhMv7heV4x0dgYH+Uv37BGbaSPo9FUsydOmzqQMfAgMBAAECggEAD9vGQbfmc1zlXI7612mrHFrC5kRtTON6q7xtbwGj7U0/hWqHptwXMjf2t0XZLRIo9Z+d88Ko1IVNvjzxvrmXnc2by40SflUc5C7tFxfH+P4zvgjccftpG1y5Slxvnn3Ue8+j2n5TLAnR1tdnEl7t9JScL4Cp4ZsiTTtPNI+U7WA/cejZ4c1a20gcN60mLA/+H+t5RmESRxhI0YB4hKzc9u/l4FAGY0/B6GrtMgHEPa77FN8L709y4LvwCpS/QLZU5+00nwo+RZenffLslYM1KQLr333mgwZNqgXdTjHZN0VMUyMGNIVH5ph6zhxacmla9YDoZqbPHUnpi42TZDZ66QKBgQDlDEy2DIJFNljSvWNiN6sN4as3eSVnbNv7cfIKz5cKIlq75MI6xvw4nLv70pGx254RB9M9Shfv35T6QoSzWrK1B961ha5Kdxd4QcrLm+3WWShbF1X/7CFseyA1WfCQ5hQsCKOGMXRnjk1zT4NYUd3bjNmWtIQNC5M/f7PhH91MAwKBgQCm3+XNQWuFgmJD3o6Z12K+h502kY0Mk8OzQIWSkK73U28bfG8UQDAbTYNHMkhmE73Ska2ffhNIIT77P/PwiWbCTPAiiZmwfQoLOtWmL6a2oAp731R5w81j0/CF45SU1hnoKT4DBqiZRQYcTe6MkufArx5/G9T4OQJms9pWcMoXtQKBgGbTH7j2YqEJpdRtmykjjpH7xi5wH1+P2i3GWH+L8+VjyNHi/2L9o4sSqb2CKU/sJMjGd4ljMt3HxIyYwhP2WpW0g8F/0t+xGQMungjL5ni8q8ZA8qWibtrRZ4Mr7jhOYa3m7uo2V2iJ1LE4d+F1VyDR2XizbzigAqmV/b3J3UHbAoGAQghE+dSSRHFUiwCtMukM1NH6/tXXl4t9HBhDAr0nzwx/4cN6fOvtXVBtyyV1ipbzPTiSGJOd86PcNTWYRK1KMUaam0ARxcmkyM7OLgWbFZb8rwmvdsNpKcB647ArYj3aYIi5Vh8zZfrvMRIRfCTBefiRu8B6pIKClbtDgYL+JSUCgYBx6BC9Eu8ryu7NazSw6cSyApNDLbYyvuXOhPnvfPm8zXf9rkaF+reKMYfZaMsXBdnTkHDrMXQq4N42/+dK48XQkmXOYKr4RfSdcSWUTowvh0UX4spfrXBfH96MIoEBIvwWItj9prdj3Q3f7yslJ//ZAI2kDBLUBFehfYeXdVK23Q==";

        String encryptData = encrypt2Base64ByPriKey(priKey, data);
        System.out.println("加密后数据:" + encryptData);
        String decryptData = decryptFromBase64ByPubKey(pubKey, encryptData);
        System.out.println("解密后数据:" + decryptData);

        String sign = sign(priKey, data);
        System.out.println("签名:" + sign);
        boolean isRight = verifySign(pubKey, data, sign);
        System.out.println("RSA 签名验证:" + isRight);
    }

}
