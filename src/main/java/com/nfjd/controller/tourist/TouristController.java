package com.nfjd.controller.tourist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nfjd.model.DataTablePageCtrlModel;
import com.nfjd.model.MapValueModel;
import com.nfjd.service.traveller.TouristService;

@RestController
public class TouristController {
	@Autowired
    private TouristService service;
	
	@RequestMapping(value = "/tourist/{province}/profile/gender",method = RequestMethod.GET)//每个地区的热度
	public List<MapValueModel> getGender(@PathVariable String province){
        return service.getProfileGender(province);       
    }
	
	@RequestMapping(value = "/tourist/{province}/profile/age",method = RequestMethod.GET)//每个地区的热度
	public List<MapValueModel> getAge(@PathVariable String province){
		
        return service.getProfileAge(province);       
    }
	@RequestMapping(value = "/tourist/{province}/tourists",method = RequestMethod.GET)//每个地区的热度
	public DataTablePageCtrlModel getTourist(@PathVariable String province,DataTablePageCtrlModel datatablePageCtrl){
		
        return service.getTourist(datatablePageCtrl,province);       
    }
	
	
}
