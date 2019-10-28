package com.medicine.utils;

import java.util.UUID;

public class UUIDUtils {

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	public static String getNewFileName(String fileName) {
		String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		return getUUID()+suffix;
	}
}
