package com.nfjd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nfjd.model.LocationModel;
import com.nfjd.model.MapValueModel;

public interface BroadbandMapper {
	public List<MapValueModel>  getRegionHot(@Param(value="province")String province);
	
	public List<MapValueModel>  getProfileGender(@Param(value="province")String province);
	public List<MapValueModel>  getProfileAge(@Param(value="province")String province);
	
	public List<Object>  getCustomers(@Param(value="province")String province,@Param(value="start")int start,@Param(value="num")int num);
	public int geCountCustomers(@Param(value="province")String province);
	
	public List<LocationModel> getCustomersLocation(@Param(value="province")String province,@Param(value="city")String city);
}
