package weekn.wreport.model;

import java.util.List;

public class ReportModel {
	private Integer id;
	private Integer project_id;
	private Integer user_id;
	private Integer general;//'是否是归纳后的report，为1为是'
	private Long time;
	private String outcome;
	private String problem;
	private String plan;
	private float rate;    //'项目进度'
	private List<ReportModel> sub;
	
	public List<ReportModel> getSub() {
		return sub;
	}
	public void setSub(List<ReportModel> sub) {
		this.sub = sub;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProject_id() {
		return project_id;
	}
	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getGeneral() {
		return general;
	}
	public void setGeneral(Integer general) {
		this.general = general;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	
}
