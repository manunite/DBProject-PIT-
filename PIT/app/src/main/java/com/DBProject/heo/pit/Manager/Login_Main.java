package com.DBProject.heo.pit.Manager;

import android.app.Application;
import android.content.Context;

/**
 * Created by Heo on 2015. 10. 10..
 */
public class Login_Main extends Application {

    public static String Email;
    public static String Category;
    public static String ProjectName;

    private static Context mContext;

    public static Context getContext() {
        //  return instance.getApplicationContext();
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //  instance = this;
        mContext = getApplicationContext();
    }
}
