package com.DBProject.heo.pit.InfoClass;

/**
 * Created by Heo on 15. 9. 21..
 */
public class ToDoBean {
    public static String ToDoName;
    private int State;
    private String ToDoStartDate;
    private String ToDoEndDate;

    public String getToDoName(){ return ToDoName;}
    public int getState(){return State;}
    public String getToDoStartDate(){ return ToDoStartDate;}
    public String getToDoEndDate(){ return ToDoEndDate;}

    public void setToDoName(String val){ ToDoName = val; }
    public void setState(int val){ State = val;}
    public void setToDoStartDate(String val){ ToDoStartDate = val; }
    public void setToDoEndDate(String val){ ToDoEndDate = val;}
}
