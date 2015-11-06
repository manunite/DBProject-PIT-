package HKJ;

public class ToDoBean {
	private String ToDoName;
	private int state;
	private String ToDoStartDate;
	private String ToDoEndDate;
	
	public String getToDoName(){ return ToDoName;}
	public int getState(){return state;}
	public String getToDoStartDate(){ return ToDoStartDate;}
	public String getToDoEndDate(){ return ToDoEndDate;}
	
	public void setToDoName(String val){ ToDoName = val; }
	public void setState(int val){ state = val;}
	public void setToDoStartDate(String val){ ToDoStartDate = val; }
	public void setToDoEndDate(String val){ ToDoEndDate = val;}
}