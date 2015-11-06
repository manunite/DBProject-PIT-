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
import com.DBProject.heo.pit.InfoClass.ProjectBean;
import com.DBProject.heo.pit.InfoClass.UserBean;
import com.DBProject.heo.pit.Manager.Login_Main;
import com.DBProject.heo.pit.Manager.ToDoManager;
import com.DBProject.heo.pit.R;
import com.loopj.android.http.RequestParams;

/**
 * Created by Heo on 15. 10. 11..
 */

public class ToDoWizard extends Activity implements View.OnClickListener , RadioGroup.OnCheckedChangeListener{
    private String inputName;
    private int inputState = -1;
    private String inputStartDay_Y;
    private String inputStartDay_M;
    private String inputStartDay_D;
    private String inputEndDay_Y;
    private String inputEndDay_M;
    private String inputEndDay_D;
    private Button makesbtn;
    private EditText ToDoName;
    //private EditText ToDoState;
    private RadioGroup rg1;

    private EditText ToDoStartDay_Y;
    private EditText ToDoStartDay_M;
    private EditText ToDoStartDay_D;
    private EditText ToDoEndDay_Y;
    private EditText ToDoEndDay_M;
    private EditText ToDoEndDay_D;
    private Boolean flag = false;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createtodo);
        makesbtn = (Button)findViewById(R.id.makeToDo);
        ToDoName = (EditText) findViewById(R.id.ToDoName);
        //ToDoState = (EditText) findViewById(R.id.ToDoState);
        rg1 = (RadioGroup) findViewById(R.id.RG1);
        ToDoStartDay_Y = (EditText) findViewById(R.id.ToDoStart_Y);
        ToDoStartDay_M = (EditText) findViewById(R.id.ToDoStart_M);
        ToDoStartDay_D = (EditText) findViewById(R.id.ToDoStart_D);
        ToDoEndDay_Y = (EditText) findViewById(R.id.ToDoEnd_Y);
        ToDoEndDay_M = (EditText) findViewById(R.id.ToDoEnd_M);
        ToDoEndDay_D = (EditText) findViewById(R.id.ToDoEnd_D);
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
        makesbtn.setOnClickListener(this);
        rg1.setOnCheckedChangeListener(this);
    }

    public void onClick(View v)
    {
        RequestParams params = new RequestParams();
        switch(v.getId())
        {
            case R.id.makeToDo : //확인 버튼을 눌렀을때
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
                    Toast.makeText(ToDoWizard.this,"빈칸을 채워주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    params.put("Email", UserBean.Email);
                    params.put("CateName", CategoryBean.CateName);
                    params.put("ProjectName", ProjectBean.ProjectName);
                    params.put("ToDoName",inputName);
                    params.put("state",inputState);
                    Log.i("EEEE","inputState"+inputState);
                    params.put("ToDoStartDate",inputStartDay_Y+"-"+inputStartDay_M+"-"+inputStartDay_D);
                    params.put("ToDoEndDate",inputEndDay_Y+"-"+inputEndDay_M+"-"+inputEndDay_D);
                    Log.i("LogTag aaa", inputStartDay_Y + "-" + inputStartDay_M + "-" + inputStartDay_D);
                    Log.i("LogTag aaa", inputEndDay_Y+"-"+inputEndDay_M+"-"+inputEndDay_D);
                }

                ToDoManager.getInstnace().ToDocreate_sendData(Login_Main.getContext(), params, new ToDoManager.OnResultListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        flag = true;
                        Toast.makeText(ToDoWizard.this, "성공적", Toast.LENGTH_SHORT).show();
                        //projectinfo = result;
                        intent = new Intent(ToDoWizard.this, CategoryActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFail(int code) {
                        Toast.makeText(ToDoWizard.this, "실패함", Toast.LENGTH_SHORT).show();
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
        if(rg1.getCheckedRadioButtonId() == R.id.unstart) //미시작 설정
        {
            inputState = 0;
            Toast.makeText(ToDoWizard.this, "s" + inputState, Toast.LENGTH_SHORT).show();
        }
        else if(rg1.getCheckedRadioButtonId() == R.id.start) // 진행중 설정
        {
            inputState = 1;
            Toast.makeText(ToDoWizard.this, "s" + inputState, Toast.LENGTH_SHORT).show();
        }
        else if(rg1.getCheckedRadioButtonId() == R.id.complete) // 완료 설정
        {
            inputState = 2;
            Toast.makeText(ToDoWizard.this, "s" + inputState, Toast.LENGTH_SHORT).show();
        }
    }
}
