package com.soho.beanannotation.injection.dao;

import org.springframework.stereotype.Repository;

@Repository
public class InjectionDAOImpl implements InjectionDAO {

	@Override
	public void save(String arg) {
		// TODO Auto-generated method stub
		System.out.println("模拟数据库的保持操作..." + arg );
	}
}
