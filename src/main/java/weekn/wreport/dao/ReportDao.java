package weekn.wreport.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import weekn.wreport.model.ProjectModel;
import weekn.wreport.model.ReportModel;

public interface ReportDao {
	public int addReport(ReportModel report);

	public List<ProjectModel> getReportWithUser(@Param("user_id") Integer userid, @Param("starttime") Long starttime,
			@Param("endtime") Long endtime);

	public List<ReportModel> getSummarizeReport(@Param("project_id") Integer project_id);

	public List<ProjectModel> getReportWithTeam(@Param("team_id") int team_id, @Param("starttime") Long starttime,
			@Param("endtime") Long endtime);

	public void updateReport(ReportModel report);

	public int deleteReport(@Param("report_id") Integer report_id);
}
