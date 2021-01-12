package cc.devclub.ftd.sha;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @Author: 【我是开发者FTD】公众号 微信号：ForTheDevelopers
 * @Description: Bouncy Castle 提供的 SHA 算法实现
 */
public class BCShaCoder {

    private static final String SHA_ALGORITHM = "SHA";
    private static final String SHA224_ALGORITHM = "SHA-224";
    private static final String SHA256_ALGORITHM = "SHA-256";
    private static final String SHA384_ALGORITHM = "SHA-384";
    private static final String SHA512_ALGORITHM = "SHA-512";

    public static String encodeSha(String data, String algorithm) throws NoSuchAlgorithmException {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化MessageDigest
        MessageDigest sha = MessageDigest.getInstance(algorithm);
        // 获取消息摘要
        byte[] bytes = sha.digest(data.getBytes());
        // 返回十六进制字符串
        return Hex.toHexString(bytes);
    }


    public static void main(String[] args) throws Exception {
        String data = "欢迎关注【我是开发者FTD】公众号，微信号：ForTheDevelopers";

        System.out.println("SHA-1加密结果：");
        System.out.println(encodeSha(data, SHA_ALGORITHM));
        System.out.println("SHA-224加密结果：");
        System.out.println(encodeSha(data, SHA224_ALGORITHM));
        System.out.println("SHA-256加密结果：");
        System.out.println(encodeSha(data, SHA256_ALGORITHM));
        System.out.println("SHA-384加密结果：");
        System.out.println(encodeSha(data, SHA384_ALGORITHM));
        System.out.println("SHA-512加密结果：");
        System.out.println(encodeSha(data, SHA512_ALGORITHM));
    }
}
