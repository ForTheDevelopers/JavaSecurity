package cc.devclub.ftd.md5;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.Security;

/**
 * @Author: 【我是开发者FTD】公众号 微信号：ForTheDevelopers
 * @Description: Bouncy Castle 提供的 MD 算法实现
 */
public class BCMdCoder {


    /********************************* Bouncy Castle 实现的MD4算法应用 *********************************/
    // MD4加密
    public static String encodeMd4(byte[] data) throws Exception {
        // 加入BouncyCastle的支持
        Security.addProvider(new BouncyCastleProvider());
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("MD4");
        // 执行摘要信息
        byte[] digest = md.digest(data);
        // 将摘要信息转换为32位的十六进制字符串
        return Hex.toHexString(digest);
    }


    public static void main(String[] args) throws Exception {
        String data = "欢迎关注【我是开发者FTD】公众号，微信号：ForTheDevelopers";

        String encodeMd4String = BCMdCoder.encodeMd4(data.getBytes());
        System.out.println(encodeMd4String);
    }
}
