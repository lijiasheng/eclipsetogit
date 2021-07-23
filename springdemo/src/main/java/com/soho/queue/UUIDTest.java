package com.soho.queue;

public class UUIDTest {

	public static void main(String[] args) {
		
//		UUIDGenerator.init(100L);
		System.out.println("begin");
		long uuid = UUIDGenerator.generateUUID();
		System.out.println("uuid=" + uuid  );
	}
}
