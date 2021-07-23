package com.soho.poi;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.hpsf.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;


public class PoiUtil {
	
	public static List<String> supportTypeList =
            Arrays.asList("int", "Integer", "float", "Float", "long", "Long", "double", "Double", "String","BigDecimal");


	 public static List readExcel(String fileName, int sheetNum, Class clazz) throws Exception{
	        //1.读取Excel文档对象
	        //将class的Field的名称<name, setName()>记录到map中
	        Map<String, Method> setMethodMap = new HashMap<>();
	        for (Field field : clazz.getDeclaredFields()) 
	        {
	            String name = field.getName();
	            if(supportTypeList.contains(field.getType().getSimpleName())) 
	            {
	                try{
	                    @SuppressWarnings("unchecked")
						Method setMethod = clazz.getDeclaredMethod(
	                            "set" + String.valueOf(name.charAt(0)).toUpperCase()+ field.getName().substring(1), field.getType());
	                    setMethodMap.put(name, setMethod);
	                }catch (NoSuchMethodException e) {
	                    continue;
	                }

	            }
	        }

	        ArrayList excelData = new ArrayList<>();
	        XSSFWorkbook hssfWorkbook = null;
	        try {
	            File file = new File(fileName);
	            InputStream resourceAsStream = new FileInputStream(file);
	            hssfWorkbook = new XSSFWorkbook(resourceAsStream);
	            XSSFSheet sheet = hssfWorkbook.getSheetAt(sheetNum);
	            int lastRowNum = sheet.getLastRowNum();
	            //Ref为了将第一行标题取出来
	            Ref<List> listRef = new Ref<>();
	            getSingleRow(sheet, 0, setMethodMap, listRef, clazz);//<1,name>

	            //将每一行的数据注入到class实例中并保存在list中
	            for(int i = 1;i <= lastRowNum; i++) 
	            {
	                try {
	                    Object singleRow = getSingleRow(sheet, i, setMethodMap, listRef, clazz);
	                    excelData.add(singleRow);
	                }catch (Exception e) {
	                    throw new Exception("该路径下 " + file.getPath() + " 文件内容有誤");
	                }
	            }
	            return excelData;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	 }
	 
	private static Object getSingleRow(XSSFSheet sheet, int rowNum, Map<String, Method> setMethod, Ref<List> listRef,
			Class clazz) throws Exception {
		XSSFRow row = sheet.getRow(rowNum);

		if (!checkRow(row, rowNum, setMethod)) {
			System.out.println("err: 第" + (rowNum + 1) + "行数据有误");
			throw new Exception("PoiUtil -> excel文件有誤");
		}

		// 此次读取是否为标题行读取
		boolean titleRow = (rowNum == 0 && Objects.isNull(listRef.ref));
		List<Object> rowData = new ArrayList<>();
		
		Object instance = clazz.newInstance();
		int lastCellNum = row.getLastCellNum() & '\uffff';

		for (int j = 0; j < lastCellNum; j++) 
		{
			XSSFCell cell = row.getCell(j);
			Object value;
			if (titleRow) {
				value = getValueFromCell(cell, String.class);
				rowData.add(j, value);
			} else {
				// 实例化Class对象，并注入数据
				Field declaredField = clazz.getDeclaredField(String.valueOf(listRef.ref.get(j)));
				Class<?> type = declaredField.getType();
				value = getValueFromCell(cell, type);
				Method set = setMethod.get(listRef.ref.get(j));
				set.invoke(instance, value);
			}

		}
		if (titleRow) {
			listRef.ref = rowData;
		}
		return instance;
	}

	private static Object getValueFromCell(XSSFCell cell, Class expectClazz) {
		Object value = null;
		// **--更改**String typeName = expectClazz.getTypeName();
		String typeName = expectClazz.getSimpleName();

		switch (cell.getCellType()) {
		case STRING:
			value = cell.getStringCellValue();
			break;
		case NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) 
			{
				java.util.Date date = cell.getDateCellValue();
				if (date != null) {
					if (typeName.equals("String")) {
						value = new SimpleDateFormat("yyyy-MM-dd").format(date);
					}
					if (typeName.equals("Date")) {
						value = date;
					}
				} else {
					value = null;
				}
			} else {
				Double cellValue = cell.getNumericCellValue();
				value = new DecimalFormat("0").format(cellValue);
				// 做一个基本类型的兼容
				if (typeName.equals("int") || typeName.equals("Integer")) {
					value = cellValue.intValue();
				}
				if (typeName.equals("double") || typeName.equals("Double")) {
					value = cellValue.doubleValue();
				}
				if (typeName.equals("float") || typeName.equals("Float")) {
					value = cellValue.floatValue();
				}
				if (typeName.equals("long") || typeName.equals("Long")) {
					value = cellValue.longValue();
				}
			}
			break;
		case FORMULA:
			// 导入时如果为公式生成的数据则无值
			if (!cell.getStringCellValue().equals("")) {
				value = cell.getStringCellValue();
			} else {
				value = cell.getNumericCellValue() + "";
			}
			break;
		case BLANK:
			break;
		case ERROR:
			break;
		case BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		default:
			value = null;
		}
		return value;
	}

	private static boolean checkRow(Row row, int rowNum, Map<String, Method> setMethod) {
		int lastCellNum = row.getLastCellNum() & '\uffff'; // 盗poi的...
		if (lastCellNum > setMethod.keySet().size()) {
			return false;
		}
		return true;
	}

	// -----------------------测试代码
	public static void main(String[] args) {
		try {
			List list = readExcel("D:\\temp\\scene.xlsx", 0, GameScene.class);
			list.forEach(sence -> {
				System.out.println(sence.toString());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	 
	 
	 
}