package weekn.wreport.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import weekn.wreport.dao.ProjectDao;
import weekn.wreport.model.ProjectModel;
import weekn.wreport.model.ReportModel;
import weekn.wreport.model.ResponseModel;
import weekn.wreport.model.SysUserModel;
import weekn.wreport.security.MyException_noLogin;
import weekn.wreport.security.MySecurityManager;
import weekn.wreport.service.RedisService;
import weekn.wreport.service.imp.ProjectServiceimpl;

@RestController
public class ProjectController {
	@Autowired
	ProjectServiceimpl  project_service;
	
	@Autowired
	RedisService redis_s;
	
	/**
	 * 添加项目
	 * @throws MyException_noLogin 
	 */
	@RequestMapping(value = "/project", method = RequestMethod.POST)
	public ResponseModel addReport(@RequestBody ProjectModel project, HttpServletRequest request) throws MyException_noLogin {// POST /session # 创建新的会话（登录）
		System.out.println("ReportController-addReport");
		
		SysUserModel user = new MySecurityManager().start(request, redis_s).end();
		ResponseModel response=new ResponseModel();
		
		project.setTeam_id(user.getTeam_id());
		response.setResponse(project_service.addProject(project));
		response.setMessage("新建成功");
		
		return  response;
	
	}
	/**
	 * 获得用户项目
	 */
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public ResponseModel getRunningProject() {
		System.out.println("ProjectController-getRunningProject");
		ResponseModel response=new ResponseModel();
		
		response.setResponse(project_service.getRunningProject());
		
		return  response;
	
	}
	
	/**
	 * 删除项目
	 */
	@RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE)
	public ResponseModel deleteProject(@PathVariable String id) {
		System.out.println("ProjectController-deleteProject--"+id);
		ResponseModel response=new ResponseModel();
		
		response.setResponse(project_service.deleteProject(Integer.parseInt(id)));
		
		return  response;
	
	}
	
	
}
