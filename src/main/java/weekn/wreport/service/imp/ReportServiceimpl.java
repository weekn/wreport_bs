package weekn.wreport.service.imp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import weekn.wreport.dao.ProjectDao;
import weekn.wreport.dao.ReportDao;
import weekn.wreport.model.ProjectModel;
import weekn.wreport.model.ReportModel;
import weekn.wreport.model.SimpleReportModel;
import weekn.wreport.model.SysUserModel;
import weekn.wreport.service.ReportService;
import weekn.wreport.tools.JsonUtils;
import weekn.wreport.tools.TimeUtils;




@Service
public class ReportServiceimpl {

	@Autowired
	private ReportDao report_mapper;
	@Autowired
	private ProjectDao  project_mapper;
	
	public int addReport(ReportModel report) {
		
		report.setTime(TimeUtils.getCurrentTimeStamp());
		return report_mapper.addReport(report);
		
	}

	
	public List<SimpleReportModel> getReportWithUser(SysUserModel user) {
		Integer userid=user.getId();
		Long time=(long) 100000;
		List<ProjectModel> report_list=report_mapper.getReportWithUser(userid,time);
		List<SimpleReportModel> res=buildSimpleReport(report_list);
		
		
		return res;
	}

	
	public List<ProjectModel> getReportWithTeam() {
		
		List<ProjectModel> report_list=report_mapper.getReportWithTeam();
		clearProject(report_list);
		getUniqueReport(report_list);
		return report_list;
	}

	
	public int deleteReport(int report_id) {
		return report_mapper.deleteReport(report_id);
	}

	
	public void updateReport(ReportModel report) {
		report_mapper.updateReport(report);		
	}

	
	private ReportModel summarizeReport(List<ReportModel> reports) {
		int size=reports.size();
		
		
		StringBuilder s_outcome=new StringBuilder("");
		StringBuilder s_problem=new StringBuilder("");
		StringBuilder s_plan=new StringBuilder("");
		for(ReportModel report: reports) {
			if(report.getGeneral()==1) {
				return report;
			}else {
				String user_name=report.getUser_name();
				String outcome=report.getOutcome();
				String problem=report.getProblem();
				String plan=report.getPlan();
				s_outcome.append("<p>"+user_name+":<br></p>");
				s_outcome.append(outcome);
				s_problem.append("<p>"+user_name+":<br></p>");
				s_problem.append(problem);
				s_plan.append("<p>"+user_name+":<br></p>");
				s_plan.append(plan);
				
			}
		}
		ReportModel s_report=new ReportModel();
		s_report.setOutcome(s_outcome.toString());
		s_report.setProblem(s_problem.toString());
		s_report.setPlan(s_plan.toString());
		return s_report;
		
	}
	private void getUniqueReport(List<ProjectModel> p) {
		//从reports中获得唯一的report，该方法必须在clearproject方法后
		//如果没有总结性的report，就把reports中的所有report拼接起来，并且general设为0
		//如果有总结性的report，就让改report为最终的report，并且general设为1
		//获得总结report通过私有方法summarizeReport
		Iterator<ProjectModel> iter0 = p.iterator();
		while(iter0.hasNext()){ 
			ProjectModel pm0=iter0.next();
			
			if(pm0.getReports()!=null&&pm0.getReports().size()>0) {
				pm0.setReport(summarizeReport(pm0.getReports()));
			}
			if(pm0.getSub().size()>0){
				Iterator<ProjectModel> iter1 = pm0.getSub().iterator();
				while(iter1.hasNext()){ 
					
					ProjectModel pm1=iter1.next();
					
					if(pm1.getReports()!=null&&pm1.getReports().size()>0) {
						pm1.setReport(summarizeReport(pm1.getReports()));
					}
					if(pm1.getSub().size()>0){
						Iterator<ProjectModel> iter2 = pm1.getSub().iterator();
						while(iter2.hasNext()){ 
							ProjectModel pm2=iter2.next();
							
							if(pm2.getReports()!=null&&pm2.getReports().size()>0) {
								pm2.setReport(summarizeReport(pm2.getReports()));
							}
						}
					}
				}
			}
		}
	}
	private List<SimpleReportModel> buildSimpleReport(List<ProjectModel> p) {
		//把有项目上下级结构关系的日志，变为直接有项目完整路径的单挑日志
		Iterator<ProjectModel> iter0 = p.iterator();
		List<SimpleReportModel> simpleReport_list=new LinkedList<SimpleReportModel>();
		while(iter0.hasNext()){ 
			ProjectModel pm0=iter0.next();
			
			if(pm0.getReports()!=null&&pm0.getReports().size()>0) {
				SimpleReportModel sm=new SimpleReportModel();
				sm.setId(pm0.getReports().get(0).getId());
				sm.setProject_level(0);
				sm.setProject_id_0(pm0.getId());
				sm.setProject_name_0(pm0.getName());
				sm.setPlan(pm0.getReports().get(0).getPlan());
				sm.setProblem(pm0.getReports().get(0).getProblem());
				sm.setOutcome(pm0.getReports().get(0).getOutcome());
				sm.setProject_level(0);
				simpleReport_list.add(sm);
			}
			if(pm0.getSub().size()>0){
				Iterator<ProjectModel> iter1 = pm0.getSub().iterator();
				while(iter1.hasNext()){ 
					
					ProjectModel pm1=iter1.next();
					
					if(pm1.getReports()!=null&&pm1.getReports().size()>0) {
						SimpleReportModel sm=new SimpleReportModel();
						sm.setId(pm1.getReports().get(0).getId());
						sm.setProject_level(1);
						sm.setProject_id_0(pm0.getId());
						sm.setProject_name_0(pm0.getName());
						sm.setProject_id_1(pm1.getId());
						sm.setProject_name_1(pm1.getName());
						sm.setPlan(pm1.getReports().get(0).getPlan());
						sm.setProblem(pm1.getReports().get(0).getProblem());
						sm.setOutcome(pm1.getReports().get(0).getOutcome());
						sm.setProject_level(1);
						simpleReport_list.add(sm);
					}
					if(pm1.getSub().size()>0){
						Iterator<ProjectModel> iter2 = pm1.getSub().iterator();
						while(iter2.hasNext()){ 
							ProjectModel pm2=iter2.next();
							
							if(pm2.getReports()!=null&&pm2.getReports().size()>0) {
								SimpleReportModel sm=new SimpleReportModel();
								sm.setId(pm2.getReports().get(0).getId());
								sm.setProject_level(2);
								sm.setProject_id_0(pm0.getId());
								sm.setProject_name_0(pm0.getName());
								sm.setProject_id_1(pm1.getId());
								sm.setProject_name_1(pm1.getName());
								sm.setProject_id_2(pm2.getId());
								sm.setProject_name_2(pm2.getName());
								sm.setPlan(pm2.getReports().get(0).getPlan());
								sm.setProblem(pm2.getReports().get(0).getProblem());
								sm.setOutcome(pm2.getReports().get(0).getOutcome());
								sm.setProject_level(2);
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
					
					if(pm2.getReports()==null||pm2.getReports().size()<1) {
						iter2.remove();
					}else {
						ifclear1=false;
						
					}
					
				}
				if(ifclear1&&(pm1.getReports()==null||pm1.getReports().size()<1)) {
					iter1.remove();
				}else {
					ifclear0=false;
				}
				
			}
			if(ifclear0&&(pm0.getReports()==null||pm0.getReports().size()<1)) {
				iter0.remove();
			}
			
		}
	}
	

	

}
