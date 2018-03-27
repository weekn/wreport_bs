package weekn.wreport.service;

import java.util.List;

import weekn.wreport.model.ProjectModel;
import weekn.wreport.model.ReportModel;
import weekn.wreport.model.SimpleReportModel;
import weekn.wreport.model.SysUserModel;

public interface ReportService {
	public int addReport(ReportModel report);
	public List<SimpleReportModel> getReportWithUser(SysUserModel user);
	public List<ProjectModel> getReportWithTeam();
	public int deleteReport(int report_id);
	public void updateReport(ReportModel report);
	public void summarizeReport(ReportModel report);
}
