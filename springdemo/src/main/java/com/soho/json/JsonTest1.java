package com.soho.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonTest1 {
	
	/*
	 * Java Object convert to Json String
	 */
	private List<Person> listOfPersons = new ArrayList<Person>();
	
	public void setUp() {
	    listOfPersons.add(new Person(15, "John Doe", new Date()));
	    listOfPersons.add(new Person(20, "Janette Doe", new Date()));
	}
	
	/*
	 * Java列表对象转换成JSON串
	 */
	public void JavaList2JsonString() {
	    String jsonOutput= JSON.toJSONString(listOfPersons);
		System.out.println(jsonOutput);
		
		jsonOutput= JSON.toJSONString(listOfPersons,SerializerFeature.BeanToArray);

		System.out.println(jsonOutput);
	}
	
	/*
	 * JSONArray转换为JSON串
	 */
	public void JsonArray2JsonString() {
		JSONArray jsonArray = new JSONArray() ;
		for ( int i=0; i<2 ; i++ ) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("Age", 10);
			jsonObject.put("FULL NAME", "Doe " + i);
	        jsonObject.put("DATE OF BIRTH", "2016/12/12 12:12:12");
	        jsonArray.add(jsonObject);
		}
		
		String jsonOutput= JSON.toJSONString(jsonArray);
		System.out.println(jsonOutput);
	}
	/*
	 * JSON串转换为Java对象。
	 */
	public void JsonString2Bean() {
		Person person = new Person(20,"John Doe", new Date());
	    String jsonObject = JSON.toJSONString(person);
	    Person newPerson = JSON.parseObject(jsonObject, Person.class);
	    System.out.println(newPerson);
	}
	
	// java对象转Json字符串
	public void objectToJson() {
		
		//简单对象转JSON串.
		Person person = new Person(20,"John Doe", new Date());
		String personJson = JSON.toJSONString(person);
		System.out.println("简单Java对象转JSON:" + personJson);
		
		//List<Object>转JSON串.
		Person person1 = new Person(30,"John Li", new Date());
		Person person2 = new Person(40,"John Doe", new Date());
		
		List< Person > persons = new ArrayList<> ();
		persons.add(person1);
		persons.add(person2);
		
		//JAVA列表对象转成JSON数组
		String  personsJson = JSON.toJSONString(persons);
		System.out.println("List<Object>对象转JSON:" + personsJson );
	}
	
	/*
	 * JSON字符串转换成JAVA Bean或 List<Bean>
	 */
	public void JsonToObject() {
		 String jsonStr1 = "{'age':30, 'DATE OF BIRTH':'2020/03/23','Full Name':'John Li'}";
		 
		 Person person = JSON.parseObject(jsonStr1, Person.class);
		 System.out.println("json字符串转简单java对象:"+ person );
	
		 String jsonStr2 ="[{'age':30, 'DATE OF BIRTH':'2020/03/23','Full Name':'John Li'},{'age':40, 'DATE OF BIRTH':'2020/03/23','Full Name':'John Joe'}]" ;
		 
		 List<Person> persons = JSON.parseArray(jsonStr2, Person.class);
		 System.out.println("json字符串转List<Object>对象:"+persons.toString());
		 
		 
	}

	public static void main(String[] args) {
		JsonTest1 test = new JsonTest1();
		test.objectToJson();
		test.JsonToObject();
	}
}
