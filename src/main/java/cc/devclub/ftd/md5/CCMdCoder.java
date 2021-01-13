package cc.devclub.ftd.md5;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 消息摘要算法 - MD（Message Digest）
 *
 * @Author: 【我是开发者FTD】公众号 微信号：ForTheDevelopers
 * @Description: Commons Codec 提供的 MD 算法实现
 */
public class CCMdCoder {

    /****************************Commons Codec 提供的 MD2 算法实现*****************************/

    /**
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encodeMd2(byte[] data) {
        return DigestUtils.md2(data);
    }

    /**
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encodeMd2(String data) {
        return DigestUtils.md2(data);
    }

    /**
     * @param data
     * @return
     * @throws Exception
     */
    public static String encodeMd2Hex(byte[] data) {
        return DigestUtils.md2Hex(data);
    }

    /**
     * @param data
     * @return
     * @throws Exception
     */
    public static String encodeMd2Hex(String data) {
        return DigestUtils.md2Hex(data);
    }

    /****************************Commons Codec 提供的 MD5 算法实现*****************************/

    /**
     * @param data
     * @return
     */
    public static byte[] encodeMd5(byte[] data) {
        return DigestUtils.md5(data);
    }

    /**
     * @param data
     * @return
     */
    public static byte[] encodeMd5(String data) {
        return DigestUtils.md5(data);
    }

    /**
     * MD5加密 返回十六进制的字符串
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encodeMd5Hex(byte[] data) {
        return DigestUtils.md5Hex(data);
    }

    /**
     * MD5加密 返回十六进制的字符串
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encodeMd5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }


    public static void main(String[] args) {
        String data = "欢迎关注【我是开发者FTD】公众号，微信号：ForTheDevelopers";

        System.out.println("MD2加密结果：");
        System.out.println(CCMdCoder.encodeMd2Hex(data.getBytes()));
        System.out.println(CCMdCoder.encodeMd2Hex(data));

        System.out.println("MD5加密结果：");
        System.out.println(CCMdCoder.encodeMd5Hex(data.getBytes()));
        System.out.println(CCMdCoder.encodeMd5Hex(data));
    }
}
