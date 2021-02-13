package cc.devclub.ftd.ecc;

import java.math.BigInteger;


public class EccMath {
    //此方法求余数。prime:素数，primitive：本原元，random：随机数。
    public static long reaminder(long prime, long primitive, long random) {
        long reamin = primitive % prime;
        long currentreamin = reamin;
        String binary = Long.toBinaryString(random);
        System.out.println(binary);
        for (int i = 0; i < binary.length() - 1; i++) {
            if (binary.charAt(i + 1) == '0') {
                currentreamin = (currentreamin * currentreamin) % prime;
            } else {
                currentreamin = (currentreamin * currentreamin * reamin) % prime;
            }

        }
        //
		/*if(random == 1) {
			return reamin;
		}
		
		for(long i=2; i<=random; i++) {
			currentreamin = currentreamin * reamin % prime;
		}*/
        return currentreamin;
    }

    public static BigInteger reaminder(BigInteger prime, BigInteger primitive, long random) {
        BigInteger reamin = primitive.mod(prime);//primitive%prime;
        BigInteger currentreamin = reamin;
        String binary = Long.toBinaryString(random);
        for (int i = 0; i < binary.length() - 1; i++) {
            if (binary.charAt(i + 1) == '0') {
                currentreamin = currentreamin.multiply(currentreamin).mod(prime);//(currentreamin * currentreamin) % prime;
            } else {
                currentreamin = currentreamin.multiply(currentreamin).multiply(reamin).mod(prime);//(currentreamin * currentreamin * reamin) % prime;
            }

        }
        return currentreamin;
    }

    public static BigInteger reaminder(String prim, String primitiv, String rand) {
        BigInteger prime = new BigInteger(prim);
        BigInteger primitive = new BigInteger(primitiv);
        Long random = new Long(rand);
        BigInteger reamin = primitive.mod(prime);//primitive%prime;
        BigInteger currentreamin = reamin;
        String binary = Long.toBinaryString(random);
        for (int i = 0; i < binary.length() - 1; i++) {
            if (binary.charAt(i + 1) == '0') {
                currentreamin = currentreamin.multiply(currentreamin).mod(prime);//(currentreamin * currentreamin) % prime;
            } else {
                currentreamin = currentreamin.multiply(currentreamin).multiply(reamin).mod(prime);//(currentreamin * currentreamin * reamin) % prime;
            }

        }
        return currentreamin;
    }

    //此方法判断素数
    public static boolean isPrime(long num) {
        boolean flag = true;
        for (long i = 2; i < num / 2; i++) {
            if (num == 2) {
                break;
            }
            if (num % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    //求最大公约数:欧几里得算法，辗转相除法
    public static long gcd(long a, long b) {
        long reamin = a % b;
        if (reamin == 0) {
            return b;
        } else {
            return gcd(b, reamin);
        }
    }

    //扩展的欧几里得算法求逆元，如果有返回值，没有返回-1
    public static long exgcd(long a, long b) {
        long x1 = 1, x2 = 0, x3 = b,
                y1 = 0, y2 = 1, y3 = a;
        while (true) {
            if (y3 == 0) {
                return -1;
            }
            if (y3 == 1) {
                return y2 > 0 ? y2 : y2 + b;
            }
            long t1, t2, t3;
            long q = x3 / y3;
            t1 = x1 - q * y1;
            t2 = x2 - q * y2;
            t3 = x3 - q * y3;
            x1 = y1;
            x2 = y2;
            x3 = y3;
            y1 = t1;
            y2 = t2;
            y3 = t3;
        }
    }

    public static BigInteger exgcd(BigInteger a, BigInteger b) {
        BigInteger x1 = BigInteger.ONE, x2 = BigInteger.ZERO, x3 = b,
                y1 = BigInteger.ZERO, y2 = BigInteger.ONE, y3 = a;
        while (true) {
            if (y3.equals(BigInteger.ZERO)) {
                return BigInteger.ZERO.subtract(BigInteger.ONE);
            }
            if (y3.equals(BigInteger.ONE)) {
                return y2;
            }
            BigInteger t1, t2, t3;
            BigInteger q = x3.divide(y3);//x3/y3;
            t1 = x1.subtract(q.multiply(y1));//x1-q*y1;
            t2 = x2.subtract(q.multiply(y2));//x2 - q*y2;
            t3 = x3.subtract(q.multiply(y3));//x3 - q*y3;
            x1 = y1;
            x2 = y2;
            x3 = y3;
            y1 = t1;
            y2 = t2;
            y3 = t3;
        }
    }

    public static void main(String[] args) {
        //System.out.println(exgcd(new BigInteger(5+""),new BigInteger(96+"")));
        //System.out.println((int)'，');
        //System.out.println((char)(34382));
        //System.out.println(-19 % 96);
        //System.out.println(Character.getNumericValue('虎'));
        //System.out.println(reaminder("561","7","560"));
        //System.out.println(reaminder(561,7,560));
        //System.out.println(new BigInteger(7+"").modPow(new BigInteger("560"), new BigInteger("561")));
        //System.out.println(exgcd(-2,3));
        String a = "你好  好啊";
        //System.out.println((int)' ');
        char[] chars = a.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            String s = Integer.toHexString(chars[i]);
            s = EccTools.obox(s, 4);
            System.out.println(s);
        }
    }
}