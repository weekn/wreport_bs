package weekn.wreport.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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
		
		new_report.setUser_id(user.getId());//添加周报只能添加自己的，所以从redis中取userid

		int id = report_server.addReport(new_report);
		response.setResponse(new_report);

		return response;

	}

	/**
	 * 获得用户周报
	 * 
	 * @throws MyException_noLogin
	 */
	@RequestMapping(value = "/report/user/{username}", method = RequestMethod.GET)
	public ResponseModel getUserReport(@PathVariable String username, HttpServletRequest request)
			throws MyException_noLogin {// POST /session # 创建新的会话（登录）
		System.out.println("ReportController-getUserReport" + username);
		ResponseModel response = new ResponseModel();

		SysUserModel user = new MySecurityManager().start(request, redis_s).end();
//		SysUserModel user =new SysUserModel();
		response.setResponse(report_server.getReportWithUser(user));

		return response;

	}

	/**
	 * 获得团队周报
	 */
	@RequestMapping(value = "/report/team", method = RequestMethod.GET)
	public ResponseModel getTeamReport() {// POST /session # 创建新的会话（登录）
		System.out.println("ReportController-getTeamReport");
		ResponseModel response = new ResponseModel();

		response.setResponse(report_server.getReportWithTeam());

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
