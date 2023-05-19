package 数据库12306.JDBC;

import java.sql.*;

public class JDBCCommand {
    public static void main(String[] args){
        //加载MySQL驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //测试
            System.out.println("加载驱动成功！");
            //连接数据库,获得连接对象
            Connection conn= DriverManager.getConnection("jdbc:MySQL://localhost:3306/safe","root","Myb101010");
            System.out.println("数据库连接成功！");
            //创建执行环境
            Statement state=conn.createStatement();
            //执行sql语句，得到结果集
            ResultSet result=state.executeQuery("select * from user");
            //循环输出结果
            System.out.println("编号ID       姓名     密码      电话");
            while(result.next()){
                //System.out.println("编号ID  姓名  电话   密码");
                System.out.print(result.getString("user_id")+" ");
                System.out.print(result.getString("user_name")+" ");
                System.out.print(result.getString("user_password")+" ");
                System.out.print(result.getString("user_idcard")+" ");
                System.out.print(result.getString("user_phone")+" ");
                System.out.println(result.getString("user_type"));


            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("加载驱动失败！");
            System.out.println("数据库连接成功！");
        }
    }
    //获得连接对象的方法
    public  static Connection getConnnection(){

        try {
            //System.out.println("数据库连接成功");
            return DriverManager.getConnection("jdbc:MySQL://localhost:3306/safe","root","Myb101010");
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            throw new RuntimeException(e);
        }
    }
    //释放资源的方法
    public  static  void close(Connection conn, Statement statement, ResultSet result){
        //先判断三个参数是否为空，如果不为空，则进行关闭
        try {
            if (result != null) {
                result.close();
                result=null;//回收资源
            }
            if (statement != null) {
                statement.close();
                statement=null;//回收资源
            }
            if (conn != null) {
                conn.close();
                conn=null;//回收资源
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void close(Connection connection,PreparedStatement ps){

        try {
            if(ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection connection){

        try {
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
