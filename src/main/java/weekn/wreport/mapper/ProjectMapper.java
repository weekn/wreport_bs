package weekn.wreport.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import weekn.wreport.model.ProjectModel;


public interface  ProjectMapper {
	
	public List<ProjectModel> getProject();
	
	public List<ProjectModel> getRunningProject();
}
