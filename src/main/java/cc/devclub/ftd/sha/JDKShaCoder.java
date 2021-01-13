package cc.devclub.ftd.sha;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;

/**
 * 消息摘要算法 - SHA（Secure Hash Algorithm）
 *
 * @Author: 【我是开发者FTD】公众号 微信号：ForTheDevelopers
 * @Description: JDK 提供的 SHA 算法实现
 */
public class JDKShaCoder {

    private static final String SHA_ALGORITHM = "SHA";
    private static final String SHA224_ALGORITHM = "SHA-224";
    private static final String SHA256_ALGORITHM = "SHA-256";
    private static final String SHA384_ALGORITHM = "SHA-384";
    private static final String SHA512_ALGORITHM = "SHA-512";

    // SHA 加密
    public static String encodeSha(String data, String algorithm) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(algorithm);
        byte[] srcBytes = data.getBytes();
        // 使用srcBytes更新摘要
        sha.update(srcBytes);
        // 完成哈希计算，得到result
        byte[] resultBytes = sha.digest();
        // 返回十六进制字符串
        return new HexBinaryAdapter().marshal(resultBytes);
    }

    public static void main(String[] args) throws Exception {
        String data = "欢迎关注【我是开发者FTD】公众号，微信号：ForTheDevelopers";

        System.out.println("SHA-1加密结果：");
        System.out.println(JDKShaCoder.encodeSha(data, SHA_ALGORITHM));
        System.out.println("SHA-224加密结果：");
        System.out.println(JDKShaCoder.encodeSha(data, SHA224_ALGORITHM));
        System.out.println("SHA-256加密结果：");
        System.out.println(JDKShaCoder.encodeSha(data, SHA256_ALGORITHM));
        System.out.println("SHA-384加密结果：");
        System.out.println(JDKShaCoder.encodeSha(data, SHA384_ALGORITHM));
        System.out.println("SHA-512加密结果：");
        System.out.println(JDKShaCoder.encodeSha(data, SHA512_ALGORITHM));
    }
}
