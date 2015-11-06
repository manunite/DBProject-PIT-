package com.DBProject.heo.pit.Manager;

/**
 * Created by Heo on 15. 10. 15..
 */
public class ListItem_User {

    private String[] mData;
    private int[] mmData;
    final int columnCnt = 4;

    public ListItem_User(String[] data){
        mData = data;
    }

    public ListItem_User(String Email,String UserName,String PassWord){
        mData = new String[columnCnt];
        mData[0] = Email;
        mData[1] = UserName;
        mData[2] = PassWord;
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
