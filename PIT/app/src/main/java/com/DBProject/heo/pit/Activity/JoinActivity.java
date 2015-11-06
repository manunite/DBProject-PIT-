package com.DBProject.heo.pit.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.DBProject.heo.pit.Manager.Login_Main;
import com.DBProject.heo.pit.Manager.Login_SignUp_Manager;
import com.DBProject.heo.pit.R;

/**
 * Created by Heo on 15. 9. 21..
 */

public class JoinActivity extends Activity implements View.OnClickListener{

    private EditText Email;
    private EditText pw;
    private EditText pwc;
    private EditText Name;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Name = (EditText)findViewById(R.id.Login_Name);
        Email = (EditText)findViewById(R.id.Login_Email);
        pw = (EditText)findViewById(R.id.Login_PassWord);
        pwc = (EditText)findViewById(R.id.Login_PassWord_cer);
        signup = (Button)findViewById(R.id.Join_btn);

        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String Emails = Email.getText().toString();
        String PassWords = pw.getText().toString();
        String Confirm = pwc.getText().toString();
        String UserName = Name.getText().toString();

        if(Emails.isEmpty() == true || PassWords.isEmpty() == true)
        {
            Toast.makeText(JoinActivity.this, "이메일 혹은 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!PassWords.equals(Confirm)) {
            Toast.makeText(JoinActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        Login_SignUp_Manager.getInstnace().Join_sendData(Login_Main.getContext(), Emails, PassWords, UserName, new Login_SignUp_Manager.OnResultListener<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                Log.e("testine", "success");
            }

            @Override
            public void onFail(int code) {
                Log.e("testine", "fail");
            }
        });
        finish();
    }
}
