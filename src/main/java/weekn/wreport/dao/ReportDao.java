package weekn.wreport.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import weekn.wreport.model.ProjectModel;
import weekn.wreport.model.ReportModel;


public interface  ReportDao {
	public int addReport(ReportModel report);
	public List<ProjectModel> getReportWithUser(@Param("user_id")Integer userid,@Param("time")Long time);
	public List<ProjectModel> getReportWithTeam();
	public void updateReport(ReportModel report);
	public int deleteReport(@Param("report_id")Integer report_id);
}
