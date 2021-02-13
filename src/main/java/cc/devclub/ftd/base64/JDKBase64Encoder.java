package cc.devclub.ftd.base64;

import java.util.Base64;

/**
 * @Author: loading
 * @Create: 2021-02-13 15:56
 * @Description: JDK 提供的 Base64编码 实现
 */
public class JDKBase64Encoder {

    public static String encode(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    public static String decode(String base64Data) {
        return new String(Base64.getDecoder().decode(base64Data));
    }

    public static void main(String[] args) {
        String data = "欢迎关注【我是开发者FTD】公众号，微信号：ForTheDevelopers";

        String base64Data = encode(data);
        System.out.println("base64 编码:" + base64Data);
        String decodeData = decode(base64Data);
        System.out.println("base64 解码:" + decodeData);
    }
}
