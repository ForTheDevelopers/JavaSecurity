package cc.devclub.ftd.ecc;

public class EccTools {
    //字符串左边补0直到长度为i
    public static String obox(String s, int i) {
        String ss = s;
        while (ss.length() < i) {
            ss = "0" + ss;
        }
        return ss;
    }

    //字符串右边补0直到长度为i
    public static String boxo(String s, int i) {
        String ss = s;
        while (ss.length() < i) {
            ss += "0";
        }
        return ss;
    }

    //将字符串变成16进制字符串
    public static String Str2hexStr(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            sb.append(obox(Integer.toHexString(s.charAt(i)), 4));
        }
        return sb.toString();
    }

    //将16进制字符串变成字符串
    public static String hexStr2Str(String s) {
        StringBuffer sb = new StringBuffer();
        int index = 0;
        int length = s.length();
        while (index + 4 <= length) {
            String sh = s.substring(index, index + 4);
            sb.append((char) Integer.parseInt(sh, 16));
            index += 4;
        }
        if (sb.charAt(sb.length() - 1) == 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    //String数组转换为char数组
    public static char[] Ss2Cs(String[] s) {
        char[] result = new char[s.length];
        for (int i = 0; i < s.length; i++) {
            result[i] = (char) Integer.parseInt(s[i], 16);
        }
        return result;
    }

    //String转换为char数组
    public static char[] Str2Cs(String s) {
        char[] result = new char[s.length() / 2];
        for (int i = 0; i < s.length() / 2; i++) {
            StringBuffer sb = new StringBuffer();//(char)s.charAt(i)+s.charAt(i+1);
            sb.append(s.charAt(i * 2));
            sb.append(s.charAt(i * 2 + 1));
            result[i] = (char) Integer.parseInt(sb.toString(), 16);
        }
        return result;
    }

    //hexString转换为数组
    public static char[] hexStr2Cs(String s) {
        //System.out.println("hexStr:" + s);
        char[] result = new char[s.length() / 2];
        for (int i = 0; i < s.length() / 2; i++) {
            StringBuffer sb = new StringBuffer();//(char)s.charAt(i)+s.charAt(i+1);
            sb.append(s.charAt(i * 2));
            sb.append(s.charAt(i * 2 + 1));
            result[i] = (char) Integer.parseInt(sb.toString(), 16);
        }
        return result;
    }

    //char数组转换为String数组
    public static String[] Cs2Ss(char[] s) {
        String[] result = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            result[i] = Integer.toHexString(s[i]);
        }
        return result;
    }

    //char数组转换为hexString
    public static String Cs2hexStr(char[] s) {
        StringBuffer sb = new StringBuffer();
        //String[] result = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            sb.append(obox(Integer.toHexString(s[i]), 2));
        }
        return sb.toString();
    }

    //long转换为hexString
    public static String long2hexStr(long lo) {
        //String s = Long.toHexString(lo);
        //s =
        return Long.toHexString(lo);
    }

    //longs数组转换为hexString
    public static String longs2hexStr(long[] lo) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lo.length; i++) {
            sb.append(Long.toHexString(lo[0]));
            sb.append(" ");
        }
        return sb.toString();
    }

    //hexString转换为long
    public static long hexStr2long(String s) {
        return Long.parseLong(s, 16);
    }

    //hexString转换为long数组
    public static long[] hexStr2longs(String s) {
        String[] ss = s.split(" ");
        long[] ls = new long[ss.length];
        for (int i = 0; i < ls.length; i++) {
            ls[i] = Long.parseLong(ss[i], 16);
        }
        return ls;
    }

    public static void main(String[] args) {
        String hexs1 = long2hexStr(Long.MAX_VALUE);
        String hexs2 = long2hexStr(456);
        String s = hexs1 + " " + hexs2;
        System.out.println(s);
        long[] l = hexStr2longs(s);
        for (int i = 0; i < l.length; i++) {
            System.out.println(l[i]);
        }
    }
}