package com.nfjd.tools;

public class ProvinceMapperTool {
	static public String getProvinceId(String province){
		String res = null;
		switch(province){
			case "sichuan":
				res="13000";
				break;
			case "qinghai":
				res="11200";
				break;
			case "xizang":
				res="13100";
				break;
		}
		return res;
	}
	static public String getProvincefromId(String id){
		String res = null;
		switch(id){
			case "13000":
				res="四川";
				break;
			case "11200":
				res="青海";
				break;
			case "13100":
				res="西藏";
				break;
		}
		return res;
		
	}
}
