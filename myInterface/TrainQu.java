package 数据库12306.myInterface;

import 数据库12306.bean.Train;

import java.util.ArrayList;

public interface TrainQu {
    /**
     * 用来添加车次的方法
     * @param train 要添加的车次
     * @return true表示添加成功，false表示添加失败
     */
    boolean insertTrain(Train train);

    /**
     *删除车次的方法
     * @param tname 要删除的车次名
     * @return true表示删除成功，false表示删除失败
     */
    boolean deleteTrain(String tname);

    /**
     * 修改车次的方法
     * @param beforetname,latertname 修改的车次信息
     * @return true表示修改成功，false表示修改失败
     */
    boolean updateTrain(String beforetname, String latertname);

    /**
     * 查询车次信息的方法
     * @param tname 要查询的车次名，按名字查询
     * @return 查询成功返回Train，查询失败返回null
     */
    public ArrayList<Train> selectByTname(String tname);

    /**
     * 按两地名字查询车次
     * @param start,terminus 要查询的车次出发站终点站名字，按名字查询
     * @return 查询成功返回Train，查询失败返回null
     */
    public ArrayList<Train> selectByStartAndTerminus(String start,String terminus);

    /**
     * 查询车次所有信息的方法
     * @return 查询成功返回Train，查询失败返回null
     */
    public ArrayList<Train> FindAllTrain();
}
