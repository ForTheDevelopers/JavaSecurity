package cc.devclub.ftd.sha;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author: 【我是开发者FTD】公众号 微信号：ForTheDevelopers
 * @Description: Commons Codec 提供的 SHA 算法实现
 */
public class CCShaCoder {

    /*************************** SHA-1 ***************************/
    public static byte[] encodeSha1(String data) {
        return DigestUtils.sha1(data);
    }

    public static String encodeSha1Hex(String data) {
        return DigestUtils.sha1Hex(data);
    }

    /*************************** SHA-256 ***************************/
    public static byte[] encodeSha256(String data) {
        return DigestUtils.sha256(data);
    }

    public static String encodeSha256Hex(String data) {
        return DigestUtils.sha256Hex(data);
    }

    /*************************** SHA-384 ***************************/
    public static byte[] encodeSha384(String data) {
        return DigestUtils.sha384(data);
    }

    public static String encodeSha384Hex(String data) {
        return DigestUtils.sha384Hex(data);
    }

    /*************************** SHA-512 ***************************/
    public static byte[] encodeSha512(String data) {
        return DigestUtils.sha512(data);
    }

    public static String encodeSha512Hex(String data) {
        return DigestUtils.sha512Hex(data);
    }


    public static void main(String[] args) {
        String data = "欢迎关注【我是开发者FTD】公众号，微信号：ForTheDevelopers";

        System.out.println("SHA-1加密结果：");
        System.out.println(encodeSha1Hex(data));
        System.out.println("SHA-256加密结果：");
        System.out.println(encodeSha256Hex(data));
        System.out.println("SHA-384加密结果：");
        System.out.println(encodeSha384Hex(data));
        System.out.println("SHA-512加密结果：");
        System.out.println(encodeSha512Hex(data));
    }
}
