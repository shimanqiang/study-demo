package me.shimanqiang.mybatis;

import me.shimanqiang.mybatis.mapper.TestMapper;
import me.shimanqiang.mybatis.po.TestPo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author shimanqiang
 * @since 2019/9/6 10:19
 */
public class MyBatisDemo {

    public static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        TestPo testPo = mapper.selectByPrimaryKey(1L);
        System.out.println(testPo);
    }
}
