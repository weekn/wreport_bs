package com.nfjd.model;

public class TouristModel {
	private String msisdn;//加密后的手机号
	private String statist_month;
	private String cmcc_prov_prvd_id;//省份
	private String ascription;//归属地
	private String gender;
	private String age;
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getStatist_month() {
		return statist_month;
	}
	public void setStatist_month(String statist_month) {
		this.statist_month = statist_month;
	}
	public String getCmcc_prov_prvd_id() {
		return cmcc_prov_prvd_id;
	}
	public void setCmcc_prov_prvd_id(String cmcc_prov_prvd_id) {
		this.cmcc_prov_prvd_id = cmcc_prov_prvd_id;
	}
	public String getAscription() {
		return ascription;
	}
	public void setAscription(String ascription) {
		this.ascription = ascription;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
}
