package com.github.sdcxy.mybatis;

import com.github.sdcxy.entity.User;
import com.github.sdcxy.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName MybatisTest
 * @Description TODO
 * @Author lxx
 * @Date 2019/10/10 0:09
 **/
public class MybatisTest {

    public static void main(String[] args) {
        // 读取xml文件的方式
        readXmlMethod();
    }

    private static void readXmlMethod(){
        try{
            // 获取全局配置路径
            String resource = "mybatis-config.xml";
            // 使用Resources.getResourceAsStream加载全局配置文件
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 调用SqlSessionFactoryBuilder()方法构建读取到的数据源信息
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 调用SqlSessionFactory.openSession()方法获取到SqlSession对象
            SqlSession session = sqlSessionFactory.openSession();
            // 加载UserMapper到session Mapper中
            UserMapper userMapper = session.getMapper(UserMapper.class);
            // 调用方法返回User对象
            User user = userMapper.selectUser(1);
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
