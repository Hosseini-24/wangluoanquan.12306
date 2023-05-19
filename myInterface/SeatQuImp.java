package 数据库12306.myInterface;

import 数据库12306.JDBC.JDBCCommand;
import 数据库12306.bean.Orders;
import 数据库12306.bean.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SeatQuImp implements SeatQu {
    @Override
    public boolean insertSeat(Seat seat) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into seat values (?,?,?,?,?,?,?)");
            ps.setString(1,seat.getTrain_id());//设置第一个问号的值
            //System.out.println(train.getTrain_id());
            ps.setString(2,seat.getSeat_type());//设置第一个问号的值
            //System.out.println(train.getTrain_name());
            ps.setString(3,seat.getSeat_location());//设置第一个问号的值
            //System.out.println(train.getTrain_type());
            ps.setString(4,seat.getCarriage());//设置第一个问号的值
            //System.out.println(train.getStart());
            ps.setString(5,seat.getSeat_raw());
            ps.setString(6,seat.getSeat_id());
            ps.setString(7,seat.getSeat_rest());
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
    public boolean deleteSeat(String seat_id) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("delete from seat where seat_id=?");
            ps.setString(1,seat_id);//设置第一个问号的值
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
    public boolean updateSeat(Seat seat) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("update seat set train_id=?,seat_location=?,carriage=?,seat_raw=?,seat_rest=? where seat_id= ?");
            ps.setString(1,seat.getTrain_id());
            ps.setString(2,seat.getSeat_location());
            ps.setString(3,seat.getCarriage());
            ps.setString(4,seat.getSeat_raw());
            ps.setString(5,seat.getSeat_rest());
            ps.setString(6,seat.getSeat_id());
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
    public ArrayList<Seat> FindFreeSeat(String train_id) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Seat> list= new ArrayList<>();
        try {
            ps = conn.prepareStatement("select * from seat where train_id = ?");
            ps.setString(1,train_id);//设置第一个问号的值
            //System.out.println(ps);
            rs = ps.executeQuery();
            while(rs.next()){
                Seat seat=new Seat();
                seat.setTrain_id(rs.getString("train_id"));
                seat.setSeat_type(rs.getString("seat_type"));
                seat.setSeat_location(rs.getString("seat_location"));
                seat.setCarriage(rs.getString("carriage"));
                seat.setSeat_raw(rs.getString("seat_raw"));
                seat.setSeat_id(rs.getString("seat_id"));
                seat.setSeat_rest(rs.getString("seat_rest"));
                list.add(seat);
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
    public ArrayList<Seat> FindAllSeat() {
        Connection conn= JDBCCommand.getConnnection();
        Statement stmt=null;
        ResultSet rs=null;
        ArrayList<Seat> list= new ArrayList<>();
        try{
            //获取Statement对象
            stmt=conn.createStatement();
            //发送SQL语句
            String sql="select * from seat";
            rs=stmt.executeQuery(sql);
            //处理结果集
            while(rs.next()){
                Seat seat=new Seat();
                seat.setTrain_id(rs.getString("train_id"));
                seat.setSeat_type(rs.getString("seat_type"));
                seat.setSeat_location(rs.getString("seat_location"));
                seat.setCarriage(rs.getString("carriage"));
                seat.setSeat_raw(rs.getString("seat_raw"));
                seat.setSeat_id(rs.getString("seat_id"));
                seat.setSeat_rest(rs.getString("seat_rest"));
                list.add(seat);
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
    public ArrayList<Seat> getSeatBySid(String seat_id) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Seat> list= new ArrayList<>();
        try {
            ps = conn.prepareStatement("select * from seat where seat_id = ?");
            ps.setString(1,seat_id);//设置第一个问号的值
            //System.out.println(ps);
            rs = ps.executeQuery();
            while(rs.next()){
                Seat seat=new Seat();
                seat.setTrain_id(rs.getString("train_id"));
                seat.setSeat_type(rs.getString("seat_type"));
                seat.setSeat_location(rs.getString("seat_location"));
                seat.setCarriage(rs.getString("carriage"));
                seat.setSeat_raw(rs.getString("seat_raw"));
                seat.setSeat_id(rs.getString("seat_id"));
                seat.setSeat_rest(rs.getString("seat_rest"));
                list.add(seat);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCCommand.close(conn,ps,rs);
        }
        return null;//查询失败返回null return null;
    }
}
