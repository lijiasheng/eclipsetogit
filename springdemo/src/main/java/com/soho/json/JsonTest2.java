package com.soho.json;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;

/*
 * 复杂JSON的解析实例
 */

public class JsonTest2 {
	
	public List<Query> jsonToComplexObj() throws IOException {
        // 读取类路径下的query.json文件
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream inputStream = cl.getResourceAsStream("query.json");
        //将文件输入流转成字符串
        String jsontext = IOUtils.toString(inputStream, "utf8");
        System.out.println("输入流为: " + jsontext );
        
        // 先将字符串转为List数组
        List<Query> queryList = JSON.parseArray(jsontext, Query.class);
        for (Query query : queryList) {
            List<Column> columnList = new ArrayList<Column>();
            List<LinkedMap<String,Object>> columns = query.getColumn();
            for (LinkedMap<String, Object> linkedMap : columns) {
                //将map转化为java实体类
                Column column = (Column)map2Object(linkedMap, Column.class);
                System.out.println(column.toString());
                columnList.add(column);
            }
            query.setColumnList(columnList); //为columnList属性赋值
        }
        return queryList;
    }
	
    public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                String flag = (String) map.get(field.getName());
                if(flag != null){
                    if(flag.equals("false") || flag.equals("true")){
                        field.set(obj, Boolean.parseBoolean(flag));
                    }else{
                        field.set(obj, map.get(field.getName()));
                    }
                }                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return obj;
    }

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		JsonTest2 test = new JsonTest2();
		
		test.jsonToComplexObj();

	}

}
