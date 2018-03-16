package weekn.wreport.service;

import java.util.List;

import weekn.wreport.model.ReportModel;

public interface ReportService {
	public void addReport(String username,ReportModel report);
	public List<ReportModel> getReportWithUser(String username);
	public List<ReportModel> getReportWithTeam();
	public void deleteReport(ReportModel report);
	public void updateReport(ReportModel report);
	public void summarizeReport(ReportModel report);
}
