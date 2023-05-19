package 数据库12306.socket.OpenSSl;

import java.io.IOException;
import java.util.Base64;

public class Base64Util {
    /**
     * 对字符串进行base64编码
     * @param str 需要编码的字符串
     * @return 编码后的字符串
     */
    public static String encode(String str) {
        byte[] encodedBytes = Base64.getEncoder().encode(str.getBytes());
        return new String(encodedBytes);
    }

    /**
     * 对字符串进行base64解码
     * @param str 需要解码的字符串
     * @return 解码后的字符串
     */
    public static String decode(String str) {
        byte[] decodedBytes = Base64.getDecoder().decode(str.getBytes());
        return new String(decodedBytes);
    }
    public static void main(String[] args){
//      String encodedStr = Base64Util.encode("Hello World");
//      System.out.println("Encoded string: " + encodedStr);
//      String decodedStr = Base64Util.decode(encodedStr);
//      System.out.println("Decoded string: " + decodedStr);

        String str="3GQIAMK83PLNUwNsaPqg4fkAPp+bbyGt";
        byte[] base64de=Base64.getDecoder().decode(str);
        System.out.println(base64de);
        //DES解密
        byte[] key="abcdefgh".getBytes();
        //byte[] cipher=str.getBytes();
        try {
            byte[] decryptText=OpenSSL_DES.decrypt(base64de,key);
            Ticket ticket=Ticket.SplitTicket(decryptText);
            System.out.println(ticket.getKcv()+" "+ticket.getIDc()+" "+ticket.getADc()
                    +" "+ticket.getIDv()+" "+ticket.getTSc()+" "+ticket.getLifetime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //System.out.println(byteStr.length);

    }
}
