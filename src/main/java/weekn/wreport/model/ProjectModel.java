package weekn.wreport.model;

import java.util.List;

public class ProjectModel {
	private int id;
	private String name;
	private int up_id;
	private int level;
	private int team_id;
	private double plan_start_time;
	private double plan_end_time;
	private List<ReportModel> reports;
	private ReportModel report;
	private List<PRoleModel> roles;
	private List<ProjectModel> sub;
	
	
	public double getPlan_start_time() {
		return plan_start_time;
	}
	public void setPlan_start_time(double plan_start_time) {
		this.plan_start_time = plan_start_time;
	}
	public double getPlan_end_time() {
		return plan_end_time;
	}
	public void setPlan_end_time(double plan_end_time) {
		this.plan_end_time = plan_end_time;
	}
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
