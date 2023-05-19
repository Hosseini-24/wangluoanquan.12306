package 数据库12306.socket.client;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.net.Socket;


public class client {
    public static void main(String[] args) throws Exception{

        Socket client=new Socket("127.0.0.1",8888);
        DataOutputStream out = new DataOutputStream(client.getOutputStream());
        DataInputStream in = new DataInputStream(client.getInputStream());
//        out.writeUTF("selectSeatByUid,100");
//        out.flush();

//        out.writeUTF("selectAllSeat");
//        out.flush();

//        out.writeUTF("100");
//        out.flush();

//        out.writeUTF("updateSeat,1,110,特等座,AAA,0车厢,第0排,2");
//        out.flush();

//        out.writeUTF("insertSeat,20,119,特等座,02A,0车厢,第0排,2");
//        out.flush();


//        out.writeUTF("deleteSeat,20");
//        out.flush();

//        out.writeUTF("FindAllOrders");
//        out.flush();
//
//        out.writeUTF("addOrders,3,1003,1236,Irving,103,G4,二等座,01A,运行,武汉,西安,2车厢,第二排,2023-04-10 13:14:15");
//        out.flush();

//        out.writeUTF("getOrdersByUid,1001");
//        out.flush();

//        out.writeUTF("getOrdersByUid,1001");
//        out.flush();

//        out.writeUTF("updateOrders,4,1003,1236,Irving,103,G3,无座,01B,运行,武汉,北京,3车厢,第三排,2023-06-06 14:14:14");
//        out.flush();

//        out.writeUTF("deleteOrders,3");
//        out.flush();

//        out.writeUTF("deleteOrders,3");
//        out.flush();

//        out.writeUTF("login,kobe,242424");
//        out.flush();

//        out.writeUTF("Register,姚明,11,1004,1555,888888,男");
//        out.flush();
//
//        out.writeUTF("DeleteUser,姚明");
//        out.flush();

        out.writeUTF("UpdateUserPwd,kobe,24242424");
        out.flush();
        String msg = in.readUTF();
        System.out.println(msg);
        out.close();
        client.close();

    }
}
