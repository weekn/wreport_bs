package weekn.wreport.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import weekn.wreport.model.ReportModel;
import weekn.wreport.model.ResponseModel;
import weekn.wreport.model.SysUserModel;
import weekn.wreport.security.MyException_noLogin;
import weekn.wreport.security.MySecurityManager;
import weekn.wreport.service.RedisService;
import weekn.wreport.service.ReportService;
import weekn.wreport.service.imp.ReportServiceimpl;

@RestController
public class ReportController {
	@Autowired
	ReportServiceimpl report_server;
	@Autowired
	RedisService redis_s;

	/**
	 * 添加周报
	 * 
	 * @throws MyException_noLogin
	 */
	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public ResponseModel addReport(@RequestBody ReportModel new_report, HttpServletRequest request)
			throws MyException_noLogin {// POST /session # 创建新的会话（登录）
		System.out.println("ReportController-addReport" + new_report.getProblem());
		ResponseModel response = new ResponseModel();

		SysUserModel user = new MySecurityManager().start(request, redis_s).end();

		new_report.setUser_id(user.getId());// 添加周报只能添加自己的，所以从redis中取userid
		new_report.setUser_name(user.getUsername());
		int id = report_server.addReport(new_report);
		response.setResponse(new_report);

		return response;

	}

	/**
	 * 添加汇总周报
	 * 
	 * @throws MyException_noLogin
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/report/sumarize/project/{project_id}", method = RequestMethod.POST)
	public ResponseModel summarizeReport(@RequestBody ReportModel new_report, @PathVariable int project_id,
			HttpServletRequest request) throws MyException_noLogin, JsonProcessingException {// POST /session #
																								// 创建新的会话（登录）
		System.out.println("ReportController-summarizeReport-----" + new_report.getProblem());
		ResponseModel response = new ResponseModel();

		SysUserModel user = new MySecurityManager().start(request, redis_s).end();

		new_report.setProject_id(project_id);
		report_server.summarizeReport(new_report, user);
		response.setStatus(200);

		return response;

	}

	/**
	 * 获得用户周报
	 * 
	 * @throws MyException_noLogin
	 */
	@RequestMapping(value = "/report/user/{user_id}", method = RequestMethod.GET)
	public ResponseModel getUserReport(@PathVariable String user_id,
			@RequestParam(value = "time", required = false) Long time, HttpServletRequest request)
			throws MyException_noLogin {// POST /session # 创建新的会话（登录）

		if(time==null) {
			time=new Date().getTime();
		}
		
		
		System.out.println("ReportController-getUserReport" + user_id);
		

		SysUserModel user = new MySecurityManager().start(request, redis_s).end();
		// SysUserModel user =new SysUserModel();
		ResponseModel response = new ResponseModel();
		response.setResponse(report_server.getReportWithUser(user,time));

		return response;

	}

	/**
	 * 获得团队周报
	 * 
	 * @throws JsonProcessingException
	 * @throws MyException_noLogin
	 */
	@RequestMapping(value = "/report/team", method = RequestMethod.GET)
	public ResponseModel getTeamReport(@RequestParam(value = "time", required = false) Long time,
			HttpServletRequest request) throws JsonProcessingException, MyException_noLogin {// POST
																														// /session
																														// #
																														// 创建新的会话（登录）
		System.out.println("ReportController-getTeamReport");
		if(time==null) {
			time=new Date().getTime();
			System.out.println("time----null-----------------------");
		}
		
		SysUserModel user = new MySecurityManager().start(request, redis_s).end();
		
		ResponseModel response = new ResponseModel();
		response.setResponse(report_server.getReportWithTeam(user,user.getTeam_id(),time));

		return response;

	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/report/{report_id}", method = RequestMethod.PUT)
	public ResponseModel updateReport(@RequestBody ReportModel new_report, @PathVariable String report_id) {// POST
																											// /session
																											// #
																											// 创建新的会话（登录）
		System.out.println("ReportController-updateReport" + new_report.getUser_id());
		ResponseModel response = new ResponseModel();

		try {
			new_report.setId(Integer.parseInt(report_id));
			report_server.updateReport(new_report);

			response.setMessage("success");
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("false");
		}

		// response.setResponse(report_server.getReportWithUser(username));

		return response;

	}

	/**
	 * 删除周报
	 */
	@RequestMapping(value = "/report/{report_id}", method = RequestMethod.DELETE)
	public ResponseModel deleteReport(@PathVariable String report_id) {// POST /session # 创建新的会话（登录）
		System.out.println("ReportController-deleteReport");
		ResponseModel response = new ResponseModel();

		response.setResponse(report_server.deleteReport(Integer.parseInt(report_id)));

		return response;

	}

}
