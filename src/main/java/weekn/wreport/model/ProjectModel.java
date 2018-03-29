package weekn.wreport.model;

import java.util.List;

public class ProjectModel {
	private int id;
	private String name;
	private int up_id;
	private int level;
	private int team_id;
	private List<ReportModel> reports;
	private ReportModel report;
	private List<PRoleModel> roles;
	private List<ProjectModel> sub;
	
	
	public List<PRoleModel> getRoles() {
		return roles;
	}
	public void setRoles(List<PRoleModel> roles) {
		this.roles = roles;
	}
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public int getUp_id() {
		return up_id;
	}
	public void setUp_id(int up_id) {
		this.up_id = up_id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public List<ReportModel> getReports() {
		return reports;
	}
	public void setReports(List<ReportModel> reports) {
		this.reports = reports;
	}
	public ReportModel getReport() {
		return report;
	}
	public void setReport(ReportModel report) {
		this.report = report;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ProjectModel> getSub() {
		return sub;
	}
	public void setSub(List<ProjectModel> sub) {
		this.sub = sub;
	}
	
}
