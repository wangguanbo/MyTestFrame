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
