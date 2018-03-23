package weekn.wreport.service.imp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weekn.wreport.mapper.ProjectMapper;
import weekn.wreport.mapper.ReportMapper;
import weekn.wreport.model.ProjectModel;
import weekn.wreport.model.ReportModel;
import weekn.wreport.model.SimpleReportModel;
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
	public List<SimpleReportModel> getReportWithUser(String username) {
		Integer userid=1;
		Long time=(long) 100000;
		List<ProjectModel> report_list=report_mapper.getReportWithUser(userid,time);
		
		
		return buildSimpleReport(report_list);
	}

	@Override
	public List<ProjectModel> getReportWithTeam() {
		Integer userid=1;
		Long time=(long) 100000;
		List<ProjectModel> report_list=report_mapper.getReportWithUser(userid,time);
		clearProject(report_list);
		
		return report_list;
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
	private SimpleReportModel getSimpleReportFromReport(ReportModel report) {
		SimpleReportModel sm=new SimpleReportModel();
		sm.setId(report.getId());
		return null;
	}
	private List<SimpleReportModel> buildSimpleReport(List<ProjectModel> p) {
		//把有项目上下级结构关系的日志，变为直接有项目完整路径的单挑日志
		Iterator<ProjectModel> iter0 = p.iterator();
		List<SimpleReportModel> simpleReport_list=new LinkedList<SimpleReportModel>();
		while(iter0.hasNext()){ 
			ProjectModel pm0=iter0.next();
			if(pm0.getReport()!=null&&pm0.getReport().size()>0) {
				SimpleReportModel sm=new SimpleReportModel();
				sm.setId(pm0.getReport().get(0).getId());
				sm.setProject_level(0);
				sm.setProject_id_0(pm0.getId());
				sm.setProject_name_0(pm0.getName());
				sm.setPlan(pm0.getReport().get(0).getPlan());
				sm.setProblem(pm0.getReport().get(0).getProblem());
				sm.setOutcome(pm0.getReport().get(0).getOutcome());
				simpleReport_list.add(sm);
			}else {
				Iterator<ProjectModel> iter1 = pm0.getSub().iterator();
				while(iter1.hasNext()){ 
					ProjectModel pm1=iter1.next();
					if(pm1.getReport()!=null&&pm1.getReport().size()>0) {
						SimpleReportModel sm=new SimpleReportModel();
						sm.setId(pm1.getReport().get(0).getId());
						sm.setProject_level(1);
						sm.setProject_id_0(pm0.getId());
						sm.setProject_name_0(pm0.getName());
						sm.setProject_id_1(pm1.getId());
						sm.setProject_name_1(pm1.getName());
						sm.setPlan(pm1.getReport().get(0).getPlan());
						sm.setProblem(pm1.getReport().get(0).getProblem());
						sm.setOutcome(pm1.getReport().get(0).getOutcome());
						simpleReport_list.add(sm);
					}else {
						Iterator<ProjectModel> iter2 = pm1.getSub().iterator();
						while(iter2.hasNext()){ 
							ProjectModel pm2=iter2.next();
							if(pm2.getReport()!=null&&pm2.getReport().size()>0) {
								SimpleReportModel sm=new SimpleReportModel();
								sm.setId(pm2.getReport().get(0).getId());
								sm.setProject_level(2);
								sm.setProject_id_0(pm0.getId());
								sm.setProject_name_0(pm0.getName());
								sm.setProject_id_1(pm1.getId());
								sm.setProject_name_1(pm1.getName());
								sm.setProject_id_2(pm2.getId());
								sm.setProject_name_2(pm2.getName());
								sm.setPlan(pm2.getReport().get(0).getPlan());
								sm.setProblem(pm2.getReport().get(0).getProblem());
								sm.setOutcome(pm2.getReport().get(0).getOutcome());
								simpleReport_list.add(sm);
							}
						}
					}
				}
			}
		}
		return simpleReport_list;
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
