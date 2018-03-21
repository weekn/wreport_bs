package weekn.wreport.service.imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weekn.wreport.mapper.ProjectMapper;
import weekn.wreport.mapper.ReportMapper;
import weekn.wreport.model.ProjectModel;
import weekn.wreport.model.ReportModel;
import weekn.wreport.service.ReportService;
import weekn.wreport.tools.TimeUtils;




@Service
public class ProjectServiceimpl {

	
	@Autowired
	private ProjectMapper  project_mapper;
	
	public List<ProjectModel> getRunningProject() {
		return project_mapper.getRunningProject();
		
	}
	
	public List<ProjectModel> addProject(ProjectModel pro) {
		project_mapper.addProject(pro);
		return project_mapper.getRunningProject();
	}
	
	public List<ProjectModel> deleteProject(int id) {
		project_mapper.deleteProject(id);
		return project_mapper.getRunningProject();
	}

	

	
	

	

}
