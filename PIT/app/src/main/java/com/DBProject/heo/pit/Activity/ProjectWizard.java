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

import com.DBProject.heo.pit.InfoClass.CategoryBean;
import com.DBProject.heo.pit.InfoClass.UserBean;
import com.DBProject.heo.pit.Manager.Login_Main;
import com.DBProject.heo.pit.Manager.ProjectManager;
import com.DBProject.heo.pit.R;
import com.loopj.android.http.RequestParams;

/**
 * Created by Heo on 15. 10. 11..
 */

public class ProjectWizard extends Activity implements View.OnClickListener {
    private String inputName_P;
    private String inputStartDay_P_Y;
    private String inputStartDay_P_M;
    private String inputStartDay_P_D;
    private String inputEndDay_P_Y;
    private String inputEndDay_P_M;
    private String inputEndDay_P_D;
    private String inputBriefy;
    private Button makesbtn;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createpro);
        makesbtn = (Button)findViewById(R.id.makeProject);
        ProjectName = (EditText) findViewById(R.id.ProjectName);
        //ToDoState = (EditText) findViewById(R.id.ToDoState);
        rg1 = (RadioGroup) findViewById(R.id.RG1);
        ProjectBriefy = (EditText) findViewById(R.id.ProjectBriefy);
        ProjectStartDay_Y = (EditText) findViewById(R.id.ProjectStart_Y);
        ProjectStartDay_M = (EditText) findViewById(R.id.ProjectStart_M);
        ProjectStartDay_D = (EditText) findViewById(R.id.ProjectStart_D);
        ProjectEndDay_Y = (EditText) findViewById(R.id.ProjectEnd_Y);
        ProjectEndDay_M = (EditText) findViewById(R.id.ProjectEnd_M);
        ProjectEndDay_D = (EditText) findViewById(R.id.ProjectEnd_D);
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
        makesbtn.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        RequestParams params = new RequestParams();
        switch(v.getId())
        {
            case R.id.makeProject : //확인 버튼을 눌렀을때
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
                    Toast.makeText(ProjectWizard.this,"빈칸을 채워주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    params.put("Email", UserBean.Email);
                    params.put("CateName", CategoryBean.CateName);
                    //params.put("ProjectName", ProjectBean.ProjectName);
                    params.put("ProjectName",inputName_P);
                    params.put("ProjectBriefy",inputBriefy);
                    //params.put("state",inputState);
                    //Log.i("EEEE","inputState"+inputState);
                    params.put("ProjectStartDate",inputStartDay_P_Y+"-"+inputStartDay_P_M+"-"+inputStartDay_P_D);
                    params.put("ProjectEndDate",inputEndDay_P_Y+"-"+inputEndDay_P_M+"-"+inputEndDay_P_D);
                    Log.i("LogTag aaa", inputStartDay_P_Y + "-" + inputStartDay_P_M + "-" + inputStartDay_P_D);
                    Log.i("LogTag aaa", inputEndDay_P_Y+"-"+inputEndDay_P_M+"-"+inputEndDay_P_D);
                }

                ProjectManager.getInstnace().Projectcreate_sendData(Login_Main.getContext(), params, new ProjectManager.OnResultListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        flag = true;
                        Toast.makeText(ProjectWizard.this, "성공적", Toast.LENGTH_SHORT).show();
                        //projectinfo = result;
                        intent = new Intent(ProjectWizard.this, CategoryActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFail(int code) {
                        Toast.makeText(ProjectWizard.this, "실패함", Toast.LENGTH_SHORT).show();
                    }
                });

                /*if(flag.equals(true)) {
                    intent = new Intent(CateWizard.this, CategoryActivity.class);
                    startActivity(intent);
                }*/
        }
    }


}
