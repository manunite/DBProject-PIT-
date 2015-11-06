package HKJ;

public class ProjectBean {
	private int ProjectId;
	private int CateId;
	private int UserId;
	private String ProjectName;
	private String ProjectUpdateTime;
	private String ProjectBriefy;
	//private int ProjectPercent;
	private String ProjectStartDate;
	private String ProjectEndDate;
	//private int startMoney;
	//private int endMoney;
	//private String startMoneyDate;
	//private String endMoneyDate;
	
	public int getProjectId(){ return ProjectId;}
	public int getCateId(){ return CateId;}
	public int getUserId(){ return UserId;}
	public String getProjectName(){ return ProjectName;}
	public String getProjectUpdateTime(){ return ProjectUpdateTime;}
	public String getProjectBriefy(){return ProjectBriefy;}
	//public int getProjectPercent(){return ProjectPercent;}
	public String getStartDate(){return ProjectStartDate;}
	public String getEndDate(){return ProjectEndDate;}
	//public int getstartMoney(){return startMoney;}
	//public int getendMoney(){return endMoney;}
	//public String getstartMoneyDate(){return startMoneyDate;}
	//public String getendMoneyDate(){return endMoneyDate;}
	
	public void setProjectId(int val){ ProjectId = val;}
	public void setCateId(int val){ CateId = val;}
	public void setUserId(int val){ UserId = val;}
	public void setProjectName(String val){ ProjectName = val;}
	public void setProjectUpdateTime(String val){ ProjectUpdateTime = val;}
	public void setProjectBriefy(String val){ProjectBriefy = val;}
	//public void setProjectPercent(int val){ProjectPercent = val;}
	public void setStartDate(String val){ProjectStartDate = val;}
	public void setEndDate(String val){ProjectEndDate = val;}
	//public void setstartMoney(int val){startMoney = val;}
	//public void setendMoney(int val){endMoney = val;}
	//public void setstartMoneyDate(String val){startMoneyDate = val;}
	//public void setendMoneyDate(String val){endMoneyDate = val;}
}
