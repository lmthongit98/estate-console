package com.laptrinhjavasql.util;

import java.util.List;

public class ValidateUtil {
	public static boolean isNotBlank(Integer value) {
		return value != null && value > 0;
	}
	
	public static boolean isNotBlank(String value) {
		return value != null && !value.isEmpty();
	}
	
	public static boolean isNotBlank(List<String> values) {
		return values != null && !values.isEmpty();
	}
}
