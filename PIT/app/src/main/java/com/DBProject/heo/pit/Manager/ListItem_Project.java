package com.DBProject.heo.pit.Manager;

/**
 * Created by Heo on 15. 10. 15..
 */
public class ListItem_Project {

    private String[] mData;
    private int[] mmData;
    final int columnCnt = 4;

    public ListItem_Project(String[] data){
        mData = data;
    }

    public ListItem_Project(String ProName,String ProStart,String ProEnd,String ProBriefy){
        mData = new String[columnCnt];
        mData[0] = ProName;
        mData[1] = ProStart;
        mData[2] = ProEnd;
        mData[3] = ProBriefy;
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
