package 数据库12306.myInterface;
import java.sql.*;
import java.util.ArrayList;
import 数据库12306.JDBC.JDBCCommand;
import 数据库12306.bean.Orders;

public class OrdersQuImp implements OrdersQu {
    @Override
    public ArrayList<Orders> FindAllOrders() {
        Connection conn= JDBCCommand.getConnnection();
        Statement stmt=null;
        ResultSet rs=null;
        ArrayList<Orders> list= new ArrayList<>();
        try{
            //获取Statement对象
            stmt=conn.createStatement();
            //发送SQL语句
            String sql="select * from orders";
            rs=stmt.executeQuery(sql);
            //处理结果集
            while(rs.next()){
                Orders orders=new Orders();
                orders.setOrders_id(rs.getString("orders_id"));
                //System.out.println(train.getTrain_id());
                orders.setUser_id(rs.getString("user_id"));
                //System.out.println(train.getTrain_name());
                orders.setUser_idcard(rs.getString("user_idcard"));
                //System.out.println(train.getTrain_type());
                orders.setUser_name(rs.getString("user_name"));
                //System.out.println(train.getStart());
                orders.setTrain_id(rs.getString("train_id"));
                //System.out.println(train.getTerminus());
                orders.setTrain_name(rs.getString("train_name"));
                orders.setSeat_type(rs.getString("seat_type"));
                orders.setSeat_raw(rs.getString("seat_raw"));
                orders.setSeat_location(rs.getString("seat_location"));
                orders.setStatus(rs.getString("status"));
                orders.setStart_time(rs.getString("start_time"));
                orders.setStart(rs.getString("start"));
                orders.setTerminus(rs.getString("terminus"));
                orders.setCarriage(rs.getString("carriage"));
                list.add(orders);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCCommand.close(conn,stmt,rs);
        }
        return null;
    }

    @Override
    public ArrayList<Orders> getOrdersByUid(String uid) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Orders> list= new ArrayList<>();
        try {
            ps = conn.prepareStatement("select * from orders where user_id = ?");
            ps.setString(1,uid);//设置第一个问号的值
            //System.out.println(ps);
            rs = ps.executeQuery();
            while(rs.next()){
                Orders orders=new Orders();
                orders.setOrders_id(rs.getString("orders_id"));
                //System.out.println(train.getTrain_id());
                orders.setUser_id(rs.getString("user_id"));
                //System.out.println(train.getTrain_name());
                orders.setUser_idcard(rs.getString("user_idcard"));
                //System.out.println(train.getTrain_type());
                orders.setUser_name(rs.getString("user_name"));
                //System.out.println(train.getStart());
                orders.setTrain_id(rs.getString("train_id"));
                //System.out.println(train.getTerminus());
                orders.setTrain_name(rs.getString("train_name"));
                orders.setSeat_type(rs.getString("seat_type"));
                orders.setSeat_raw(rs.getString("seat_raw"));
                orders.setSeat_location(rs.getString("seat_location"));
                orders.setStatus(rs.getString("status"));
                orders.setStart_time(rs.getString("start_time"));
                orders.setStart(rs.getString("start"));
                orders.setTerminus(rs.getString("terminus"));
                orders.setCarriage(rs.getString("carriage"));
                list.add(orders);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCCommand.close(conn,ps,rs);
        }
        return null;//查询失败返回null return null;
    }

    @Override
    public boolean addOrders(Orders orders) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into orders values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,orders.getOrders_id());//设置第一个问号的值
            //System.out.println(train.getTrain_id());
            ps.setString(2,orders.getUser_id());//设置第一个问号的值
            //System.out.println(train.getTrain_name());
            ps.setString(3,orders.getUser_idcard());//设置第一个问号的值
            //System.out.println(train.getTrain_type());
            ps.setString(4,orders.getUser_name());//设置第一个问号的值
            //System.out.println(train.getStart());
            ps.setString(5,orders.getTrain_id());//设置第一个问号的值
            //System.out.println(train.getTerminus());
            ps.setString(6,orders.getTrain_name());//设置第一个问号的值
            ps.setString(7,orders.getSeat_type());//设置第一个问号的值
            ps.setString(8,orders.getSeat_location());//设置第一个问号的值
            ps.setString(9,orders.getStatus());//设置第一个问号的值
            ps.setString(10,orders.getStart());//设置第一个问号的值
            ps.setString(11,orders.getTerminus());//设置第一个问号的值
            ps.setString(12,orders.getCarriage());//设置第一个问号的值
            ps.setString(13,orders.getSeat_raw());//设置第一个问号的值
            ps.setString(14,orders.getStart_time());//设置第一个问号的值
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCCommand.close(conn,ps);
        }
        return false;
    }

    @Override
    public boolean updateOrders(Orders r) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("update orders set train_id = ?,train_name=?,seat_type=?,seat_raw=?," +
                    "seat_location=?,start=?,terminus=?,carriage=?,start_time=? " +
                    "where orders_id = ?");
            ps.setString(1,r.getTrain_id());
            ps.setString(2,r.getTrain_name());
            ps.setString(3,r.getSeat_type());
            ps.setString(4,r.getSeat_raw());
            ps.setString(5,r.getSeat_location());
            ps.setString(6,r.getStart());//
            ps.setString(7,r.getTerminus());
            ps.setString(8,r.getCarriage());
            ps.setString(9,r.getStart_time());
            ps.setString(10,r.getOrders_id());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCCommand.close(conn,ps);
        }
        return false;
    }

    @Override
    public boolean deleteOrders(String oid) {

        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("delete from orders where orders_id = ?");
            ps.setString(1,oid);//设置第一个问号的值
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCCommand.close(conn,ps);
        }
        return false;
    }

    @Override
    public Orders getOrdersByOid(String ord_id) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from orders where orders_id = ?");
            ps.setString(1,ord_id);//设置第一个问号的值
            //System.out.println(ps);
            rs = ps.executeQuery();
            Orders orders=new Orders();
            while(rs.next()){
                orders.setOrders_id(rs.getString("orders_id"));
                //System.out.println(train.getTrain_id());
                orders.setUser_id(rs.getString("user_id"));
                //System.out.println(train.getTrain_name());
                orders.setUser_idcard(rs.getString("user_idcard"));
                //System.out.println(train.getTrain_type());
                orders.setUser_name(rs.getString("user_name"));
                //System.out.println(train.getStart());
                orders.setTrain_id(rs.getString("train_id"));
                //System.out.println(train.getTerminus());
                orders.setTrain_name(rs.getString("train_name"));
                orders.setSeat_type(rs.getString("seat_type"));
                orders.setSeat_raw(rs.getString("seat_raw"));
                orders.setSeat_location(rs.getString("seat_location"));
                orders.setStatus(rs.getString("status"));
                orders.setStart_time(rs.getString("start_time"));
                orders.setStart(rs.getString("start"));
                orders.setTerminus(rs.getString("terminus"));
                orders.setCarriage(rs.getString("carriage"));
            } return orders;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCCommand.close(conn,ps,rs);
        }
        return null;//查询失败返回null return null;
    }
}
