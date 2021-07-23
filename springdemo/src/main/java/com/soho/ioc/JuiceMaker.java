package com.soho.ioc;

public class JuiceMaker {
	
	private Source source = null;
	
	// 通过设置注入source对象
	public void setSource( Source source1) {
		this.source = source1;
	}
	
	//通过构造器注入。构造器参数source1必须和constructor-arg name相同
	public JuiceMaker( Source source1) {
		this.source = source1;
	}
	
	
	public String makeJuice() {
		
		String juice = "XXX点了：" + source.getFruit() + source.getSugar() + source.getSize();
		System.out.println(juice);
	    return juice;
	}
}
