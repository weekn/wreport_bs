package weekn.wreport.service;

import java.util.List;

import weekn.wreport.model.ProjectModel;
import weekn.wreport.model.ReportModel;

public interface ProjectService {
	public void addReport(String username,ReportModel report);
	public List<ProjectModel> getReportWithUser(String username);
	public List<ReportModel> getReportWithTeam();
	public void deleteReport(ReportModel report);
	public void updateReport(ReportModel report);
	public void summarizeReport(ReportModel report);
}
