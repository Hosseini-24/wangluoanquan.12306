package 数据库12306.socket.OpenSSl;
import javax.crypto.Cipher;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import static 数据库12306.socket.OpenSSl.Ticket.SplitTicket;

public class DES_ZeroPading {
    public static String encryptWithZeroPadding(String plaintext, String key) throws Exception {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] paddedPlaintext = padWithZero(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(cipher.doFinal(paddedPlaintext));
    }

    public static String decryptWithZeroPadding(String ciphertext, String key) throws Exception {
        byte[] ciphertextBytes = Base64.getDecoder().decode(ciphertext);

        int n;
        System.out.println(ciphertextBytes.length);
        for (int i=0;i<ciphertextBytes.length;i++){
            n=(ciphertextBytes[i] & 0xff);
            System.out.print(n+" ");
        }

        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedData = cipher.doFinal(ciphertextBytes);
        return new String(removeZeroPadding(decryptedData), StandardCharsets.UTF_8);
    }

    private static byte[] padWithZero(byte[] data) {
        int blockSize = 8;
        int paddingLength = blockSize - (data.length % blockSize);
        byte[] paddedData = new byte[data.length + paddingLength];
        System.arraycopy(data, 0, paddedData, 0, data.length);
        Arrays.fill(paddedData, data.length, paddedData.length, (byte) 0x00);
        return paddedData;
    }

    private static byte[] removeZeroPadding(byte[] data) {
        int paddingCount = 0;
        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i] == 0x00) {
                paddingCount++;
            } else {
                break;
            }
        }
        return Arrays.copyOfRange(data, 0, data.length - paddingCount);
    }
    public static void main(String[] args) throws Exception {
        System.out.println("加密值:"+encryptWithZeroPadding("2020-12-22 23:00:00","abcdefgh"));
        System.out.println("解密值:"+decryptWithZeroPadding("Y7awvbv08wfXNj4rjFUH/aeBScnKZFd4","bluestar"));
        String Str="Ko1p3p1f3/ksikvhXMGnQRWwz7bOkQtKEs7Ai7uC8ehcj1mFkIvPsQ==";
        String strA=Str.substring(0,32);
        String strB=Str.substring(Str.length()-24);
        System.out.println(strA);
        System.out.println(strB);
        System.out.println(strA.length()+"  "+strB.length()+"  "+Str.length());
        //byte[] encode = org.bouncycastle.util.encoders.Base64.encode(strA.getBytes());
        byte[] decode = Base64.getDecoder().decode(strA);
        System.out.println(decode.length);
        String decrypt = decryptWithZeroPadding(strA,"abcdefgh");
        System.out.println(decrypt);
        byte[] decrypttt=decrypt.getBytes();

        int m;
        System.out.println(decrypttt.length);
        for (int i=0;i<decrypttt.length;i++){
            m=(decrypttt[i] & 0xff);
            System.out.print(m+" ");
        }
        System.out.println("\n"+"这是：");
        Ticket ticket = SplitTicket(decrypttt);
        System.out.println(ticket.getKcv()+"||"+ticket.getIDc()+"||"+ticket.getADc()
                +"||"+ticket.getIDv()+"||"+ticket.getTSc()+"||"+ticket.getLifetime());
    }

}
