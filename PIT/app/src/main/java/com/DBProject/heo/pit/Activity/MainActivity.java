package com.DBProject.heo.pit.Activity;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.DBProject.heo.pit.InfoClass.ProjectBean;
import com.DBProject.heo.pit.InfoClass.ToDoBean;
import com.DBProject.heo.pit.Manager.Login_Main;
import com.DBProject.heo.pit.R;
import com.DBProject.heo.pit.TimeLine.CalStuff;
import com.DBProject.heo.pit.TimeLine.TLEvent;
import com.DBProject.heo.pit.TimeLine.TLView;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends Activity
{
    private static final String LogTag = "drgn";

    // TODO move timer thing to view itself
    Handler handler = new Handler();
    Runnable runnable = new Runnable()
    {
        public void run()
        {
            contentView.postInvalidate();
            handler.postDelayed(runnable, 250);
        }
    };

    private TLView contentView;
    private CalStuff calstuff;
    private ArrayList<TLEvent> ourEvents;
    private Vector<ToDoBean> ToDoList;
    private ProjectBean projectinfo;

    //private Spinner memo;

    private String Email;
    private String CateName;
    private String ProjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        Email = Login_Main.Email;
        CateName = Login_Main.Category;
        ProjectName = Login_Main.ProjectName;

        calstuff = new CalStuff(this);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_login);
        contentView = (TLView) findViewById(R.id.fullscreen_content);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);

        calstuff.LoadCalendars();
        Log.d(LogTag, "calendars loaded: " + calstuff.ourCalendars.size());

        calstuff.LoadEvents();
        Log.d(LogTag, "events loaded: " + calstuff.ourEvents.size());

        calstuff.LoadInstances();
        //Log.d(LogTag, "instances loaded: " + calstuff.ourInstances.size());

        contentView.SetCalStuff(calstuff);
/*
        Intent intent = getIntent();
        if(intent != null) {
            Email = intent.getStringExtra("Email");
            CateName = intent.getStringExtra("CateName");
            ProjectName = intent.getStringExtra("ProjectName");
        }
        Email = "admin";
        CateName = "android";
        ProjectName = "PIT";

        RequestParams params = new RequestParams();
        params.put("Email",Email);
        params.put("CateName", CateName);
        params.put("ProjectName",ProjectName);

        ProjectManager.getInstnace().Proitem_sendData(MyApplication.getContext(), params, new ProjectManager.OnResultListener<ProjectBean>() {
            @Override
            public void onSuccess(ProjectBean result) {
                projectinfo = result;
            }

            @Override
            public void onFail(int code) {
            }
        });

        ToDoList = new Vector<ToDoBean>();
        ToDoManager.getInstnace().ToDolist_sendData(MyApplication.getContext(), params, new ToDoManager.OnResultListener<Vector>() {
            @Override
            public void onSuccess(Vector result) {
                Log.e("TAG resultsize", " " + result.size());
                for(int i=0;i<result.size();i++){
                    ToDoList = result;//.add(result.elementAt(i));
                }
            }

            @Override
            public void onFail(int code) {

            }
        });
        //통신의 결과로 받아온 값들을 Projectinfo과 ToDoList에 저장....되어있다고 가정

        Log.e("TAG", " " + ToDoList.size());
        for(int i=0;i<ToDoList.size();i++){
            Log.e("TAG", " zz " + ToDoList.elementAt(i).getToDoName());
        }
        loadList();
        contentView.SetOurEvents(ourEvents);*/
    }

    private void loadList(){
        //DB에서 받아온 ToDoBean를 이용하여 ourEvents에 원소 삽입.
        ourEvents = new ArrayList<TLEvent>();

        for(int i=0;i<ToDoList.size(); i++){
            ToDoBean bean = ToDoList.elementAt(i);
            TLEvent e = new TLEvent(bean.getToDoName(), bean.getToDoStartDate(), bean.getToDoEndDate());
            ourEvents.add(e);
            Log.e("TAG", "e.dtstart = "+e.dtstart + " e.dtend = " + e.dtend);
        }
    }

    @Override
    protected void onPause()
    {
        Log.d(LogTag, "pause");
        super.onPause();

        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume()
    {
        Log.d(LogTag, "resume");
        super.onResume();
        contentView.requestFocus();
        runnable.run();
    }
}

