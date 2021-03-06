package com.DBProject.heo.pit.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.DBProject.heo.pit.InfoClass.adminUserBean;
import com.DBProject.heo.pit.Manager.Login_Main;
import com.DBProject.heo.pit.Manager.adminLogin_Manager;
import com.DBProject.heo.pit.R;

/**
 * Created by Heo on 15. 9. 21..
 */
public class adminLoginActivity extends Activity implements View.OnClickListener{

    private Button login;
    private EditText EmailBox;
    private EditText PasswordBox;
    public String Emails;
    public String Passwords;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su_login);

        adminUserBean.Su_Email = null;
        adminUserBean.Su_PassWord = null;

        login = (Button)findViewById(R.id.Login_btn_A);
        EmailBox = (EditText)findViewById(R.id.Login_Email_A);
        PasswordBox = (EditText)findViewById(R.id.Login_PassWord_A);

        login.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //@Override
    //private class NewsThreads extends AsyncTask<Object,Object,Object>{
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Login_btn_A:

                Emails = EmailBox.getText().toString();
                Passwords = PasswordBox.getText().toString();
                if(Emails.isEmpty() == true || Passwords.isEmpty() == true)
                {
                    Toast.makeText(adminLoginActivity.this,"이메일 혹은 패스워드를 입력하세요.",Toast.LENGTH_SHORT).show();
                    return ;
                }
                boolean islogin = false;

                adminLogin_Manager nm = adminLogin_Manager.getInstnace();

                int n;
                nm.adminLogin_sendData(Login_Main.getContext(), Emails, Passwords, new adminLogin_Manager.OnResultListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        if (result == true) {
                            Toast.makeText(adminLoginActivity.this, "성공한 로그인.", Toast.LENGTH_SHORT).show();
//                            Login_Main.Email = Emails;

                            adminUserBean.Su_Email = Emails;
                            adminUserBean.Su_PassWord = Passwords;

                            intent = new Intent(adminLoginActivity.this, UserWizard.class);
                            startActivity(intent);

                        } else if (result == false) {
                            Toast.makeText(adminLoginActivity.this, "실패한 로그인.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFail(int code) {
                        Toast.makeText(adminLoginActivity.this, "DB와의 연결 실패.", Toast.LENGTH_SHORT).show();

                    }


                });
                break;
        }

    }
}

