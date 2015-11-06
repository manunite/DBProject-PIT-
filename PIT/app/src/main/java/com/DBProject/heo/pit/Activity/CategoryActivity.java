package com.DBProject.heo.pit.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.DBProject.heo.pit.InfoClass.AddressBean;
import com.DBProject.heo.pit.InfoClass.CategoryBean;
import com.DBProject.heo.pit.InfoClass.ProjectBean;
import com.DBProject.heo.pit.InfoClass.UserBean;
import com.DBProject.heo.pit.Manager.ListItem;
import com.DBProject.heo.pit.Manager.getMySQL_Category;
import com.DBProject.heo.pit.Manager.getMySQL_Project_ToDo;
import com.DBProject.heo.pit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Heo on 15. 10. 11..
 */

public class CategoryActivity extends Activity implements View.OnClickListener{

    private ArrayList<String> mGroupList = null;
    private ArrayList<ArrayList<String>> mChildList = null;

    private ExpandableListView mListView;
    static public ArrayList<ListItem> listitems = new ArrayList<ListItem>();
    static public ArrayList<ListItem> listitems_proj = new ArrayList<ListItem>();

    private ExpandableListView catePick;
    private ExpandableListView projPick;
    //static String Address = "http://192.168.0.37:8080/DBProject";
    //static String Address = "http://117.16.198.171:8080/DBProject";
    //static String Address = "http://192.168.0.51:8080/DBProject";


    private BaseExpandableAdapter mBaseExpandableAdapter = null;


    private Button makesCateBtn;
    private Button deleteCateBtn;
    private Button modifyCateBtn;
    getMySQL_Category ms = new getMySQL_Category();
    getMySQL_Project_ToDo ps = new getMySQL_Project_ToDo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // register receiver
        this.registerReceiver(mReceiver_1, new IntentFilter("Cate_action"));
        ms.setContext(this);



        //this.unregisterReceiver(mReceiver);
        setLayout();
        makesCateBtn = (Button)findViewById(R.id.makeCate);
        deleteCateBtn = (Button)findViewById(R.id.deleteCate);
        modifyCateBtn = (Button)findViewById(R.id.modifyCate);
        mGroupList = new ArrayList<String>();
        mChildList = new ArrayList<ArrayList<String>>();
        //mChildListContent = new ArrayList<String>();
        //mChildListContent = new ArrayList<String>();
        mListView = (ExpandableListView)findViewById(R.id.elv);
        makesCateBtn.setOnClickListener(this);
        deleteCateBtn.setOnClickListener(this);
        modifyCateBtn.setOnClickListener(this);

        /////////////////////////WIFI-설정!!
        //학교->WM
        //String URL = "http://192.168.0.72/select_CateName.php";
        //String URL = "http://192.168.0.37/select_CateName.php";
        //String URL = "http://192.168.0.50:8080/DBProject/category_list?Email=" + UserBean.Email;
        String URL = AddressBean.Address + "/category_list?Email=" + UserBean.Email;
        //학교->INU
        //String URL = "http://117.16.198.249/select_CateName.php";
        //String URL = "http://117.16.198.249:8080/DBProject/category_list?Email="+ UserBean.Email;
        ////////////////////////////////////

        ms.execute(URL);
        //MatrixTime(4000);
        Log.i("HEO","AJAJAJAJ");
    }

    public BroadcastReceiver mReceiver_1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("Cate_action"))
            {
                Log.i("HEOKYU","success Receiver mReceiver_1!");
                listitems = ms.returnVal;
                UserBean.UserId = ms.re_UserId;
                Log.i("HEOKYU", (Integer.toString(UserBean.UserId)));
//                flag = true;//
//                  if(flag == true)
                setCateList();
            }
        }
    };
    ///////////////////////////////////////// 카테고리 별로 프로젝트들이 나열된것 완성!!
    public void setCateList()
    {
        int sizes = listitems.size();

        String URL = "";
        for(int i=0;i<sizes;i++)
        {

            String result = "";
            getMySQL_Project_ToDo psa = new getMySQL_Project_ToDo();
            mGroupList.add(listitems.get(i).getData(0));
            //URL = "http://192.168.0.50:8080/DBProject/project_list?Email=" + UserBean.Email + "&CateName=" + listitems.get(i).getData(0);
            URL = AddressBean.Address + "/project_list?Email=" + UserBean.Email + "&CateName=" + listitems.get(i).getData(0);
            Log.i("HEOKYU", URL);
            try {
                result = psa.execute(URL).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


            ArrayList<String> mChildListContent = null;
            mChildListContent = new ArrayList<String>();

            if(result.length() >= 53) {
                setExecute(result);
                int sizes_2 = listitems_proj.size();
                Log.i("HEOSIZE", Integer.toString(sizes_2));

                for (int j = 0; j < sizes_2; j++) {
                    mChildListContent.add(listitems_proj.get(j).getData(0));
                    Log.i("HEOSIZE", listitems_proj.get(j).getData(0));
                }
                mChildList.add(mChildListContent);
                listitems_proj.clear();
            }
            else {
                mChildList.add(mChildListContent);
                listitems_proj.clear();
            }
        }

        mListView.setAdapter(new BaseExpandableAdapter(this, mGroupList, mChildList));

        ///////////////////////////////////////// 카테고리 별로 프로젝트들이 나열된것 완성!!

        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //Toast.makeText(getApplicationContext(), "g click = " + groupPosition, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            //차일드 목록을 클릭했을때
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(),mGroupList.get(groupPosition) + "," + mChildList.get(groupPosition).get(childPosition),Toast.LENGTH_SHORT).show();
                CategoryBean.CateName = mGroupList.get(groupPosition);
                ProjectBean.ProjectName = mChildList.get(groupPosition).get(childPosition);
                //현재 선택된 카테고리와 프로젝트 네임을 추출
                Intent intent = new Intent(CategoryActivity.this,ProjectTimeline.class);
                //startActivityForResult(intent,0);
                startActivity(intent);
                return false;

            }
        });

        mListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                //Toast.makeText(getApplicationContext(), "g Collapse = " + groupPosition, Toast.LENGTH_SHORT).show();
            }
        });

        mListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //Toast.makeText(getApplicationContext(), "g Expand = " + groupPosition, Toast.LENGTH_SHORT).show();
            }
        });
    }
//////////////////////////////////
    protected void setExecute(String str){
        String ProjectName;
        try{
            JSONObject root = new JSONObject(str);
            JSONArray ja = root.getJSONArray("results"); //get the JSONArray which I made in the php file. the name of JSONArray is "results"

            for(int i=0;i<ja.length();i++){
                JSONObject jo = ja.getJSONObject(i);
                ProjectName = jo.getString("ProjectName");
                listitems_proj.add(new ListItem(ProjectName));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
//////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        return super.onPrepareOptionsMenu(menu);
    }

    //@Override
    public void onClick(View v)
    {
        Intent intent;
        switch(v.getId())
        {
            case R.id.makeCate :
                intent = new Intent(CategoryActivity.this, CateWizard.class);
                //startActivityForResult(intent, 0);
                startActivity(intent);
                break;
            case R.id.deleteCate :
                intent = new Intent(CategoryActivity.this, CateWizard_Delete.class);
                //startActivityForResult(intent, 0);
                startActivity(intent);
                break;
            case R.id.modifyCate :
                intent = new Intent(CategoryActivity.this, CateWizard_Modify_List.class);
                startActivity(intent);
                break;
        }
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    private void setLayout(){
        mListView = (ExpandableListView)findViewById(R.id.elv);
    }

    public void MatrixTime(int delayTime){
        long saveTime = System.currentTimeMillis();
        long currTime = 0;
        while( currTime - saveTime < delayTime){
            currTime = System.currentTimeMillis();
        }
    }
}
