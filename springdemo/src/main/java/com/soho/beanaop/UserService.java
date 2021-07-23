package com.soho.beanaop;

public class UserService {
	
	/*
	 * 对标有注解@GlobalTransaction的方法进行拦截
	 */
	
	@GlobalTransaction(name="userService")
	public String perform() {
		System.out.println("globaltransaction perform...");
        return "perform";
	}
	
	@SagaAction(name="transferIn")
	public String  transferIn() {
		
		System.out.println(" sagaaction transferIn...");
        return "transferIn";
	}
}