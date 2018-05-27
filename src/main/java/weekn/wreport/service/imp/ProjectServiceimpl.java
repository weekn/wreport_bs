package weekn.wreport.service.imp;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import weekn.wreport.dao.ProjectDao;
import weekn.wreport.dao.ReportDao;
import weekn.wreport.model.PRoleModel;
import weekn.wreport.model.ProjectModel;
import weekn.wreport.model.ProjectRoleModel;
import weekn.wreport.model.ReportModel;
import weekn.wreport.service.ReportService;
import weekn.wreport.tools.JsonUtils;
import weekn.wreport.tools.TimeUtils;




@Service
public class ProjectServiceimpl {

	
	@Autowired
	private ProjectDao  project_mapper;
	
	public List<ProjectModel> getRunningProject(int team_id) {
		List<ProjectModel> pro_list= project_mapper.getRunningProject();
		
		List<ProjectRoleModel> pr_list=project_mapper.getProjectRolesA2TeamId(team_id);//团队所有pro以及pro的角色信息
		
		
		Map<Integer,ProjectRoleModel> pr_map=new HashMap<Integer, ProjectRoleModel>();//转成map
		for(ProjectRoleModel pr:pr_list) {
			int id=pr.getProject_id();
			if(pr.getRoles()!=null && pr.getRoles().size()>0) {
				pr_map.put(id, pr);
			}
			
		}

		for(ProjectModel p0:pro_list) {
			int id0=p0.getId();
			if(pr_map.containsKey(id0)) {
				p0.setRoles(pr_map.get(id0).getRoles());
			}
			
			for(ProjectModel p1:p0.getSub()) {
				int id1=p1.getId();
				if(pr_map.containsKey(id1)) {
					p1.setRoles(pr_map.get(id1).getRoles());
				}
				
				
				for(ProjectModel p2:p1.getSub()) {
					int id2=p2.getId();
					if(pr_map.containsKey(id2)) {
						p2.setRoles(pr_map.get(id2).getRoles());
					}
					
				}
			}
		}
		
		try {
			System.out.println(JsonUtils.encode(pro_list));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pro_list;
	}
	
	public List<ProjectModel> addProject(ProjectModel pro) {
		project_mapper.addProject(pro);
		for(PRoleModel role:pro.getRoles()) {
			role.setProject_id(pro.getId());
			project_mapper.addProjectRoles(role);
		}
		return project_mapper.getRunningProject();
	}
	
	public List<ProjectModel> deleteProject(int id) {
		project_mapper.deleteProject(id);
		return project_mapper.getRunningProject();
	}

	

	
	

	

}
