package com.DBProject.heo.pit.Manager;

/**
 * Created by Heo on 15. 10. 15..
 */
public class ListItem_ToDoList {

    private String[] mData;
    private int[] mmData;
    final int columnCnt = 4;

    public ListItem_ToDoList(String[] data){
        mData = data;
    }

    public ListItem_ToDoList(String ToDoName,String ToDoStart,String ToDoEnd,String ToDoState){
        mData = new String[columnCnt];
        mData[0] = ToDoName;
        mData[1] = ToDoStart;
        mData[2] = ToDoEnd;
        mData[3] = ToDoState;
    }



    public String[] getmData(){
        return mData;
    }

    public String getData(int index){
        return mData[index];
    }
    public void setData(String[] data){
        mData = data;
    }
}
