package weekn.wreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import weekn.wreport.mapper.ProjectMapper;
import weekn.wreport.mapper.ReportMapper;
import weekn.wreport.model.ReportModel;
import weekn.wreport.model.ResponseModel;
import weekn.wreport.service.ReportService;

@RestController
public class ReportController {
	@Autowired
	ReportService report_server;
	
	/**
	 * 添加周报
	 */
	@RequestMapping(value = "/{username}/report", method = RequestMethod.POST)
	public ResponseModel addReport(@RequestBody ReportModel new_report,@PathVariable String username) {// POST /session # 创建新的会话（登录）
		System.out.println("ReportController-addReport"+new_report.getProblem());
		ResponseModel response=new ResponseModel();
		report_server.addReport(username,new_report);
		response.setResponse(new_report);
		
		return  response;
	
	}
	/**
	 * 获得用户周报
	 */
	@RequestMapping(value = "/{username}/report", method = RequestMethod.GET)
	public ResponseModel getUserReport(@PathVariable String username) {// POST /session # 创建新的会话（登录）
		System.out.println("ReportController-getUserReport"+username);
		ResponseModel response=new ResponseModel();
		
		response.setResponse(report_server.getReportWithUser(username));
		
		return  response;
	
	}
	
//	/**
//	 * 获得项目project
//	 */
//	
//	@Autowired
//	private ProjectMapper mapper;
//	@RequestMapping(value = "/project", method = RequestMethod.GET)
//	public ResponseModel getProject() {// POST /session # 创建新的会话（登录）
//		System.out.println("ReportController-getProject");
//		ResponseModel response=new ResponseModel();
//		
//		response.setResponse(mapper.getProject());
//		
//		return  response;
//	
//	}
}
