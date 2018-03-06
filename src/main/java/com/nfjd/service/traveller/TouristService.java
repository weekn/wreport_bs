package com.nfjd.service.traveller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nfjd.mapper.TouristMapper;
import com.nfjd.mapper.UserMapper;
import com.nfjd.model.DataTablePageCtrlModel;
import com.nfjd.model.MapValueModel;
import com.nfjd.model.TouristModel;
import com.nfjd.model.UserModel;
import com.nfjd.tools.ProvinceMapperTool;

@Service
public class TouristService {
	@Autowired
	private TouristMapper mapper;
	
	public List<MapValueModel> getProfileGender(String province){
		List<MapValueModel> mv_list=mapper.getProfileGender(ProvinceMapperTool.getProvinceId(province));
		for (MapValueModel mm:mv_list){
			mm.setName(dealGenderName(mm.getName()));
		}
        return mv_list;
    }
	
	public List<MapValueModel> getProfileAge(String province){
		List<MapValueModel> mv_list=mapper.getProfileAge(ProvinceMapperTool.getProvinceId(province));
		for (MapValueModel mm:mv_list){
			mm.setName(dealAgeName(mm.getName()));
		}
        return mv_list;
    }
	
	public DataTablePageCtrlModel getTourist(DataTablePageCtrlModel datatablePageCtrl,String province){
		int start=datatablePageCtrl.getStart();
		int num=datatablePageCtrl.getLength();
		List<Object> tm=mapper.getTourists(ProvinceMapperTool.getProvinceId(province),start, num);
		int sum=mapper.geCountTourists(ProvinceMapperTool.getProvinceId(province));
		System.out.println("--------getTourist---  "+sum);
		datatablePageCtrl.setData(dealTourists(tm));
		datatablePageCtrl.setRecordsTotal(sum);
		return datatablePageCtrl;
	}
	private List<Object> dealTourists(List<Object> tm_list){
		for(Object tm:tm_list){
			((TouristModel) tm).setGender(dealGenderName(((TouristModel) tm).getGender()));
			((TouristModel) tm).setCmcc_prov_prvd_id(ProvinceMapperTool.getProvincefromId(((TouristModel) tm).getCmcc_prov_prvd_id()));
		}
		return tm_list;
	}
	
	private String dealAgeName(String name){
		String res = null;
		switch(name){
			case "0":
				res="小年轻";
				break;
			case "1":
				res="青年";
				break;
			case "2":
				res="中青年";
				break;
			case "3":
				res="中年";
				break;
			case "4":
				res="中老年";
				break;
			case "5":
				res="老年";
				break;
		}
		return res;
	}
	private String dealGenderName(String name){
		String res = null;
		if(name.equals("2")){
			res="男";
		}else if(name.equals("1")){
			res="女";
		}else{
			res="未知";
		}
		return res;
	}
	
	



}

