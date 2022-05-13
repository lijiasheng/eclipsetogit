package com.simplebank.mapper;


import com.simplebank.model.AccountDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 定义DAO操作的Mapper接口
 */
@Mapper
public interface AccountMapper {
    List<AccountDO> selAccountById(Integer id) ;
    AccountDO selAccountByIdForUpd(Integer id);
    void addAccount( AccountDO accountDO);
    void updAccount( AccountDO accountDO);
    void delAccountById( Integer id );
}
