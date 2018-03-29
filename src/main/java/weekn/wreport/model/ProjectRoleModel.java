package weekn.wreport.model;

import java.util.List;

public class ProjectRoleModel {
	
	private int project_id;
	private String project_name;
	private List<PRoleModel> roles;
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public List<PRoleModel> getRoles() {
		return roles;
	}
	public void setRoles(List<PRoleModel> roles) {
		this.roles = roles;
	}
	
}


