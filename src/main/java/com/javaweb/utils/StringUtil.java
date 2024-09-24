package com.javaweb.utils;

public class StringUtil {
	//Ham nay ktra string co rong hay null k
	public static boolean checkString(String data) {
		if(data!=null && !data.equals("")) {
			return true;
		}
		else {
			return false;
		}
	}

}
