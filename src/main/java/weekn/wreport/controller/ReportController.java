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
	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public ResponseModel addReport(@RequestBody ReportModel new_report) {// POST /session # 创建新的会话（登录）
		System.out.println("ReportController-addReport"+new_report.getProblem());
		ResponseModel response=new ResponseModel();
		report_server.addReport(new_report);
		response.setResponse(new_report);
		
		return  response;
	
	}
	/**
	 * 获得用户周报
	 */
	@RequestMapping(value = "/report/user/{username}", method = RequestMethod.GET)
	public ResponseModel getUserReport(@PathVariable String username) {// POST /session # 创建新的会话（登录）
		System.out.println("ReportController-getUserReport"+username);
		ResponseModel response=new ResponseModel();
		
		response.setResponse(report_server.getReportWithUser(username));
		
		return  response;
	
	}
	/**
	 * 更新
	 */
	@RequestMapping(value = "/report/{report_id}", method = RequestMethod.PUT)
	public ResponseModel updateReport(@RequestBody ReportModel new_report,@PathVariable String report_id) {// POST /session # 创建新的会话（登录）
		System.out.println("ReportController-updateReport"+new_report.getUser_id());
		ResponseModel response=new ResponseModel();
		
		try {
			new_report.setId(Integer.parseInt(report_id));
			report_server.updateReport(new_report);
			
			response.setMessage("success");
		}catch(Exception e){
			e.printStackTrace();
			response.setMessage("false");
		}
		
		
//		response.setResponse(report_server.getReportWithUser(username));
		
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
