package org.drgn.timeline.Manager;

/**
 * Created by Heo on 15. 10. 15..
 */
public class ListItem {

    private String[] mData;
    private int[] mmData;
    final int columnCnt = 3;

    public ListItem(String[] data){
        mData = data;
    }

    public ListItem(String ProName,String ProStart,String ProEnd){
        mData = new String[columnCnt];
        mData[0] = ProName;
        mData[1] = ProStart;
        mData[2] = ProEnd;
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
