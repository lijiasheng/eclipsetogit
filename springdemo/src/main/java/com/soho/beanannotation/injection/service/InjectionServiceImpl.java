package com.soho.beanannotation.injection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soho.beanannotation.injection.dao.InjectionDAO;

@Component
public class InjectionServiceImpl implements InjectionService {
	
	@Autowired
	private InjectionDAO  injectionDAO = null; 

	@Override
	public void save(String arg) {
		// TODO Auto-generated method stub
		System.out.println("service接收参数"+ arg);
		arg = arg + ":" + this.hashCode();
		injectionDAO.save(arg);
	}
}
