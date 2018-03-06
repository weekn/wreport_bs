package com.nfjd.controller.tourist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nfjd.model.MapValueModel;
import com.nfjd.service.traveller.HotService;

@RestController
public class HotController {
	@Autowired
    private HotService service;
	
	@RequestMapping(value = "/tourist/{province}/region/hot",method = RequestMethod.GET)//每个地区的热度
	public List<MapValueModel> getSpotsRegionHot(@PathVariable String province){
        return service.getSpotsRegionHot(province);       
    }
	
	@RequestMapping(value = "/tourist/{province}/spots/hot",method = RequestMethod.GET)//每个景点的热度
	public List<MapValueModel> getHotSpots(@PathVariable String province,
			@RequestParam(value = "num", required = false) Integer  num){
		if(num==null){
			num=10;
		}
        return service.getSpotsHot(num,province);       
    }
}
