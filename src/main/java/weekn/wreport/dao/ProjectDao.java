package weekn.wreport.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import weekn.wreport.model.ProjectModel;


public interface  ProjectDao {
	
	public List<ProjectModel> getProject();
	
	public List<ProjectModel> getRunningProject();
	
	public void addProject(ProjectModel project);
	
	public void deleteProject(@Param("id") int id);
}
