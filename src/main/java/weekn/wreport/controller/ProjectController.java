package weekn.wreport.controller;

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
import weekn.wreport.service.imp.ProjectServiceimpl;

@RestController
public class ProjectController {
	@Autowired
	ProjectServiceimpl  project_service;
	
	/**
	 * 添加项目
	 */
	@RequestMapping(value = "/project", method = RequestMethod.POST)
	public ResponseModel addReport(@RequestBody ProjectModel project) {// POST /session # 创建新的会话（登录）
		System.out.println("ReportController-addReport");
		ResponseModel response=new ResponseModel();
		
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
