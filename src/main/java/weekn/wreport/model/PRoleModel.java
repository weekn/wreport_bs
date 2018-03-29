package weekn.wreport.model;

public class PRoleModel {
	private int id;
	private int project_id;
	private int user_id;
	private String user_name;
	private int role;
	private String role_name;
	
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
		switch (role) {
		case 0:
			this.role_name="A角";
			break;
		case 1:
			this.role_name="B角";
			break;
		case 2:
			this.role_name="参与者";
			break;
		default:
			break;
		}
	}
	
}