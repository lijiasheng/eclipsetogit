package com.soho;

import com.soho.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring集成MyBatis
 */
public class Demo2 {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContex = new ClassPathXmlApplicationContext("applicationcontext.xml");
        AccountService accountService = (AccountService) applicationContex.getBean("accountService");
        accountService.queryAccountInfo(1001);

        System.in.read();
    }
}
