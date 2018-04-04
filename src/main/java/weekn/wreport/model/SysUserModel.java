package weekn.wreport.model;

import java.util.List;

public class SysUserModel {
	private Integer id;
    private String username;
    private String account;
    private String password;
    private int team_id;
    private String token;
    private List<SysRoleModel> roles;
    
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<SysRoleModel> getRoles() {
		return roles;
	}
	public void setRoles(List<SysRoleModel> roles) {
		this.roles = roles;
	}

    
}
