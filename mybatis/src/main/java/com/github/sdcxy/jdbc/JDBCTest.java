package com.github.sdcxy.jdbc;

import com.github.sdcxy.entity.User;

import java.sql.*;

/**
 * @ClassName JDBCTest
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/9 15:46
 **/
public class JDBCTest {

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        // 创建一个User对象来接收查询结果
        User user = new User();
        try{
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 设置数据库源
            String url = "jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf8&useSSL=false";
            String username = "root";
            String password = "root";
            // 获取数据库链接
            connection = DriverManager.getConnection(url,username,password);
            // 查询数据库语句
            String sql = "select * from sys_user where id=?";
            // 查询数据库
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,1);
            // 获得结果集
            resultSet = preparedStatement.executeQuery();
            // 遍历结果集
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setAge(resultSet.getInt("age"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setRemark(resultSet.getString("remark"));
            }
            System.out.println(
                    "{"+"id:" + user.getId()+","
                       +"username:"+user.getUsername()+","
                       +"age:"+user.getAge()+","
                       +"telephone:"+user.getTelephone()+","
                       +"remark:"+user.getRemark() +"}"
                    );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection,preparedStatement,resultSet);
        }
    }


    private static void close(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
        try{
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
