package com.DBProject.heo.pit.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.DBProject.heo.pit.InfoClass.UserBean;
import com.DBProject.heo.pit.Manager.CateManager;
import com.DBProject.heo.pit.Manager.Login_Main;
import com.DBProject.heo.pit.R;
import com.loopj.android.http.RequestParams;

/**
 * Created by Heo on 15. 10. 11..
 */

public class CateWizard extends Activity implements View.OnClickListener{
    private String input;
    private Button makesbtn;
    private EditText nCateName;
    private Boolean flag = false;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate_wizard);
        makesbtn = (Button)findViewById(R.id.submit);
        nCateName = (EditText) findViewById(R.id.cateName);
        makesbtn.setOnClickListener(this);
    }

    public void onClick(View v)
    {


        RequestParams params = new RequestParams();
        switch(v.getId())
        {
            case R.id.submit :
                input = nCateName.getText().toString();
                if(input.isEmpty() == true)
                {
                    Toast.makeText(CateWizard.this,"빈칸을 채워주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(input.isEmpty() == false)
                {
                    params.put("Email", UserBean.Email);
                    params.put("CateName",input);
                }

                CateManager.getInstnace().create_sendData(Login_Main.getContext(), params, new CateManager.OnResultListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        flag = true;
                        Toast.makeText(CateWizard.this, "성공적", Toast.LENGTH_SHORT).show();
                        //projectinfo = result;
                        intent = new Intent(CateWizard.this, CategoryActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFail(int code) {
                        Toast.makeText(CateWizard.this, "실패함", Toast.LENGTH_SHORT).show();
                    }
                });

                /*if(flag.equals(true)) {
                    intent = new Intent(CateWizard.this, CategoryActivity.class);
                    startActivity(intent);
                }*/
        }
    }

    /*
    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            input = nCateName.getText().toString();
            if(input != null){

                RequestParams params = new RequestParams();
                params.put("Email",Email);        //넣을 값은 오른쪽. 새 카테고리 추가
                params.put("CateName", input);

                ProjectManager.getInstnace().Proitem_sendData(MyApplication.getContext(), params, new ProjectManager.OnResultListener<ProjectBean>() {
                    @Override
                    public void onSuccess(ProjectBean result) {
                        projectinfo = result;
                    }

                    @Override
                    public void onFail(int code) {
                    }
                }
            });


            ToDoManager.getInstnace().ToDolist_sendData(MyApplication.getContext(), params, new ToDoManager.OnResultListener<Vector>() {
                @Override
                public void onSuccess(Vector result) {
                    for (int i = 0; i < result.size(); i++) {
                        ourEvents.add((TLEvent) result.elementAt(i));
                    }
                }

                @Override
                public void onFail(int code) {

                }
            });

        }
    };
*/
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cate_wizard, menu);
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
    }*/
}
