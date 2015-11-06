package com.DBProject.heo.pit.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.DBProject.heo.pit.InfoClass.AddressBean;
import com.DBProject.heo.pit.InfoClass.CategoryBean;
import com.DBProject.heo.pit.InfoClass.ProjectBean;
import com.DBProject.heo.pit.InfoClass.ToDoBean;
import com.DBProject.heo.pit.InfoClass.UserBean;
import com.DBProject.heo.pit.Manager.ListItem_ToDoList;
import com.DBProject.heo.pit.Manager.Login_Main;
import com.DBProject.heo.pit.Manager.ToDoManager;
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

public class ToDoWizard_Modify extends Activity implements View.OnClickListener , RadioGroup.OnCheckedChangeListener{
    private String inputName;
    private int inputState = -1;
    private String inputStartDay_Y;
    private String inputStartDay_M;
    private String inputStartDay_D;
    private String inputEndDay_Y;
    private String inputEndDay_M;
    private String inputEndDay_D;
    private Button changebtn;
    private EditText ToDoName;
    //private EditText ToDoState;
    private RadioGroup rg1;
    private String S_D;
    private String E_D;
    private ArrayList<String> mGroupList = null;
    private ArrayList<ArrayList<String>> mChildList = null;
    private ExpandableListView mListView;


    private ArrayList<ListItem_ToDoList> listitem = new ArrayList<ListItem_ToDoList>();

    private EditText ToDoStartDay_Y;
    private EditText ToDoStartDay_M;
    private EditText ToDoStartDay_D;
    private EditText ToDoEndDay_Y;
    private EditText ToDoEndDay_M;
    private EditText ToDoEndDay_D;
    private Boolean flag = false;
    private Intent intent;
    private getMySQL_Project_ToDo db = new getMySQL_Project_ToDo();
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifytodo);
        changebtn = (Button)findViewById(R.id.ChangeToDo_M);
        ToDoName = (EditText) findViewById(R.id.ToDoName_M);
        //ToDoState = (EditText) findViewById(R.id.ToDoState);
        rg1 = (RadioGroup) findViewById(R.id.RG1_M);
        ToDoStartDay_Y = (EditText) findViewById(R.id.ToDoStart_Y_M);
        ToDoStartDay_M = (EditText) findViewById(R.id.ToDoStart_M_M);
        ToDoStartDay_D = (EditText) findViewById(R.id.ToDoStart_D_M);
        ToDoEndDay_Y = (EditText) findViewById(R.id.ToDoEnd_Y_M);
        ToDoEndDay_M = (EditText) findViewById(R.id.ToDoEnd_M_M);
        ToDoEndDay_D = (EditText) findViewById(R.id.ToDoEnd_D_M);
        ToDoStartDay_Y.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL
                | InputType.TYPE_NUMBER_FLAG_SIGNED);
        ToDoStartDay_M.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL
                | InputType.TYPE_NUMBER_FLAG_SIGNED);
        ToDoStartDay_D.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL
                | InputType.TYPE_NUMBER_FLAG_SIGNED);
        ToDoEndDay_Y.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL
                | InputType.TYPE_NUMBER_FLAG_SIGNED);
        ToDoEndDay_M.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL
                | InputType.TYPE_NUMBER_FLAG_SIGNED);
        ToDoEndDay_D.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL
                | InputType.TYPE_NUMBER_FLAG_SIGNED);
        changebtn.setOnClickListener(this);
        rg1.setOnCheckedChangeListener(this);

        Log.i("EEEE","SS");
        String result = new String();
        String URL;
        //String Address = "http://192.168.0.37:8080/DBProject/todo_item";
        //String Address = "http://192.168.0.51:8080/DBProject/todo_item";
        //Address = "http://117.16.198.171:8080/DBProject/todo_list";
        URL = AddressBean.Address + "/todo_item" + "?Email="+ UserBean.Email + "&CateName=" + CategoryBean.CateName + "&ProjectName=" + ProjectBean.ProjectName +"&ToDoName=" + ToDoBean.ToDoName;
        Log.i("EEEE", ToDoBean.ToDoName);
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
        ToDoName.setText(listitem.get(0).getData(0));
        Log.i("EEEE", "KYU");
        S_D = listitem.get(0).getData(1);
        E_D = listitem.get(0).getData(2);
        int States = Integer.parseInt(listitem.get(0).getData(3));
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
        Log.i("EEEE", "JKSDJFLKSDJKLF");
        ToDoStartDay_Y.setText(S_year);
        ToDoStartDay_M.setText(S_month);
        ToDoStartDay_D.setText(S_day);
        ToDoEndDay_Y.setText(E_year);
        ToDoEndDay_M.setText(E_month);
        ToDoEndDay_D.setText(E_day);
        Log.i("EEEE", "SDSFDSFS");

        if(States == 0)
        {

        }
        else if(States == 1)
        {

        }
        else if(States == 2)
        {

        }

    }

    public void onClick(View v)
    {
        RequestParams params = new RequestParams();
        switch(v.getId())
        {
            case R.id.ChangeToDo_M : //확인 버튼을 눌렀을때
                inputName = ToDoName.getText().toString();
                inputStartDay_Y = ToDoStartDay_Y.getText().toString();
                inputStartDay_M = ToDoStartDay_M.getText().toString();
                inputStartDay_D = ToDoStartDay_D.getText().toString();
                inputEndDay_Y = ToDoEndDay_Y.getText().toString();
                inputEndDay_M = ToDoEndDay_M.getText().toString();
                inputEndDay_D = ToDoEndDay_D.getText().toString();

                if(inputName.isEmpty() == true && inputState == -1 && inputStartDay_Y.isEmpty() == true && inputEndDay_Y.isEmpty() == true
                        || inputStartDay_M.isEmpty() == true && inputEndDay_M.isEmpty() == true && inputStartDay_D.isEmpty() == true && inputEndDay_D.isEmpty() == true)
                {
                    Toast.makeText(ToDoWizard_Modify.this,"빈칸을 채워주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    params.put("Email", UserBean.Email);
                    params.put("CateName", CategoryBean.CateName);
                    params.put("ProjectName", ProjectBean.ProjectName);
                    params.put("newToDoName",inputName);
                    params.put("oldToDoName",ToDoBean.ToDoName);
                    params.put("state",(inputState));
                    params.put("ToDoStartDate",inputStartDay_Y+"-"+inputStartDay_M+"-"+inputStartDay_D);
                    params.put("ToDoEndDate",inputEndDay_Y+"-"+inputEndDay_M+"-"+inputEndDay_D);
                    Log.i("LogTag aaa", inputStartDay_Y + "-" + inputStartDay_M + "-" + inputStartDay_D);
                    Log.i("LogTag aaa", inputEndDay_Y+"-"+inputEndDay_M+"-"+inputEndDay_D);
                }

                ToDoManager.getInstnace().ToDochange_sendData(Login_Main.getContext(), params, new ToDoManager.OnResultListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        flag = true;
                        Toast.makeText(ToDoWizard_Modify.this, "성공적", Toast.LENGTH_SHORT).show();
                        //projectinfo = result;
                        intent = new Intent(ToDoWizard_Modify.this, CategoryActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFail(int code) {
                        Toast.makeText(ToDoWizard_Modify.this, "실패함", Toast.LENGTH_SHORT).show();
                    }
                });

                /*if(flag.equals(true)) {
                    intent = new Intent(CateWizard.this, CategoryActivity.class);
                    startActivity(intent);
                }*/
        }
    }

    public void onCheckedChanged(RadioGroup arg0, int arg1)
    {
        //TODO Auto-generated method stub
        if(rg1.getCheckedRadioButtonId() == R.id.unstart_M) //미시작 설정
        {
            inputState = 0;
            Toast.makeText(ToDoWizard_Modify.this, "d" + inputState, Toast.LENGTH_SHORT).show();
        }
        else if(rg1.getCheckedRadioButtonId() == R.id.start_M) // 진행중 설정
        {
            inputState = 1;
            Toast.makeText(ToDoWizard_Modify.this, "d" + inputState, Toast.LENGTH_SHORT).show();
        }
        else if(rg1.getCheckedRadioButtonId() == R.id.complete_M) // 완료 설정
        {
            inputState = 2;
            Toast.makeText(ToDoWizard_Modify.this, "d" + inputState, Toast.LENGTH_SHORT).show();
        }
    }

    protected void setExecute(String str){
        String ToDoName;
        String ToDoStartDate;
        String ToDoEndDate;
        String ToDoState;
        try{
            JSONObject root = new JSONObject(str);
            JSONArray ja = root.getJSONArray("results"); //get the JSONArray which I made in the php file. the name of JSONArray is "results"

            for(int i=0;i<ja.length();i++){
                JSONObject jo = ja.getJSONObject(i);
                ToDoName = jo.getString("ToDoName");
                ToDoStartDate = jo.getString("ToDoStartDate");
                ToDoEndDate = jo.getString("ToDoEndDate");
                ToDoState = jo.getString("state");
                Log.i("LogTag",ToDoState);
                Log.i("LogTag : test ", ToDoName + "," + ToDoStartDate + "," + ToDoEndDate);
                listitem.add(new ListItem_ToDoList(ToDoName, ToDoStartDate, ToDoEndDate, ToDoState));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
