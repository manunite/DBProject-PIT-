package com.DBProject.heo.pit.InfoClass;

import android.app.Application;

/**
 * Created by Heo on 15. 9. 21..
 */
public class UserBean extends Application{
    public static int UserId;
    public static String Email;
    public static String PassWord;


    public int getUserId(){ return UserId;}
    public String getEmail(){ return Email;}
    public String getPassWord(){ return PassWord;}

    public void setUserId(int val){ UserId = val; }
    public void setEmail(String val){ Email = val; }
    public void setPassWord(String val){ PassWord = val;}

}
