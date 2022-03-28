package com.laptrinhjavasql.util;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavasql.enums.BuildingTypesEnum;

public class CommonUtil {
	public static String covertTypeCodeToName(String code) {
		List<String> names = new ArrayList<String>();
		String[] codes = code.split(",");
		for(String c : codes) {
			String name = BuildingTypesEnum.findNameByCode(c);
			names.add(name);
		}
		
 		return String.join(", ", names);
	}
}
