package com.soho.service;

import com.soho.mapper.AccountMapper;
import com.soho.model.AccountDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * AccountService:账户服务，位于domain层
 */

@Component("accountService")
public class AccountService {
    @Autowired
    AccountMapper accountMapper;
    //Inquiry Account Information
    public List<AccountDO >  queryAccountInfo(Integer accountId) {

        List<AccountDO> accountDO = accountMapper.selAccountById(accountId);
        System.out.println("Account Info:" + accountDO);
        return accountDO;
    }
}
