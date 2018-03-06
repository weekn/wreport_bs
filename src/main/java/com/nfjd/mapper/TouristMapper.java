package com.nfjd.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nfjd.model.MapValueModel;
import com.nfjd.model.TouristModel;
public interface TouristMapper {
	public List<MapValueModel>  getSpotsRegionHot(@Param(value="province")String province);
	public List<MapValueModel>  getSpotsHot(@Param(value="num")int num,@Param(value="province")String province);
	public List<MapValueModel>  getProfileGender(@Param(value="province")String province);
	public List<MapValueModel>  getProfileAge(@Param(value="province")String province);
	
	public List<Object>  getTourists(@Param(value="province")String province,@Param(value="start")int start,@Param(value="num")int num);
	public int geCountTourists(@Param(value="province")String province);
	
}
