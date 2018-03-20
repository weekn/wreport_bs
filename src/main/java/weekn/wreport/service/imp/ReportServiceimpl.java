package weekn.wreport.service.imp;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weekn.wreport.mapper.ProjectMapper;
import weekn.wreport.mapper.ReportMapper;
import weekn.wreport.model.ProjectModel;
import weekn.wreport.model.ReportModel;
import weekn.wreport.service.ReportService;
import weekn.wreport.tools.TimeUtils;




@Service
public class ReportServiceimpl implements ReportService{

	@Autowired
	private ReportMapper report_mapper;
	@Autowired
	private ProjectMapper  project_mapper;
	@Override
	public void addReport(String username,ReportModel report) {
		report.setUser_id(1);
		report.setTime(TimeUtils.getCurrentTimeStamp());
		report_mapper.addReport(report);
		
	}

	@Override
	public List<ProjectModel> getReportWithUser(String username) {
		Integer userid=1;
		Long time=(long) 100000;
		List<ReportModel> report_list=report_mapper.getReportWithUser(userid,time);
		List<ProjectModel> project_list= project_mapper.getProject();
		ReportModel res_report=new ReportModel();
		for(Iterator<ReportModel> it=report_list.iterator();it.hasNext();){
			ReportModel  report=it.next();
			//report
		}
		return project_list;
	}

	@Override
	public List<ReportModel> getReportWithTeam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteReport(ReportModel report) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateReport(ReportModel report) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void summarizeReport(ReportModel report) {
		// TODO Auto-generated method stub
		
	}
	

	

}
