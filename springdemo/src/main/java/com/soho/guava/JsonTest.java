package com.soho.guava;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/*
 * Google Gson库的使用测试。
 */

/*
 * 类对象的序列化和反序列化
 */
class Student {
	public String name;
	public int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}

}

public class JsonTest {
	
	public static void jsonTree(JsonElement e) {
		if( e.isJsonNull()) {
			System.out.println("value:" + e.toString());
			 return  ;
		}
		if (e.isJsonPrimitive())
        {
            System.out.println("value:" + e.toString());
            return;
        }
		
		if (e.isJsonArray())
        {
			System.out.println("Array:");
			
            JsonArray ja = e.getAsJsonArray();
            if (null != ja)
            {
                for (JsonElement ae : ja)
                {
                    jsonTree(ae);
                }
            }
            return;
        }
		
		if( e.isJsonObject()) {
			Set< Entry <String,JsonElement >> es = e.getAsJsonObject().entrySet();
			
			for (Entry<String, JsonElement> en : es)
            {
				 String key = en.getKey();
				 System.out.println("key:" + key);
                jsonTree(en.getValue());
            }
		}
	}
	

	public static void main(String[] args) {
		
		Gson gson = new Gson();
		
		//基本数据类型的解析
		int number = gson.fromJson("100", int.class);
		double d = gson.fromJson("99.99", double.class);
		double d1 =gson.fromJson("\"99.99\"", double.class);
		boolean b = gson.fromJson("true", boolean.class);
		String str = gson.fromJson("String", String.class); 
		
		
		
		//POJO类的生成与解析
		List<Student> students = new ArrayList<> ();
		for ( int i =0; i< 10 ; i ++) {
			Student stu = new Student();
			stu.setName("name" + i );
			stu.setAge(i*5);
			students.add(stu);
		}
		
		//将列表对象转化成Json对象
		String jsonobject = gson.toJson(students);
		
		System.out.println("列表对象students生成Json对象" + jsonobject);
		
		//将Json对象反序列化成POJO对象。
		List<Student> studs = gson.fromJson(jsonobject,  new TypeToken<List<Student>>() {
		}.getType()) ;
		
		for( int i = 0 ; i < studs.size()  ; i ++ ) {
			Student s = studs.get(i) ;
			System.out.println(s);
		}
		
		// JsonParser的使用
		String jsonString = "{\"id\":1, \"name\":\"lzj\", \"cars\":[\"audi\", \"baoma\", \"benci\"]}";
		
		JsonElement jsonNode = JsonParser.parseString(jsonString);
		
		if( jsonNode.isJsonObject()) {
			JsonObject jsonObject = jsonNode.getAsJsonObject() ;
			JsonElement jsonElementId = jsonObject.get("id") ;
			int id = jsonElementId.getAsInt() ;
			System.out.println("id : " + id);
			
			JsonElement jsonElementName = jsonObject.get("name");
            String name = jsonElementName.getAsString();
            System.out.println("name : " + name);
            JsonElement jsonElementCars = jsonObject.get("cars");
            JsonArray arrays = jsonElementCars.getAsJsonArray();
            for(int i=0; i<arrays.size(); i++){
                JsonElement jsonElementArray = arrays.get(i);
                String car = jsonElementArray.getAsString();
                System.out.println("car" + i + " : " + car);
            }
        }
		
		try {
			String json = FileUtils.readFileToString( new File("D://temp//test.txt"), "UTF-8");
			
			JsonElement e = JsonParser.parseString(json);
			
			jsonTree(e);
			
		} catch (Exception e ) {
			e.printStackTrace();
		}

	}
}
