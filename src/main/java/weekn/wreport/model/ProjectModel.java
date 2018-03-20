package weekn.wreport.model;

import java.util.List;

public class ProjectModel {
	private int id;
	private String name;
	private List<ReportModel> report;
	private List<ProjectModel> sub;
	
	public List<ReportModel> getReport() {
		return report;
	}
	public void setReport(List<ReportModel> report) {
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
