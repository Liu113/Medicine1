package com.medicine.utils;

import java.util.UUID;

public class FileNameUtils {
	
	public static String getNewFileName(String fileName){
		if(fileName == null && "".equals(fileName)){
			throw new RuntimeException("文件内容不能为空");
		}
		String suffix="";
		suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		String newFileName = UUID.randomUUID().toString().replace("-", "")+suffix;
		return newFileName;
	}
}
