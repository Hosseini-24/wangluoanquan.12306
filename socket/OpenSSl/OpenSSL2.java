package 数据库12306.socket.OpenSSl;
import 数据库12306.socket.DES.DESUtil;
import 数据库12306.socket.DES.DesEncryptDecrypt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.*;

public class OpenSSL2 {
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public OpenSSL2(String pemFilePath,String pemFilePath2) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        String pemData = readPemFile(pemFilePath);
        this.privateKey = getPrivateKeyFromPem(pemData);
        String pemData2 = readPemFile(pemFilePath2);
        this.publicKey = getPublicKeyFromPEm(pemData2);
    }

    private static String readPemFile(String pemFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(pemFilePath));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            if (!line.startsWith("-")) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }

    private static PrivateKey getPrivateKeyFromPem(String pemData) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decoded = Base64.getDecoder().decode(pemData);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    private static PublicKey getPublicKeyFromPEm(String pemData) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        byte[] publicKeyBytes =Base64.getDecoder().decode(pemData);
        X509EncodedKeySpec keyspec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(keyspec);
    }
    public String encrypt(String plaintext) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        String encoded = Base64.getEncoder().encodeToString(encryptedBytes);
        return encoded;
    }

    public String decrypt(String ciphertext) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
        //先转为字节数组
        byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        String plaintext = new String(decryptedBytes);
        return plaintext;
    }

    public static int[] byteArrayToIntArray(byte[] byteArray) {
        int[] intArray = new int[byteArray.length / 4];
        // 循环遍历byte数组，每四个字节转换成一个int
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = ((byteArray[4*i+3] & 0xFF) << 24) |
                    ((byteArray[4*i+2] & 0xFF) << 16) |
                    ((byteArray[4*i+1] & 0xFF) << 8 ) |
                    (byteArray[4*i]  & 0xFF);

        }
        return intArray;
    }
    public static void main(String[] args) throws NoSuchProviderException {
        OpenSSL2 openSSL = null;
        try {
            openSSL = new OpenSSL2("C:\\Users\\Hosseini\\Desktop\\private_key.pem",
                    "C:\\Users\\Hosseini\\Desktop\\public_key.pem");
            System.out.println(openSSL.privateKey);

            byte[] str="3GQIAMK83PLNUwNsaPqg4fkAPp+bbyGt".getBytes();
            byte[] key=openSSL.privateKey.getEncoded();
            byte[] result=OpenSSL_DES.decrypt(str,key);



            System.out.println(openSSL.publicKey);

            /*RSA私钥加密*/
            String plaintext = "Hello, world!";
            String base64Text = Base64.getEncoder().encodeToString(plaintext.getBytes());
            System.out.println("base64转码后："+base64Text);
            //加密
            String ciphertext = openSSL.encrypt(base64Text);
            System.out.println("私钥加密后内容为(密文)："+ciphertext);

//
//            /*RSA公钥解密*/
//            //解密
//            String siyaojie=openSSL.decrypt(ciphertext);
//            System.out.println("解密后内容经base64转换前："+siyaojie);
//            String zhuanweistr=new String(Base64.getDecoder().decode(siyaojie));
//            System.out.println("经base64转换后："+zhuanweistr);
//            /*
//            String strdata=readPemFile("C:\\Users\\Hosseini\\Desktop\\public_key.pem");
//            System.out.println("读取文件（公钥文件）内容："+strdata);
//            */
            /*RSA公钥解密*/
           String decryptedBase64Text = openSSL.decrypt(ciphertext);
           System.out.println("解密后(明文)未转码前："+decryptedBase64Text);
           String plaintext2= new String(Base64.getDecoder().decode(decryptedBase64Text));
           System.out.println("转码后："+plaintext2); // 输出: Hello, world!

            //测试
            String Base64Text= "G+xuAwdH/PKeOpANCP/CtE8TTQQcoqOcINtmJB5TTtFbvqEHjja7Et7kUbFo94YH3vXMWD5WOgWPsgfC8Az4CcORQkhq8zVkGE9Tun40ebh8KOMLrKppmkAH8za/EWqyK/oFOVrlTHXNvFPpan7XL30FHj9jeOyqZuUIQpcf3+Z1pKMbVoXdYwHAYFL9bfGRxCGtq1C7M4/KrdlodZZkZZlsc70DwJB2aWnwrzH/ofg/lnIJM/MTVvcX0BvlCg/fDvNx+1PexkEX2Pxr9Fx3Mpi+68LadAArQRtkTRU9hchLSlsNdFQdES/jvhs8i4+3EWLRtpTV6v+AhIy+4xErBQ==";
            //String Base64Text="3GQIAMK83PLNUwNsaPqg4fkAPp+bbyGt";
            //String text= new String(Base64.getDecoder().decode(Base64Text));
            //System.out.println(text);
            String Text=openSSL.decrypt(Base64Text);
            System.out.println(Text+" "+Text.length());
            byte[] text=Text.getBytes();
            //System.out.println(text.length);
            Ticket ticket=Ticket.SplitTicket(text);
            System.out.println(ticket.getKcv()+"||"+ticket.getIDc()+"||"+ticket.getADc()+"||"+ticket.getIDv()+"||"+ticket.getTSc()+"||"+ticket.getLifetime());


            } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
