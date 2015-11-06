package com.DBProject.heo.pit.InfoClass;

/**
 * Created by Heo on 15. 9. 21..
 */
public class CategoryBean {
    private int CateId;
    private int UserId;
    static public String CateName;
    private String CateUpdateTime;

    public int getCateId(){ return CateId;}
    public int getUserId(){ return UserId;}
    public String getCateName(){ return CateName;}
    public String getCateUpdateTime(){ return CateUpdateTime;}

    public void setCateId(int val){ CateId = val; }
    public void setUserId(int val){ UserId = val; }
    public void setCateName(String val){ CateName = val; }
    public void setCateUpdateTime(String val){ CateUpdateTime = val;}
}
