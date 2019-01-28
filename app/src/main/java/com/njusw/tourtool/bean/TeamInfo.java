package com.njusw.tourtool.bean;

public class TeamInfo {
	private Integer id;
	private String leaderUsername;
	private Integer leaderId;
	private String memberUsername;
	private Integer memberId;
	private Integer activityId;
	private Integer process;
	private Integer flag;
	
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLeaderUsername() {
		return leaderUsername;
	}
	public void setLeaderUsername(String leaderUsername) {
		this.leaderUsername = leaderUsername;
	}
	public Integer getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(Integer leaderId) {
		this.leaderId = leaderId;
	}
	public String getMemberUsername() {
		return memberUsername;
	}
	public void setMemberUsername(String memberUsername) {
		this.memberUsername = memberUsername;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	@Override
	public String toString() {
		return "TeamInfo [id=" + id + ", leaderUsername=" + leaderUsername + ", leaderId=" + leaderId
				+ ", memberUsername=" + memberUsername + ", memberId=" + memberId + ", activityId=" + activityId
				+ ", process=" + process + ", flag=" + flag + "]";
	}
	
}
