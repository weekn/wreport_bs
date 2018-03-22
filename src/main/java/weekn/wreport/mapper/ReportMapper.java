package weekn.wreport.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import weekn.wreport.model.ProjectModel;
import weekn.wreport.model.ReportModel;


public interface  ReportMapper {
	public void addReport(ReportModel report);
	public List<ProjectModel> getReportWithUser(@Param("userid")Integer userid,@Param("time")Long time);
	public void updateReport(ReportModel report);
}
