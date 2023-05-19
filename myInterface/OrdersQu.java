package 数据库12306.myInterface;
import 数据库12306.bean.Orders;
import 数据库12306.bean.Seat;
import 数据库12306.bean.Train;

import java.util.ArrayList;

public interface OrdersQu {
    //作用：查询所有购票记录信息。
    //参数：无
    //返回：包含所有购票记录信息的动态数组。
    public  ArrayList<Orders> FindAllOrders();

    //作用：根据人员号查询购票记录信息。
    //参数：人员号 uid。
    //返回：uid 对应的购票记录信息的动态数组。
    public  ArrayList<Orders> getOrdersByUid(String uid);

    //作用：在订单表中添加新的购票记录信息。
    //参数：r 表示购票记录信息。
    //返回：添加成功与否。
    public  boolean addOrders(Orders r);

    //作用：修改订单表中指定购票记录信息（改签）。
    //只修改车次信息和座位信息
    //参数：r 表示订单记录信息。
    //返回：修改成功与否
    public  boolean updateOrders(Orders r);

    //作用：删除订单表中指定购票记录信息。
    //参数：r 表示订单购票记录信息。
    //返回：删除成功与否。
    public  boolean deleteOrders(String oid);

    //作用：根据订单号，返回该订单
    //参数：ord_id 表示订单购票记录信息编号。
    //返回：该条订单。
    public Orders getOrdersByOid(String ord_id);

}
