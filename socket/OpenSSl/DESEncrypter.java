package 数据库12306.socket.OpenSSl;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import static 数据库12306.socket.OpenSSl.Ticket.SplitTicket;

public class DESEncrypter {
    private static final String ALGO = "DES/ECB/NoPadding";
    private static final String CHARSET = "UTF-8";
    private static final String KEY_ALGO = "DES";

    private SecretKey secretKey;

    public DESEncrypter(String key) {
        byte[] keyBytes = key.getBytes();
        secretKey = new SecretKeySpec(keyBytes, KEY_ALGO);
    }

    public byte[] encrypt(byte[] plainText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText);

        int n;
        System.out.println(encryptedBytes.length);
        for (int i=0;i<encryptedBytes.length;i++){
            n=(encryptedBytes[i] & 0xff);
            System.out.print(n+" ");
        }

        byte[] bytes = Base64.encodeBase64(encryptedBytes);
        //String s = Base64.encodeBase64String(encryptedBytes);
        int m;
        System.out.println(bytes.length);
        for (int i=0;i<bytes.length;i++){
            m=(bytes[i] & 0xff);
            System.out.print(m+" ");
        }
        return bytes;
    }

    public byte[] decrypt(byte[] cipherText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] cipherBytes = Base64.decodeBase64(cipherText);

        int n;
        System.out.println(cipherBytes.length);
        for (int i=0;i<cipherBytes.length;i++){
            n=(cipherBytes[i] & 0xff);
            System.out.print(n+" ");
        }

        byte[] decryptedBytes = cipher.doFinal(cipherBytes);
        int m;
        System.out.println(decryptedBytes.length);
        for (int i=0;i<decryptedBytes.length;i++){
            m=(decryptedBytes[i] & 0xff);
            System.out.print(m+" ");
        }

        //int value = ByteBuffer.wrap(decryptedBytes).getInt();

        //System.out.println(value);
        return decryptedBytes;
    }
    public static void main(String[] args) {
        String key = "abcdefgh"; // 8字节密钥
        byte[] plainText = "Hello,World!0000".getBytes();
        try {
            DESEncrypter desEncrypter = new DESEncrypter(key);

            byte[] cipherText = desEncrypter.encrypt(plainText);
            System.out.println("密文: " + cipherText);



            byte[] decryptedText = desEncrypter.decrypt(cipherText);
            System.out.println("明文 : " + decryptedText);

            //测试
            String Str="Ko1p3p1f3/ksikvhXMGnQRWwz7bOkQtKEs7Ai7uC8ehcj1mFkIvPsQ==";
            String strA=Str.substring(0,32);
            String strB=Str.substring(Str.length()-24);
            System.out.println(strA);
            System.out.println(strB);
            System.out.println(strA.length()+"  "+strB.length()+"  "+Str.length());
            byte[] decrypt = desEncrypter.decrypt(strA.getBytes());
            System.out.println(decrypt.length);
            System.out.println(decrypt.length);
            System.out.println("\n"+decrypt);


            Ticket ticket = SplitTicket(decrypt);
            System.out.println(ticket.getKcv()+"||"+ticket.getIDc()+"||"+ticket.getADc()
                    +"||"+ticket.getIDv()+"||"+ticket.getTSc()+"||"+ticket.getLifetime());
            System.out.println(ticket.toString());

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(formatter.format(date));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
