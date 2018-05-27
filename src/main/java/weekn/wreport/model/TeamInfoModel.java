package weekn.wreport.model;

import java.util.List;

public class TeamInfoModel {
	private int team_id;
	private String team_name;
	private List<SysUserModel> members;
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public List<SysUserModel> getMembers() {
		return members;
	}
	public void setMembers(List<SysUserModel> members) {
		this.members = members;
	}
	
	
}
