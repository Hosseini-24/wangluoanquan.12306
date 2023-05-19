package 数据库12306.myInterface;

import 数据库12306.bean.User;
import 数据库12306.JDBC.JDBCCommand;
import java.util.ArrayList;
import java.sql.*;

public class UserQuImp implements UserQu {
    @Override
    //登录
    public User login(User user) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select user_name,user_password from user where user_name =? and user_password =?");
            ps.setString(1,user.getUser_name());//设置第一个问号的值
            ps.setString(2,user.getUser_password());//设置第二个问号的值
            //测试SQL语句是否正确
            System.out.println(ps);
            rs = ps.executeQuery();
            String name=null,pwd=null;
            while(rs.next()){
                name= rs.getString("user_name");
                pwd= rs.getString("user_password");
                System.out.println(name+" "+pwd);
            }
            User us = new User(name, pwd);
            System.out.println(us.getUser_name()+" "+us.getUser_password());
            return us;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCCommand.close(conn,ps,rs);
        }return null;
    }

    @Override
    //添加
    public boolean insert(User user) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        try {
            //数据库中已经将type的默认值设置为2，所以这里不需要设置type的值
            ps = conn.prepareStatement("insert into user(user_id,user_name,user_password,user_idcard,user_phone,user_type) values (?,?,?,?,?,?)");
            ps.setString(1,user.getUser_id());//设置第一个问号的值
            //System.out.println(user.getUser_id());
            ps.setString(2,user.getUser_name());//设置第一个问号的值
            //System.out.println(user.getUser_name());
            ps.setString(3,user.getUser_password());//设置第二个问号的值
            //System.out.println(user.getUser_password());
            ps.setString(4,user.getUser_idcard());//设置第二个问号的值
            //System.out.println(user.getUser_idcard());
            ps.setString(5,user.getUser_phone());//设置第二个问号的值
            //System.out.println(user.getUser_phone());
            ps.setString(6,user.getUser_type());//设置第二个问号的值
            //System.out.println(user.getUser_type());
            ps.executeUpdate();
           // System.out.println(ps);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCCommand.close(conn,ps);
        }
        return false;
    }

    @Override
    public boolean delete(String uname) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("delete from user where user_name = ?");
            ps.setString(1,uname);//设置第一个问号的值
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
    //更改
    public boolean updateUserPwd(User user) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("update user set user_password = ? where user_name = ?");
            ps.setString(1,user.getUser_password());//设置第一个问号的值
            ps.setString(2,user.getUser_name());//设置第二个问号的值
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
    public User getUserByUname(String uname) {
        Connection conn = JDBCCommand.getConnnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String upass = null;
        try {
            ps = conn.prepareStatement("select user_password,user_type from user where user_name = ?");
            ps.setString(1,uname);//设置第一个问号的值
            rs = ps.executeQuery();
            while(rs.next()){
                upass = rs.getString("user_password");
            }
            return new User(uname,upass);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCCommand.close(conn,ps,rs);
        }
        return null;//查询失败返回null
    }

    //查询所有用户信息
    public ArrayList<User> FindAllUser(){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        ArrayList<User> list= new ArrayList<>();
        try{
            //获得数据的连接
            conn=JDBCCommand.getConnnection();
            //获取Statement对象
            stmt=conn.createStatement();
            //发送SQL语句
            String sql="select * from user";
            rs=stmt.executeQuery(sql);
            //处理结果集
            while(rs.next()){
                User user=new User();
                user.setUser_id(rs.getString("user_id"));
                user.setUser_name(rs.getString("user_name"));
                user.setUser_password(rs.getString("user_password"));
                user.setUser_idcard(rs.getString("user_idcard"));
                user.setUser_phone(rs.getString("user_phone"));
                user.setUser_type(rs.getString("user_type"));
                list.add(user);
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
