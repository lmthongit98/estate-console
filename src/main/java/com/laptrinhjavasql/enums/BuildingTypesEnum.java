package com.laptrinhjavasql.enums;

public enum BuildingTypesEnum {
	TANG_TRET("tang-tret", "Tầng trệt"), NGUYEN_CAN("nguyen-can", "Nguyên căn"), NOI_THAT("noi-that", "Nội thất");
	
	private final String code;
	private final String name;

	BuildingTypesEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static String findNameByCode(String code) {
		for(BuildingTypesEnum typeEnum : BuildingTypesEnum.values()) {
			if(typeEnum.getCode().equals(code)) {
				return typeEnum.name;
			}
		}
		
		return "UNKNOW";
	}

}
