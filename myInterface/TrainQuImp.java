package 数据库12306.myInterface;
import java.sql.Statement;
import java.util.ArrayList;
import 数据库12306.JDBC.JDBCCommand;
import 数据库12306.bean.Train;
import 数据库12306.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TrainQuImp implements TrainQu {
    @Override
    public boolean insertTrain(Train train) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into train(train_id,train_name,train_type,start,terminus) " +
                    "values (?,?,?,?,?)");
            ps.setString(1,train.getTrain_id());//设置第一个问号的值
            //System.out.println(train.getTrain_id());
            ps.setString(2,train.getTrain_name());//设置第一个问号的值
            //System.out.println(train.getTrain_name());
            ps.setString(3,train.getTrain_type());//设置第一个问号的值
            //System.out.println(train.getTrain_type());
            ps.setString(4,train.getStart());//设置第一个问号的值
            //System.out.println(train.getStart());
            ps.setString(5,train.getTerminus());//设置第一个问号的值
            //System.out.println(train.getTerminus());
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
    public boolean deleteTrain(String tname) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("delete from train where train_name = ?");
            ps.setString(1,tname);//设置第一个问号的值
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
    //修改列车名字
    public boolean updateTrain(String beforetname, String latertname) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("update train set train_name = ? where train_name = ?");
            ps.setString(1,latertname);//设置第一个问号的值
            ps.setString(2,beforetname);//设置第二个问号的值
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
    public ArrayList<Train> selectByTname(String tname) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Train> list= new ArrayList<>();
        try {
            ps = conn.prepareStatement("select train_id,train_name,train_type,start,terminus from train where train_name = ?");
            ps.setString(1,tname);//设置第一个问号的值
            //System.out.println(ps);
            rs = ps.executeQuery();
            while(rs.next()){
                Train train=new Train();
                train.setTrain_id(rs.getString("train_id"));
                //System.out.println(train.getTrain_id());
                train.setTrain_name(rs.getString("train_name"));
                //System.out.println(train.getTrain_name());
                train.setTrain_type(rs.getString("train_type"));
                //System.out.println(train.getTrain_type());
                train.setStart(rs.getString("start"));
                //System.out.println(train.getStart());
                train.setTerminus(rs.getString("terminus"));
                //System.out.println(train.getTerminus());
                list.add(train);
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
    public ArrayList<Train> selectByStartAndTerminus(String start, String terminus) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Train> list= new ArrayList<>();
        try {
            ps = conn.prepareStatement("select * from train where start=? and terminus=?");
            ps.setString(1,start);//设置第一个问号的值
            ps.setString(2,terminus);//设置第二个问号的值
            rs = ps.executeQuery();
            while(rs.next()){
                Train train=new Train();
                train.setTrain_id(rs.getString("train_id"));
                train.setTrain_name(rs.getString("train_name"));
                train.setTrain_type(rs.getString("train_type"));
                train.setStart(rs.getString("start"));
                train.setTerminus(rs.getString("terminus"));
                list.add(train);
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
    public ArrayList<Train> FindAllTrain() {
        Connection conn=JDBCCommand.getConnnection();
        Statement stmt=null;
        ResultSet rs=null;
        ArrayList<Train> list= new ArrayList<>();
        try{
            //获取Statement对象
            stmt=conn.createStatement();
            //发送SQL语句
            String sql="select * from train";
            rs=stmt.executeQuery(sql);
            //处理结果集
            while(rs.next()){
                Train train=new Train();
                train.setTrain_id(rs.getString("train_id"));
                //System.out.println(train.getTrain_id());
                train.setTrain_name(rs.getString("train_name"));
                //System.out.println(train.getTrain_name());
                train.setTrain_type(rs.getString("train_type"));
                //System.out.println(train.getTrain_type());
                train.setStart(rs.getString("start"));
                //System.out.println(train.getStart());
                train.setTerminus(rs.getString("terminus"));
                //System.out.println(train.getTerminus());
                list.add(train);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCCommand.close(conn,stmt,rs);
        }
        return null;
    }
}
