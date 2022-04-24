package com.soho.mapper;

import com.soho.model.AccountDO;

import java.util.List;

/**
 * 定义DAO操作的Mapper接口
 */
public interface AccountMapper {
//    AccountDO selAccountById( Integer id) ;

    List<AccountDO> selAccountById(Integer id) ;
    AccountDO selAccountByIdForUpd(Integer id);
    void addAccount( AccountDO accountDO);
    void updAccount( AccountDO accountDO);
    void delAccountById( Integer id );
}
