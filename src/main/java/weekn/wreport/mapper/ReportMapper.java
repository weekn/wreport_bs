package weekn.wreport.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import weekn.wreport.model.ReportModel;


public interface  ReportMapper {
	public void addReport(ReportModel report);
	public List<ReportModel> getReportWithUser(@Param("userid")Integer userid,@Param("time")Long time);
}
