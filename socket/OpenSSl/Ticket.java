package 数据库12306.socket.OpenSSl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Ticket {
    private String Kcv;
    private int IDc;
    private int[] ADc;
    private int IDv;
    private int[] TSc;
    private int Lifetime;

    public Ticket() {
    }


    public Ticket(String kcv, int IDc, int[] ADc, int IDv, int[] TSc, int lifetime) {
        Kcv = kcv;
        this.IDc = IDc;
        this.ADc = ADc;
        this.IDv = IDv;
        this.TSc = TSc;
        Lifetime = lifetime;
    }

    public static Ticket SplitTicket(byte[] bytes) throws IOException{
        Ticket ticket=new Ticket();
        ticket.Kcv=new String(bytes,0,8);
        ticket.IDc=bytes[8];
        // 将第10到13位赋值给ADc
        ticket.ADc=new int[4];
        for(int i=9;i<13;i++){
            ticket.ADc[i-9]=bytes[i];
        }

        //ticket.ADc=((bytes[9] & 0xff) << 24) | ((bytes[10] & 0xff) << 16) | ((bytes[11] & 0xff) << 8) | (bytes[12] & 0xff);
        ticket.IDv=bytes[13];
        // 将第15到20位赋值给int []
        ticket.TSc=new int[6];
        ticket.TSc[0]=bytes[14]+1900;
        for(int i=15;i<20;i++){
            ticket.TSc[i-14]=bytes[i];
        }
        //ticket.TSc= ((bytes[14] & 0xff) << 40) | ((bytes[15] & 0xff) << 32) |
//                ((bytes[16] & 0xff) << 24) | ((bytes[17] & 0xff) << 16) |
//                ((bytes[18] & 0xff) << 8) | (bytes[19] & 0xff);
        ticket.Lifetime= bytes[20];
        return ticket;
    }

//    public static Boolean EqualTicket(Ticket ticket) throws IOException{
//        boolean flag;
//        if(){
//
//        }
//        return flag;
//    }
    public String getKcv() {
        return Kcv;
    }

    public void setKcv(String kcv) {
        Kcv = kcv;
    }

    public int getIDc() {
        return IDc;
    }

    public void setIDc(int IDc) {
        this.IDc = IDc;
    }


    public int[] getADc() {
        return ADc;
    }

    public void setADc(int[] ADc) {
        this.ADc = ADc;
    }

    public void setTSc(int[] TSc) {
        this.TSc = TSc;
    }

    public int getIDv() {
        return IDv;
    }

    public void setIDv(int IDv) {
        this.IDv = IDv;
    }

    public int[] getTSc() {
        return TSc;
    }

    public int getLifetime() {
        return Lifetime;
    }

    public void setLifetime(int lifetime) {
        Lifetime = lifetime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "Kcv='" + Kcv + '\'' +
                ", IDc=" + IDc +
                ", ADc=" + Arrays.toString(ADc) +
                ", IDv=" + IDv +
                ", TSc=" + Arrays.toString(TSc) +
                ", Lifetime=" + Lifetime +
                '}';
    }
}
