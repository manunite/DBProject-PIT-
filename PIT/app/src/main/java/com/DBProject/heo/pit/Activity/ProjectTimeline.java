package com.DBProject.heo.pit.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.DBProject.heo.pit.R;
import com.DBProject.heo.pit.TimeLine.CalStuff;
import com.DBProject.heo.pit.TimeLine.TLView;


public class ProjectTimeline extends Activity implements View.OnClickListener
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
    private Button makesToDoBtn;
    private Button deleteToDoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        calstuff = new CalStuff(this);

        setContentView(R.layout.activity_timeline);
        makesToDoBtn = (Button)findViewById(R.id.ToDoCreatebutton);
        deleteToDoBtn = (Button)findViewById(R.id.ToDoDeletebutton);
        Log.i("CLICK", "EEEE");
        contentView = (TLView) findViewById(R.id.fullscreen_content);
        makesToDoBtn.setOnClickListener(this);
        deleteToDoBtn.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        Intent intent;
        Log.i("CLICK","SSS");
        switch(v.getId())
        {
            case R.id.ToDoCreatebutton :
                intent = new Intent(ProjectTimeline.this, ToDoWizard.class);
                startActivity(intent);
                break;
            case R.id.ToDoDeletebutton :
                intent = new Intent(ProjectTimeline.this, ToDoWizard_Delete.class);
                intent = new Intent(ProjectTimeline.this, ToDoWizard_Delete.class);
                startActivity(intent);
                break;

        }
        finish();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);

        //calstuff.ourCalendars.clear();


        calstuff.LoadCalendars();//<-캘린더를 읽어오는 부분인듯

        Log.i("LogTag", "calendars loaded: " + calstuff.ourCalendars.size());




        calstuff.LoadEvents();
        Log.i("LogTag", "events loaded: " + calstuff.ourEvents.size());

		/*calstuff.LoadInstances();
		Log.d(LogTag, "instances loaded: " + calstuff.ourInstances.size());*/


        contentView.SetCalStuff(calstuff); //->얘가 리스트들을 띄움
        Log.i("CLICK", "KKDKSKS");
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

        runnable.run();
    }


}
