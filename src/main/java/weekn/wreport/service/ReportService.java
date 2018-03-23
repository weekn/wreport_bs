package weekn.wreport.service;

import java.util.List;

import weekn.wreport.model.ProjectModel;
import weekn.wreport.model.ReportModel;
import weekn.wreport.model.SimpleReportModel;

public interface ReportService {
	public void addReport(ReportModel report);
	public List<SimpleReportModel> getReportWithUser(String username);
	public List<ProjectModel> getReportWithTeam();
	public void deleteReport(ReportModel report);
	public void updateReport(ReportModel report);
	public void summarizeReport(ReportModel report);
}
