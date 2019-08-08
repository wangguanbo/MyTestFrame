package cn.testFrame;

import cn.testFrame.model.User;
import cn.testFrame.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.*;
import java.util.List;

public class TestConnect {


    public static void main(String[] args) {

        TestConnect_02();
        //TestConnect();
    }

    private static void TestConnect_02() {

           /* SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatisConfig.xml");
            SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsStream);
            SqlSession sqlSession = build.openSession();*/

            SqlSession session = SqlSessionUtil.getSession();
            System.out.println("sesssion对象及其地址==="+session);
            User user = session.selectOne("selectUserById",1);
            System.out.print(user);
            //session.close();

            //SqlSession session1 = SqlSessionUtil.getSession();
            //session == session1  所引用的地址一致 , 继续查询不需要进行关闭  //Executor was closed.
            System.out.println("sesssion对象及其地址==="+session);
            List<User> selectUserByName = session.selectList("selectUserByName", "2");
            for (User user1 : selectUserByName){
                System.out.println("模糊查询出来的用户名称为====="+user1);
            }
           /* user.setName(444);
            user.setAge(4444);
            session.insert("saveUser",user);
            session.commit();*/
            System.out.println("查询新用户");
            List<User> selectUserList = session.selectList("selectUserList");
            for(User u : selectUserList)
            {
                System.out.println(u);
            }

    }



    public static void TestConnect() {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://192.168.59.138:3306/strutsDemo?character=utf-8", "root", "root");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * FROM  t_user");
            while (resultSet.next()) {
                System.out.print(resultSet.getString("name") + resultSet.getString("uuid"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("===");
        System.out.println("冒泡");

        int[] aray = {1, 3, 5, 7, 5, 6, 3, 4, 6, 9, 7, 8};

        for (int i = 0; i < aray.length - 1; i++) {
            for (int j = 0; j < aray.length - i - 1; j++) {
                if (aray[j + 1] < aray[j]) {
                    int temp = aray[j];
                    aray[j] = aray[j + 1];
                    aray[j + 1] = temp;
                }
            }
        }

        for (int ayay_ : aray) {
            System.out.print(ayay_);
        }



    }

}