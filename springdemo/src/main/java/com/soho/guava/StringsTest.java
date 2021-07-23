package com.soho.guava;

import com.google.common.base.Strings;

public class StringsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(Strings.isNullOrEmpty(null));
		System.out.println(Strings.isNullOrEmpty(""));
		
		//Returns the given string if it is non-null; 
		//the empty string otherwise.
		System.out.println(Strings.nullToEmpty("ABC"));
		System.out.println(Strings.nullToEmpty(null));
		
		System.out.println(Strings.repeat("ABC", 3));
	}
}
