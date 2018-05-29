package weekn.wreport.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import weekn.wreport.dao.ProjectDao;
import weekn.wreport.dao.ReportDao;
import weekn.wreport.model.PRoleModel;
import weekn.wreport.model.ProjectModel;
import weekn.wreport.model.ProjectRoleModel;
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
	public void summarizeReport(ReportModel report,SysUserModel user) throws JsonProcessingException {
		report.setUser_id(user.getId());//添加周报只能添加自己的，所以从redis中取userid
		report.setUser_name(user.getUsername());
		report.setGeneral(1);
		report.setTime(TimeUtils.getCurrentTimeStamp());
		System.out.println(JsonUtils.encode(report));
		PRoleModel role=project_mapper.getProjectRoles(report.getProject_id(), user.getId());
		if(role==null) {
			System.out.println("没有查到觉得");
		}else if(role.getRole()==0) {
			System.out.println(" 有汇总权限");
			List<ReportModel> report_l=report_mapper.getSummarizeReport(report.getProject_id());
			if(report_l.size()>0) {
				report.setId(report_l.get(0).getId());
				report_mapper.updateReport(report);
			}else {
				report_mapper.addReport(report);
			}
		}
		//return report_mapper.addReport(report);
		
	}

	
	public List<SimpleReportModel> getReportWithUser(SysUserModel user,Long time) {
		Integer userid=user.getId();
		Map<String, Long> time_map=TimeUtils.getWeeknStartEnd(time);
		List<ProjectModel> report_list=report_mapper.getReportWithUser(userid,time_map.get("start"),time_map.get("end"));
		List<SimpleReportModel> res=buildSimpleReport(report_list);
		
		
		return res;
	}

	
	public List<ProjectModel> getReportWithTeam(SysUserModel user ,int team_id,Long time) throws JsonProcessingException {
		System.out.println("time---"+time);
		Map<String, Long> time_map=TimeUtils.getWeeknStartEnd(time);
		
		List<ProjectModel> report_list=report_mapper.getReportWithTeam(user.getTeam_id(),time_map.get("start"),time_map.get("end"));
		
		List<Integer> project_id_list=clearProject(report_list);
		
		if(project_id_list==null||project_id_list.size()==0) {
			
			return new ArrayList();
		}else {
			
			List<ProjectRoleModel> pr_list=project_mapper.getProjectRolesA2ProjectId(project_id_list);
			
			Map<Integer,ProjectRoleModel> pr_map=new HashMap<Integer, ProjectRoleModel>();
			for(ProjectRoleModel pr:pr_list) {
				int id=pr.getProject_id();
				if(pr.getRoles()!=null && pr.getRoles().size()>0) {
					pr_map.put(id, pr);
				}
				
			}
			getUniqueReport(report_list,pr_map);
			System.out.println("getReportWithTeam------------");
			System.out.println(JsonUtils.encode(report_list));
			return report_list;
		}
		
	}

	
	public int deleteReport(int report_id) {
		return report_mapper.deleteReport(report_id);
	}

	
	public void updateReport(ReportModel report) {
		report_mapper.updateReport(report);		
	}

	
	private ReportModel getSummarizeReport(List<ReportModel> reports,int project_id) {
		
		
		StringBuilder s_outcome=new StringBuilder("");
		StringBuilder s_problem=new StringBuilder("");
		StringBuilder s_plan=new StringBuilder("");
		
		Iterator<ReportModel> iter = reports.iterator();
		while(iter.hasNext()){ 
			ReportModel report=iter.next();
			if(report.getGeneral()==1) {
				report.setProject_id(project_id);
				ReportModel r_report=report.clone();
				iter.remove();//删除general为1的，只保留费总结性日志
				return r_report;
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
		s_report.setId(reports.get(0).getId());
		s_report.setProject_id(project_id);
		s_report.setOutcome(s_outcome.toString());
		s_report.setProblem(s_problem.toString());
		s_report.setPlan(s_plan.toString());
		return s_report;
		
	}
	private void getUniqueReport(List<ProjectModel> p,Map<Integer,ProjectRoleModel> pr_map) {
		//从reports中获得唯一的report，该方法必须在clearproject方法后
		//如果没有总结性的report，就把reports中的所有report拼接起来，并且general设为0
		//如果有总结性的report，就让改report为最终的report，并且general设为1
		//获得总结report通过私有方法summarizeReport
		//通过参数pr_map，给project设定用户在项目中的角色
		//如果子项没有角色信息，将父项的角色信息付给子项
		
		Iterator<ProjectModel> iter0 = p.iterator();
		while(iter0.hasNext()){ 
			ProjectModel pm0=iter0.next();
			
			if(pm0.getReports()!=null&&pm0.getReports().size()>0) {
				pm0.setReport(getSummarizeReport(pm0.getReports(),pm0.getId()));
				if(pr_map.containsKey(pm0.getId())) {//设定项目的角色
					
					pm0.setRoles(pr_map.get(pm0.getId()).getRoles());
				}
				
			}
			if(pm0.getSub().size()>0){
				Iterator<ProjectModel> iter1 = pm0.getSub().iterator();
				while(iter1.hasNext()){ 
					
					ProjectModel pm1=iter1.next();
					
					if(pm1.getReports()!=null&&pm1.getReports().size()>0) {
						pm1.setReport(getSummarizeReport(pm1.getReports(),pm1.getId()));
						if(pr_map.containsKey(pm1.getId())) {
							
							pm1.setRoles(pr_map.get(pm1.getId()).getRoles());
						}else if(pr_map.containsKey(pm0.getId())){
							pm1.setRoles(pr_map.get(pm0.getId()).getRoles());//将父项的角色信息付给子项
						}
					}
					if(pm1.getSub().size()>0){
						Iterator<ProjectModel> iter2 = pm1.getSub().iterator();
						while(iter2.hasNext()){ 
							ProjectModel pm2=iter2.next();
							
							if(pm2.getReports()!=null&&pm2.getReports().size()>0) {
								pm2.setReport(getSummarizeReport(pm2.getReports(),pm2.getId()));
								if(pr_map.containsKey(pm2.getId())) {
									ProjectRoleModel proles2=pr_map.get(pm2.getId());
									pm2.setRoles(proles2.getRoles());
								}else if(pr_map.containsKey(pm1.getId())){
									pm2.setRoles(pr_map.get(pm1.getId()).getRoles());//将父项1的角色信息付给子项
								}else if(pr_map.containsKey(pm0.getId())){
									pm2.setRoles(pr_map.get(pm0.getId()).getRoles());//将父项1的角色信息付给子项
								}
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
	
	private List<Integer> clearProject(List<ProjectModel> p) {
		//清除自己不含日志，且自己的子项也不含日志的项目
		//返回有report的project 的 id
		Iterator<ProjectModel> iter0 = p.iterator();  
		List<Integer> project_id_list=new LinkedList<Integer>();
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
						project_id_list.add(pm2.getId());
					}
					
				}
				if(ifclear1&&(pm1.getReports()==null||pm1.getReports().size()<1)) {
					iter1.remove();
				}else {
					ifclear0=false;
					project_id_list.add(pm1.getId());
				}
				
			}
			if(ifclear0&&(pm0.getReports()==null||pm0.getReports().size()<1)) {
				iter0.remove();
			}else {
				project_id_list.add(pm0.getId());
			}
			
		}
		return project_id_list;
	}
	

	

}
