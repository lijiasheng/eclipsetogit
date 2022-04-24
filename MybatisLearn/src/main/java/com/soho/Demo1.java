package com.soho;

import com.soho.mapper.AccountMapper;
import com.soho.model.AccountDO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * MyBatis原生系统编程，主要分为下列步骤
 * （1）加载mybatis的核心配置文件，获取 SqlSessionFactory
 * （2）通过SqlSessionFactory获取SqlSession对象
 * （3）基于代理方式获取Mapper的代理对象:getMapper(Class clz)
 * （4）通过代理对象执行SQL
 * 基于动态代理/接口编程CRUD
 */
public class Demo1 {
    public static void main(String[] args) throws IOException {
        //1. 加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. 通过SqlSessionFactory获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3. 基于代理方式获取Mapper的代理对象:getMapper(Class clz)
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);

        //4. 通过代理对象执行SQL，select by id
        List<AccountDO> accountDO = accountMapper.selAccountById(1001);

        System.out.println(accountDO);

        //select for update + update
//        AccountDO accountDO1 = accountMapper.selAccountByIdForUpd(1001);
//        BigDecimal bal = accountDO1.getBal();
//        bal = bal.add( new BigDecimal("1000.00"));
//        accountDO1.setBal(bal);
//        accountMapper.updAccount(accountDO1);
//        sqlSession.commit();
//
//        //insert new account
//        accountDO1.setId(1003L);
//        accountMapper.addAccount(accountDO1);
//        sqlSession.commit();
//        //delete account
//        accountMapper.delAccountById(1002);
//        sqlSession.commit();

        //4. 释放资源
        sqlSession.close();
    }
}
