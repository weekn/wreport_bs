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
	public void addReport(ReportModel report) {
		report.setUser_id(1);
		report.setTime(TimeUtils.getCurrentTimeStamp());
		report_mapper.addReport(report);
		
	}

	@Override
	public List<ProjectModel> getReportWithUser(String username) {
		Integer userid=1;
		Long time=(long) 100000;
		List<ProjectModel> report_list=report_mapper.getReportWithUser(userid,time);
		clearProject(report_list);
		
		return report_list;
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
		report_mapper.updateReport(report);		
	}

	@Override
	public void summarizeReport(ReportModel report) {
		// TODO Auto-generated method stub
		
	}
	
	private void clearProject(List<ProjectModel> p) {
		//清除自己不含日志，且自己的子项也不含日志的项目
		Iterator<ProjectModel> iter0 = p.iterator();  
		while(iter0.hasNext()){  
			ProjectModel pm0=iter0.next();
			boolean ifclear0=true;
			Iterator<ProjectModel> iter1 = pm0.getSub().iterator();  
			while(iter1.hasNext()){  
				ProjectModel pm1=iter1.next();
				boolean ifclear1=true;
				Iterator<ProjectModel> iter2 = pm1.getSub().iterator();  
				while(iter2.hasNext()){  
					ProjectModel pm2=iter2.next();
					
					if(pm2.getReport()==null||pm2.getReport().size()<1) {
						iter2.remove();
					}else {
						ifclear1=false;
						
					}
					
				}
				if(ifclear1&&(pm1.getReport()==null||pm1.getReport().size()<1)) {
					iter1.remove();
				}else {
					ifclear0=false;
				}
				
			}
			if(ifclear0&&(pm0.getReport()==null||pm0.getReport().size()<1)) {
				iter0.remove();
			}
			
		}
	}
	

	

}
