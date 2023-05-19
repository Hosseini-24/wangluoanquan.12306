package 数据库12306.myInterface;

import 数据库12306.bean.User;
import java.util.ArrayList;

public interface UserQu {
        /**
         * 登录的方法
         * @param user 用来登录的用户 包括用户和密码
         * @return -1表示登录失败 1表示管理员登录成功 2表示学生登陆成功
         */
        User login(User user);

        /**
         * 用来添加学生的方法
         * @param user 要添加的学生对象 包括用户和密码
         * @return true表示添加成功，false表示添加失败
         */
        boolean insert(User user);

        /**
         *删除学生账户的方法
         * @param uname 要删除的用户名
         * @return true表示删除成功，false表示删除失败
         */
        boolean delete(String uname);

        /**
         * 修改学生信息的方法
         * @param user 修改的学生信息
         * @return true表示修改成功，false表示修改失败
         */
        boolean updateUserPwd(User user);

        /**
         * 查询用户信息的方法
         * @param uname 要查询的学生用户名
         * @return 查询成功返回User，查询失败返回null
         */
        User getUserByUname(String uname);

        /**
         * 查询用户所有信息的方法
         * @return 查询成功返回User，查询失败返回null
         */
        public ArrayList<User> FindAllUser();

}
