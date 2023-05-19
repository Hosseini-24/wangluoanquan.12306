package 数据库12306.socket.server.task;

import 数据库12306.bean.Orders;
import 数据库12306.bean.Seat;
import 数据库12306.bean.Train;
import 数据库12306.bean.User;
import 数据库12306.myInterface.*;
import 数据库12306.socket.Massage.Massage;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;



public class serverTask implements Runnable{
    Socket m_socket;
    DataInputStream m_in;
    DataOutputStream m_out;
    BufferedReader min;


    boolean isLogin =false;
    public serverTask(Socket socket) throws IOException {
        this.m_socket = socket;
        this.m_in = new DataInputStream(this.m_socket.getInputStream());
        this.m_out = new DataOutputStream(this.m_socket.getOutputStream());
        this.min=new BufferedReader(new InputStreamReader(this.m_socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while(true){
                //String str=m_in.readUTF();
                //System.out.println(str);
                //String strClient=getDateFromClient();
                String requstFromClient = getRequstFromClient();
                Massage msg=splitTagAndData(requstFromClient);
                switch (msg.getTag()) {
                    case "selectSeatByUid":
                        String[] train_id=msg.getData();
                        FindRestSeat(train_id[0]);
                        continue;
                    case "selectAllSeat":
                        FindAllSeat();
                        continue;
                    case "updateSeat":
                        Seat seat=SetSeatFromData(msg);
                    updateSeat(seat);
                        continue;
                    case "insertSeat":
                        Seat seat2=SetSeatFromData(msg);
                    insertSeat(seat2);
                        continue;
                    case "deleteSeat":
                        String[] seat_id=msg.getData();
                    deleteSeatById(seat_id[0]);
                        continue;
                    case "FindAllOrders":
                        findAllOrders();
                        continue;
                    case "addOrders":
                        Orders orders=SetOrdersFromData(msg);
                        insertOrder(orders);
                        continue;
                    case "getOrdersByUid":
                        String[] uid=msg.getData();
                        findOrdersByUid(uid[0]);
                        continue;
//                  case "AAA":
//                      m_out.writeUTF("收到请求了吗");
//                      m_out.flush();
//                      continue;
                    case "updateOrders":
                        Orders order=SetOrdersFromData(msg);
                        updateOrders(order);
                        continue;
                    case "deleteOrders":
                        String[] orders_id=msg.getData();
                        deleteOrdersById(orders_id[0]);
                        continue;
                    case "login":
                        User user=SetUserFromData(msg);
                        Login(user);
                        continue;
                    case "Register":
                        User user2=SetUserFromData(msg);
                        Register(user2);
                        continue;
                    case "DeleteUser":
                        String[] uname=msg.getData();
                        DeleteUser(uname[0]);
                        continue;
                    case "UpdateUserPwd":
                        User user3=SetUNPFromData(msg);
                        UpdateUserPwd(user3);
                        continue;
                    case "FindAllUsers":
                        findAllUsers();
                        continue;
                    case "FindAllTrain":
                        findAllTrain();
                        continue;
                    case "SelectByStartAndTerminus":
                        String[] strings=msg.getData();
                        selectByStartAndTerminus(strings[0],strings[1]);
                        continue;
                    case "SelectByTname":
                        String[] Tname=msg.getData();
                        selectByTname(Tname[0]);
                        continue;
                    case "UpdateTrain":
                        String[] Tname2=msg.getData();
                        updateTrain(Tname2[0],Tname2[1]);
                        continue;
                    case "DeleteTrain":
                        String[] Tname3=msg.getData();
                        deleteTrain(Tname3[0]);
                        continue;
                    case "InsertTrain":
                        Train train=SetTrainFromData(msg);
                        insertTrain(train);
                        continue;
                    default:break;
                    }
                }
            } catch (IOException ex) {
            //throw new RuntimeException(ex);
        }
    }
    private String getRequstFromClient() throws IOException {
//        //InputStream input = m_socket.getInputStream();
//        byte[] buffer = new byte[2048];
//        int len=m_in.read(buffer);;
//        StringBuilder sb = new StringBuilder();
//        while ((len = m_in.read(buffer)) > 0) {
//            sb.append(new String(buffer, 0, len));
//        }
//        String str = sb.toString();
//        boolean contains = str.contains("ticket:1001");
        byte[] buffer = new byte[1024];
        int lenth = m_in.read(buffer);
        String str = new String(buffer, 0, lenth);
        System.out.println("客户端请求："+str);
        return str;
    }
//    private String GetStrFromClient() throws IOException{
//        BufferedReader in = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
//        String inputLine;
//        StringBuffer buffer = new StringBuffer();
//        while ((inputLine = in.readLine()) != null) {
//            System.out.println("客户端发来消息：" + inputLine);
//            buffer.append(inputLine);
//        }
//        String result = buffer.toString();
//        //toClientout.close();
//        //in.close();
//        return result;
//
//    }
    private String SendStrToClient(String msgg) throws IOException{
        //String msgg="hello client";
        OutputStream out=m_socket.getOutputStream();
        out.write(msgg.getBytes(StandardCharsets.UTF_8));
        out.flush();
        //PrintWriter toClientout = new PrintWriter(clientSocket.getOutputStream(), true);// 获取输出流，向客户端发送数据
        /*String mes="hello client";
        byte[] buff = new byte[2048];
        buff = mes.getBytes(StandardCharsets.UTF_8);
        m_out.write(buff);
        m_out.flush();
         */
        System.out.println("已发送");
        return null;

    }
    private String getDateFromClient() throws IOException{
        try{
        // 接收客户端发送的数据
        InputStream inputStream = m_socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = bufferedReader.readLine();
        // 输出接收到的数据
        System.out.println("Received message from client: " + str);
        return str;
        } catch (IOException e){
            e.printStackTrace();
        }
        /*
        String msgg="niao";
        OutputStream out=m_socket.getOutputStream();
        out.write(msgg.getBytes(StandardCharsets.UTF_8));
        //PrintWriter toClientout = new PrintWriter(clientSocket.getOutputStream(), true);// 获取输出流，向客户端发送数据
        BufferedReader in = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("客户端发来消息：" + inputLine);
            //toClientout.println("hello qt");
        }
        //toClientout.close();
        in.close();
        m_socket.close();
        //serverSocket.close();
        return inputLine;
         */
        return null;
    }
    private Massage splitTagAndData(String str) throws IOException{
        String[] Str=str.split(",");
        Massage msg=new Massage();
        msg.setTag(Str[0]);
        String[] array=new String[Str.length-1];
        for (int i = 1; i < Str.length; i++) {
            array[i - 1] = Str[i];
        }
        msg.setData(array);
//        System.out.println(msg.getTag());
//        for (int i = 0; i < msg.getData().length; i++) {
//            System.out.println(msg.getData()[i]);
//        }
        return msg;
    }
    private Seat SetSeatFromData(Massage msg_data) throws IOException{
        Seat seat=new Seat();
        //System.out.println(msg_data.getData().length);
        seat.setSeat_id(msg_data.getData()[0]);
        seat.setTrain_id(msg_data.getData()[1]);
        seat.setSeat_type(msg_data.getData()[2]);
        seat.setSeat_location(msg_data.getData()[3]);
        seat.setCarriage(msg_data.getData()[4]);
        seat.setSeat_raw(msg_data.getData()[5]);
        seat.setSeat_rest(msg_data.getData()[6]);
//        System.out.println(seat.getSeat_id()+" "+seat.getSeat_type());
        return seat;
    }
    private Orders SetOrdersFromData(Massage msg_data) throws IOException{
        Orders order=new Orders();
        //System.out.println(msg_data.getData().length);
        order.setOrders_id(msg_data.getData()[0]);
        order.setUser_id(msg_data.getData()[1]);
        order.setUser_idcard(msg_data.getData()[2]);
        order.setUser_name(msg_data.getData()[3]);
        order.setTrain_id(msg_data.getData()[4]);
        order.setTrain_name(msg_data.getData()[5]);
        order.setSeat_type(msg_data.getData()[6]);
        order.setSeat_location(msg_data.getData()[7]);
        order.setStatus(msg_data.getData()[8]);
        order.setStart(msg_data.getData()[9]);
        order.setTerminus(msg_data.getData()[10]);
        order.setCarriage(msg_data.getData()[11]);
        order.setSeat_raw(msg_data.getData()[12]);
        order.setStart_time(msg_data.getData()[13]);
        return order;
    }

    private User SetUserFromData(Massage msg_data)throws IOException{
        User user=new User();
        user.setUser_name(msg_data.getData()[0]);
        user.setUser_password(msg_data.getData()[1]);
        user.setUser_id(msg_data.getData()[2]);
        user.setUser_idcard(msg_data.getData()[3]);
        user.setUser_phone(msg_data.getData()[4]);
        user.setUser_type(msg_data.getData()[5]);
        return user;
    }
    private User SetUNPFromData(Massage msg_data)throws IOException{
        User user=new User();
        user.setUser_name(msg_data.getData()[0]);
        user.setUser_password(msg_data.getData()[1]);
        return user;
    }
    private Train SetTrainFromData(Massage msg_data)throws IOException{
        Train train=new Train();
        train.setTrain_id(msg_data.getData()[0]);
        train.setTrain_name(msg_data.getData()[1]);
        train.setTrain_type(msg_data.getData()[2]);
        train.setStart(msg_data.getData()[3]);
        train.setTerminus(msg_data.getData()[4]);
        return train;
    }
    private void FindRestSeat(String uid) throws IOException {
        //查讯所有余票(根据车次)
        //System.out.println("查询所有余票");
        SeatQu seatQu = new SeatQuImp();
        ArrayList<Seat> list =seatQu.FindFreeSeat(uid);
        StringBuilder builder = new StringBuilder();
        for (Seat seat:
                list) {
            System.out.println(seat);
            builder.append(seat);
            builder.append("\n");
        }
        String msg = new String(builder);
        if(msg.length()>0) SendStrToClient(msg);
        else SendStrToClient("输入有误，请输入正确的请求！");
    }

    private void FindAllSeat() throws IOException {
        SeatQu seatQu = new SeatQuImp();
        ArrayList<Seat> list =seatQu.FindAllSeat();
        StringBuilder builder = new StringBuilder();
        for (Seat seat:
                list) {
            System.out.println(seat);
            builder.append(seat);
            builder.append("\n");
        }
        String msg = new String(builder);
        if(msg.length()>0) SendStrToClient(msg);
        else SendStrToClient("输入有误，请输入正确的请求！");
    }
    private void updateSeat(Seat seat) throws IOException{
        SeatQu seatQu = new SeatQuImp();
        boolean flag=seatQu.updateSeat(seat);
        String flagg;
        if(flag)flagg="更新成功";
        else flagg="更新失败";
        SendStrToClient(flagg);
    }
    private void insertSeat(Seat seat) throws IOException{
        SeatQu seatQu = new SeatQuImp();
        boolean flag=seatQu.insertSeat(seat);
        String flagg;
        if(flag)flagg="插入座位成功";
        else flagg="插入座位失败";
        SendStrToClient(flagg);
    }
    private void deleteSeatById(String sid) throws IOException{
        SeatQu seatQu = new SeatQuImp();
        boolean flag=seatQu.deleteSeat(sid);
        String flagg;
        if(flag)flagg="删除座位成功";
        else flagg="删除座位失败";
        SendStrToClient(flagg);
    }
    private void findAllOrders() throws IOException {
        OrdersQu ordersQu = new OrdersQuImp();
        ArrayList<Orders> list =ordersQu.FindAllOrders();
        StringBuilder builder = new StringBuilder();
        for (Orders order:list) {
            System.out.println(order);
            builder.append(order);
            builder.append("\n");
        }
        String msg = new String(builder);
        if(msg.length()>0) SendStrToClient(msg);
        else SendStrToClient("输入有误，请输入正确的请求！");
    }

    private void insertOrder(Orders orders) throws IOException{
        OrdersQu order = new OrdersQuImp();
        boolean flag=order.addOrders(orders);
        String flagg;
        if(flag)flagg="添加订单成功";
        else flagg="添加订单失败";
        SendStrToClient(flagg);
    }

    private void findOrdersByUid(String Uid) throws IOException{
        OrdersQu order = new OrdersQuImp();
        ArrayList<Orders> list=order.getOrdersByUid(Uid);
        StringBuilder builder = new StringBuilder();
        for (Orders orders:
                list) {
            System.out.println(orders);
            builder.append(orders);
            builder.append("\n");
        }
        String msg = new String(builder);
        if(msg.length()>0) SendStrToClient(msg);
        else SendStrToClient("输入有误，请输入正确的请求！");
    }

    private void updateOrders(Orders order) throws IOException{
        OrdersQu ordersQu = new OrdersQuImp();
        boolean flag=ordersQu.updateOrders(order);
        String flagg;
        if(flag)flagg="更新订单成功";
        else flagg="更新订单失败";
        SendStrToClient(flagg);
    }
    private void deleteOrdersById(String sid) throws IOException{
        OrdersQu ordersQu = new OrdersQuImp();
        boolean flag=ordersQu.deleteOrders(sid);
        String flagg;
        if(flag)flagg="删除订单成功";
        else flagg="删除订单失败";
        SendStrToClient(flagg);
    }

    private void Login(User user) throws IOException{
        UserQu userQu=new UserQuImp();
        User us=userQu.login(user);
        String isok;
        if(us.getUser_name().contains(user.getUser_name()))isok= "登陆成功";
        else isok= "登陆失败";
        SendStrToClient(isok);
    }
    private void Register(User user) throws IOException{
        UserQu userQu=new UserQuImp();
        boolean isok=userQu.insert(user);
        if(isok)SendStrToClient("注册成功");
        else SendStrToClient("注册失败");
    }
    private void DeleteUser(String uname) throws IOException{
        UserQu userQu=new UserQuImp();
        User us=userQu.getUserByUname(uname);
        if(us.getUser_name().contains(uname)){
            boolean isok=userQu.delete(uname);
            SendStrToClient("删除用户成功");
        }
        else SendStrToClient("用户不存在");
    }
    private void UpdateUserPwd(User user) throws IOException{
        UserQu userQu=new UserQuImp();
        boolean isok=userQu.updateUserPwd(user);
        if(isok)SendStrToClient("修改密码成功");
        else SendStrToClient("修改密码失败");
    }
    private void findAllUsers() throws IOException{
        UserQuImp userQu = new UserQuImp();
        ArrayList<User>list = userQu.FindAllUser();
        StringBuilder builder = new StringBuilder();
        for (User user:
                list) {
            builder.append(user);
            builder.append("\n");
        }
        String msg = new String(builder);
        System.out.println(msg);
        if(msg.length()>0) SendStrToClient(msg);
        else SendStrToClient("输入有误，请输入正确的请求！");
    }
    private void findAllTrain() throws IOException{
        TrainQuImp trainQu=new TrainQuImp();
        ArrayList<Train>list=trainQu.FindAllTrain();
        StringBuilder builder = new StringBuilder();
        for (Train train:
                list) {
            builder.append(train);
            builder.append("\n");
        }
        String msg = new String(builder);
        System.out.println(msg);
        if(msg.length()>0) SendStrToClient(msg);
        else SendStrToClient("输入有误，请输入正确的请求！");
    }
   private void selectByStartAndTerminus(String start, String terminus) throws IOException{
       TrainQuImp trainQu=new TrainQuImp();
       ArrayList<Train>list=trainQu.selectByStartAndTerminus(start,terminus);
       StringBuilder builder = new StringBuilder();
       for (Train train:
               list) {
           builder.append(train);
           builder.append("\n");
       }
       String msg = new String(builder);
       System.out.println(msg);
       if(msg.length()>0) SendStrToClient(msg);
       else SendStrToClient("输入有误，请输入正确的请求！");
   }
    private void selectByTname(String Tname)throws IOException{
        TrainQuImp trainQu=new TrainQuImp();
        ArrayList<Train>list=trainQu.selectByTname(Tname);
        StringBuilder builder = new StringBuilder();
        for (Train train:
                list) {
            builder.append(train);
            builder.append("\n");
        }
        String msg = new String(builder);
        System.out.println(msg);
        if(msg.length()>0) SendStrToClient(msg);
        else SendStrToClient("输入有误，请输入正确的请求！");
    }
    private void updateTrain(String befroftname1,String latername) throws IOException{
        TrainQuImp trainQu=new TrainQuImp();
        boolean isok=trainQu.updateTrain(befroftname1,latername);
        if(isok)SendStrToClient("修改成功");
        else SendStrToClient("修改失败");
    }

    private void deleteTrain(String tname) throws IOException{
        TrainQuImp trainQu=new TrainQuImp();
        boolean isok=trainQu.deleteTrain(tname);
        if(isok)SendStrToClient("删除成功");
        else SendStrToClient("删除失败");
    }
    private void insertTrain(Train train) throws IOException{
        TrainQuImp trainQu = new TrainQuImp();
        boolean flag=trainQu.insertTrain(train);
        String flagg;
        if(flag)flagg="添加车次成功";
        else flagg="添加车次失败";
        SendStrToClient(flagg);
    }
}
