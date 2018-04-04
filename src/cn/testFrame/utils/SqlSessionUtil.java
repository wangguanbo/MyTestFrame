package cn.testFrame.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtil {


    private  static  SqlSession sqlSession;

    private  SqlSessionUtil() {

    }

    public synchronized static SqlSession getSession()
    {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
        InputStream resourceAsStream ;

        if ( null != sqlSession )
        {
            return sqlSession;
        }
        else {

            synchronized (SqlSessionUtil.class) {

                try {

                    sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
                    resourceAsStream = Resources.getResourceAsStream("mybatisConfig.xml");
                    sqlSession = sqlSessionFactoryBuilder.build(resourceAsStream).openSession();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sqlSession;
    }

}
