package cc.devclub.ftd.base64;


import org.apache.commons.codec.binary.Base64;

/**
 * @Author: loading
 * @Create: 2021-02-13 15:56
 * @Description: Commons Codec 提供的 Base64编码 实现
 */
public class CCBase64Encoder {

    public static String encode(String data) {
        return Base64.encodeBase64String(data.getBytes());
    }

    public static String decode(String base64Data) {
        return new String(Base64.decodeBase64(base64Data));
    }

    public static void main(String[] args) {
        String data = "欢迎关注【我是开发者FTD】公众号，微信号：ForTheDevelopers";

        String base64Data = encode(data);
        System.out.println("base64 编码:" + base64Data);
        String decodeData = decode(base64Data);
        System.out.println("base64 解码:" + decodeData);
//        String data = "欢迎关注【我是开发者FTD】公众号，微信号：ForTheDevelopers";
//
//        String base64Data = Base64.encodeBase64String(data.getBytes(Charset.forName("GBK")));
//        String base64Data2 = Base64.encodeBase64String(data.getBytes(Charset.forName("UTF-8")));
//        System.out.println("base64 GBK 编码:" + base64Data);
//        System.out.println("base64 UTF-8 编码:" + base64Data2);
//
//        String decodeData = new String(Base64.decodeBase64(base64Data), Charset.forName("GBK"));
//        String decodeData2 = new String(Base64.decodeBase64(base64Data2), Charset.forName("UTF-8"));
//        System.out.println("base64 GBK 解码:" + decodeData);
//        System.out.println("base64 UTF-8 解码:" + decodeData2);
    }
}
