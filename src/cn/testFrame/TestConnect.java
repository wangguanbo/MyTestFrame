package cn.testFrame;

import java.sql.*;

public class TestConnect {

    public TestConnect() {

    }

    public static void main(String[] args) {

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
    }


}
