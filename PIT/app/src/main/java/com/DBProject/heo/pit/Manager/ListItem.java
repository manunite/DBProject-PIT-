package com.DBProject.heo.pit.Manager;

/**
 * Created by Heo on 15. 10. 12..
 */
public class ListItem {

    private String[] mData;
    private int[] mmData;
    final int columnCnt = 1;

    public ListItem(String[] data){
        mData = data;
    }

    public ListItem(String id){
        mData = new String[columnCnt];
        mData[0] = id;
    }

    public ListItem(int id){
        mData = new String[columnCnt];
        mmData[0] = id;
    }


    public String[] getmData(){
        return mData;
    }

    public String getData(int index){return mData[index];}
    public void setData(String[] data){
        mData = data;
    }
}