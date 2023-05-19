package 数据库12306.myInterface;

import 数据库12306.bean.Orders;
import 数据库12306.bean.Seat;

import java.util.ArrayList;

public interface SeatQu {

    /**
     * 用来添加座位的方法
     * @param seat 要添加的座位对象
     * @return true表示添加成功，false表示添加失败
     */
    boolean insertSeat(Seat seat);

    /**
     *删除座位的方法,按编号删除
     * @param seat 要删除的座位
     * @return true表示删除成功，false表示删除失败
     */
    boolean deleteSeat(String seat);

    /**
     * 修改座位信息的方法
     * @param seat 修改的座位信息
     * @return true表示修改成功，false表示修改失败
     */
    boolean updateSeat(Seat seat);

    /**
     * 按车次编号查询空余座位信息的方法
     * @param train_id 要查询的车次编号
     * @return 查询成功返回list，查询失败返回null
     */
    public ArrayList<Seat> FindFreeSeat(String train_id);

    /**
     * 查询座位所有信息的方法
     * @return 查询成功返回list，查询失败返回null
     */
    public ArrayList<Seat> FindAllSeat();

    /**
     * 查询座位记录信息的方法
     * @return 查询成功返回Seat，查询失败返回null
     */
    public ArrayList<Seat> getSeatBySid(String seat_id);
}
