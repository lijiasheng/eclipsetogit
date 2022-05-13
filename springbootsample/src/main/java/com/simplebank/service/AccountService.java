package com.simplebank.service;

import com.simplebank.mapper.AccountMapper;
import com.simplebank.model.AccountDO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AccountService:账户服务，位于domain层
 * 演示Aware接口
 */

@Component("accountService")
public class AccountService implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Autowired
    AccountMapper accountMapper;
    //Inquiry Account Information
    public List<AccountDO>  queryAccountInfo(Integer accountId) {
        List<AccountDO> accountDO = accountMapper.selAccountById(accountId);
        System.out.println("Account Info:" + accountDO);
        System.out.println("applicationContext:" + applicationContext);
        return accountDO;
    }

    @Transactional
    public void saveAccount(AccountDO accountDO){
        accountMapper.addAccount(accountDO);
//        int i = 1/0;
//        accountMapper.addAccount(accountDO);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
