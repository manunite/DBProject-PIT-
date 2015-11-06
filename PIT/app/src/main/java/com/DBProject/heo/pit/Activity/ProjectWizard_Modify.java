package com.DBProject.heo.pit.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.DBProject.heo.pit.InfoClass.AddressBean;
import com.DBProject.heo.pit.InfoClass.CategoryBean;
import com.DBProject.heo.pit.InfoClass.ProjectBean;
import com.DBProject.heo.pit.InfoClass.UserBean;
import com.DBProject.heo.pit.Manager.ListItem_Project;
import com.DBProject.heo.pit.Manager.Login_Main;
import com.DBProject.heo.pit.Manager.ProjectManager;
import com.DBProject.heo.pit.Manager.getMySQL_Project_ToDo;
import com.DBProject.heo.pit.R;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

/**
 * Created by Heo on 15. 10. 11..
 */

public class ProjectWizard_Modify extends Activity implements View.OnClickListener {
    private String inputName_P;
    private String inputStartDay_P_Y;
    private String inputStartDay_P_M;
    private String inputStartDay_P_D;
    private String inputEndDay_P_Y;
    private String inputEndDay_P_M;
    private String inputEndDay_P_D;
    private String inputBriefy;
    private Button modifybtn;
    private EditText ProjectName;
    //private EditText ToDoState;
    private RadioGroup rg1;
    private EditText ProjectBriefy;
    private EditText ProjectStartDay_Y;
    private EditText ProjectStartDay_M;
    private EditText ProjectStartDay_D;
    private EditText ProjectEndDay_Y;
    private EditText ProjectEndDay_M;
    private EditText ProjectEndDay_D;
    private Boolean flag = false;
    private Intent intent;
    private getMySQL_Project_ToDo db = new getMySQL_Project_ToDo();
    private ArrayList<ListItem_Project> listitem = new ArrayList<ListItem_Project>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("EEE", "SGSGSDSAAAA");
        setContentView(R.layout.activity_modifypro);
        modifybtn = (Button)findViewById(R.id.makeProject_M);
        ProjectName = (EditText) findViewById(R.id.ProjectName_M);
        //ToDoState = (EditText) findViewById(R.id.ToDoState);
        ProjectBriefy = (EditText) findViewById(R.id.ProjectBriefy_M);
        ProjectStartDay_Y = (EditText) findViewById(R.id.ProjectStart_Y_M);
        ProjectStartDay_M = (EditText) findViewById(R.id.ProjectStart_M_M);
        ProjectStartDay_D = (EditText) findViewById(R.id.ProjectStart_D_M);
        ProjectEndDay_Y = (EditText) findViewById(R.id.ProjectEnd_Y_M);
        ProjectEndDay_M = (EditText) findViewById(R.id.ProjectEnd_M_M);
        ProjectEndDay_D = (EditText) findViewById(R.id.ProjectEnd_D_M);
        ProjectStartDay_Y.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL
                | InputType.TYPE_NUMBER_FLAG_SIGNED);
        ProjectStartDay_M.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL
                | InputType.TYPE_NUMBER_FLAG_SIGNED);
        ProjectStartDay_D.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL
                | InputType.TYPE_NUMBER_FLAG_SIGNED);
        ProjectEndDay_Y.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL
                | InputType.TYPE_NUMBER_FLAG_SIGNED);
        ProjectEndDay_M.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL
                | InputType.TYPE_NUMBER_FLAG_SIGNED);
        ProjectEndDay_D.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL
                | InputType.TYPE_NUMBER_FLAG_SIGNED);
        modifybtn.setOnClickListener(this);

////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Log.i("EEE","EWWEQ");
        String S_D = new String();
        String E_D = new String();
        String result = new String();
        String Breify = new String();
        String URL = new String();
        //String Address = "http://192.168.0.37:8080/DBProject/project_item";
        //String Address = "http://192.168.0.51:8080/DBProject/todo_item";
        //Address = "http://117.16.198.171:8080/DBProject/todo_list";
        URL = AddressBean.Address + "/project_item" + "?Email="+ UserBean.Email + "&CateName=" + CategoryBean.CateName + "&ProjectName=" + ProjectBean.ProjectName;

        try {
            result = db.execute(URL).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        setExecute(result);
        Log.i("EEEE", result);
        Log.i("EEEE", "KKK" + listitem.get(0).getData(0));
        ProjectName.setText(listitem.get(0).getData(0));
        Log.i("EEEE", "KYU");
        S_D = listitem.get(0).getData(1);
        E_D = listitem.get(0).getData(2);
        Breify = listitem.get(0).getData(3);
        Log.i("EEEE",listitem.get(0).getData(0) + "," + listitem.get(0).getData(1) + "," +listitem.get(0).getData(2) + "," +listitem.get(0).getData(3));
        // 선택한 ToDoList 데이터를 모두 가져옴

        StringTokenizer Stz_1 = new StringTokenizer(S_D,"-");
        StringTokenizer Stz_2 = new StringTokenizer(E_D,"-");

        String S_year = new String();
        String S_month = new String();
        String S_day = new String();
        String E_year = new String();
        String E_month = new String();
        String E_day = new String();

        while(Stz_1.hasMoreTokens())
        {
            S_year = Stz_1.nextToken();
            S_month = Stz_1.nextToken();
            S_day = Stz_1.nextToken();
        }

        while(Stz_2.hasMoreTokens())
        {
            E_year = Stz_2.nextToken();
            E_month = Stz_2.nextToken();
            E_day = Stz_2.nextToken();
        }


        ProjectStartDay_Y.setText(S_year);
        ProjectStartDay_M.setText(S_month);
        ProjectStartDay_D.setText(S_day);
        ProjectEndDay_Y.setText(E_year);
        ProjectEndDay_M.setText(E_month);
        ProjectEndDay_D.setText(E_day);
        ProjectBriefy.setText(Breify);

        ////
        listitem.clear();
        ////
////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    public void onClick(View v)
    {
        RequestParams params = new RequestParams();
        switch(v.getId())
        {
            case R.id.makeProject_M : //확인 버튼을 눌렀을때
                inputBriefy = ProjectBriefy.getText().toString();
                inputName_P = ProjectName.getText().toString();
                inputStartDay_P_Y = ProjectStartDay_Y.getText().toString();
                inputStartDay_P_M = ProjectStartDay_M.getText().toString();
                inputStartDay_P_D = ProjectStartDay_D.getText().toString();
                inputEndDay_P_Y = ProjectEndDay_Y.getText().toString();
                inputEndDay_P_M = ProjectEndDay_M.getText().toString();
                inputEndDay_P_D = ProjectEndDay_D.getText().toString();

                if(inputName_P.isEmpty() == true && inputBriefy.isEmpty() == true &&inputStartDay_P_Y.isEmpty() == true && inputEndDay_P_Y.isEmpty() == true
                        || inputStartDay_P_M.isEmpty() == true && inputEndDay_P_M.isEmpty() == true && inputStartDay_P_D.isEmpty() == true && inputEndDay_P_D.isEmpty() == true)
                {
                    Toast.makeText(ProjectWizard_Modify.this,"빈칸을 채워주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    params.put("Email", UserBean.Email);
                    params.put("CateName", CategoryBean.CateName);
                    //params.put("ProjectName", ProjectBean.ProjectName);
                    params.put("oldProjectName",ProjectBean.ProjectName);
                    params.put("newProjectName",inputName_P);
                    params.put("ProjectBriefy",inputBriefy);
                    //params.put("state",inputState);
                    //Log.i("EEEE","inputState"+inputState);
                    params.put("ProjectStartDate",inputStartDay_P_Y+"-"+inputStartDay_P_M+"-"+inputStartDay_P_D);
                    params.put("ProjectEndDate",inputEndDay_P_Y+"-"+inputEndDay_P_M+"-"+inputEndDay_P_D);
                    Log.i("LogTag aaa", inputStartDay_P_Y + "-" + inputStartDay_P_M + "-" + inputStartDay_P_D);
                    Log.i("LogTag aaa", inputEndDay_P_Y+"-"+inputEndDay_P_M+"-"+inputEndDay_P_D);
                }

                ProjectManager.getInstnace().Projectchange_sendData(Login_Main.getContext(), params, new ProjectManager.OnResultListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        flag = true;
                        Toast.makeText(ProjectWizard_Modify.this, "성공적", Toast.LENGTH_SHORT).show();
                        //projectinfo = result;
                        intent = new Intent(ProjectWizard_Modify.this, CategoryActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFail(int code) {
                        Toast.makeText(ProjectWizard_Modify.this, "실패함", Toast.LENGTH_SHORT).show();
                    }
                });

                /*if(flag.equals(true)) {
                    intent = new Intent(CateWizard.this, CategoryActivity.class);
                    startActivity(intent);
                }*/
        }
    }

    protected void setExecute(String str){
        String ProjectName;
        String ProjectStartDate;
        String ProjectEndDate;
        String ProjectBriefy;
        try{
            JSONObject root = new JSONObject(str);
            JSONArray ja = root.getJSONArray("results"); //get the JSONArray which I made in the php file. the name of JSONArray is "results"

            for(int i=0;i<ja.length();i++){
                JSONObject jo = ja.getJSONObject(i);
                ProjectName = jo.getString("ProjectName");
                ProjectStartDate = jo.getString("ProjectStartDate");
                ProjectEndDate = jo.getString("ProjectEndDate");
                ProjectBriefy = jo.getString("ProjectBriefy");
                //Log.i("LogTag",ProjectName);
                //Log.i("LogTag : test ", ProjectName + "," + ToDoStartDate + "," + ToDoEndDate);
                listitem.add(new ListItem_Project(ProjectName, ProjectStartDate, ProjectEndDate, ProjectBriefy));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
