package weekn.wreport.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import weekn.wreport.model.PRoleModel;
import weekn.wreport.model.ProjectModel;
import weekn.wreport.model.ProjectRoleModel;


public interface  ProjectDao {
	

	
	public List<ProjectModel> getRunningProject();
	
	public void addProject(ProjectModel project);
	public void updateProject(ProjectModel project);
	
	public void deleteProject(@Param("id") int id);
	
	public List<ProjectRoleModel> getProjectRolesA2ProjectId(List<Integer> project_id);
	public List<ProjectRoleModel> getProjectRolesA2TeamId(@Param("team_id")int team_id);
	
	public PRoleModel getProjectRoles(@Param("project_id")int project_id,@Param("user_id")int user_id);
	public void addProjectRoles(PRoleModel role);
	public void deleteProjectRolesA2ProId(@Param("project_id")int project_id);
}
