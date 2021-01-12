package cc.devclub.ftd.md5;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;

/**
 * @Author: 【我是开发者FTD】公众号 微信号：ForTheDevelopers
 * @Description: JDK 提供的 MD 算法实现
 */
public class JDKMdCoder {

    /********************************* JDK 提供的 MD2 算法实现 *********************************/
    // MD2加密
    public static String encodeMd2(byte[] data) throws Exception {
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("MD4");
        // 执行摘要信息
        byte[] digest = md.digest(data);
        // 将摘要信息转换为32位的十六进制字符串
        return new HexBinaryAdapter().marshal(digest);
    }

    /********************************* JDK 提供的 MD5 算法实现 *********************************/
    // MD5加密
    public static String encodeMd5(byte[] data) throws Exception {
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 执行摘要信息
        byte[] digest = md.digest(data);
        // 将摘要信息转换为32位的十六进制字符串
        return new HexBinaryAdapter().marshal(digest);
    }


    public static void main(String[] args) throws Exception {
        String data = "欢迎关注【我是开发者FTD】公众号，微信号：ForTheDevelopers";

        System.out.println("MD2加密结果：");
        System.out.println(JDKMdCoder.encodeMd2(data.getBytes()));
        System.out.println("MD5加密结果：");
        System.out.println(JDKMdCoder.encodeMd5(data.getBytes()));
    }
}
