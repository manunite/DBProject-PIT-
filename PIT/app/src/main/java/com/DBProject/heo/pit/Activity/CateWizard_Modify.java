package com.DBProject.heo.pit.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.DBProject.heo.pit.InfoClass.AddressBean;
import com.DBProject.heo.pit.InfoClass.CategoryBean;
import com.DBProject.heo.pit.InfoClass.UserBean;
import com.DBProject.heo.pit.Manager.CateManager;
import com.DBProject.heo.pit.Manager.ListItem;
import com.DBProject.heo.pit.Manager.Login_Main;
import com.DBProject.heo.pit.Manager.getMySQL_Project_ToDo;
import com.DBProject.heo.pit.R;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Heo on 15. 10. 11..
 */

public class CateWizard_Modify extends Activity implements View.OnClickListener{
    private String inputName;
    private EditText CateName;
    private Button changeBtn;
    //private EditText ToDoState;


    private ArrayList<ListItem> listitem = new ArrayList<ListItem>();

    private Boolean flag = false;
    private Intent intent;
    private getMySQL_Project_ToDo db = new getMySQL_Project_ToDo();
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifycate);
        changeBtn = (Button)findViewById(R.id.ChangeCate_M); //버튼
        CateName = (EditText) findViewById(R.id.CateName_M); //인풋네임
        //ToDoState = (EditText) findViewById(R.id.ToDoState);
        changeBtn.setOnClickListener(this);

        Log.i("EEEE","JSKDJFKLS");
        String result = new String();
        String URL;
        //String Address = "http://192.168.0.37:8080/DBProject/category_item";
        //String Address = "http://192.168.0.51:8080/DBProject/todo_item";
        //Address = "http://117.16.198.171:8080/DBProject/todo_list";
        URL = AddressBean.Address + "/category_item" + "?Email="+ UserBean.Email + "&CateName=" + CategoryBean.CateName;
        Log.i("EEEE",CategoryBean.CateName + UserBean.Email);
        try {
            result = db.execute(URL).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i("EEEE",result);
        Log.i("EEEE","MMMMMMM");
        setExecute(result);
        Log.i("EEEE", "PPPP");
        CateName.setText(listitem.get(0).getData(0));

    }

    public void onClick(View v)
    {
        RequestParams params = new RequestParams();
        switch(v.getId())
        {
            case R.id.ChangeCate_M : //확인 버튼을 눌렀을때
                inputName = CateName.getText().toString();

                if(inputName.isEmpty() == true)
                {
                    Toast.makeText(CateWizard_Modify.this,"빈칸을 채워주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    params.put("Email", UserBean.Email);
                    params.put("oldCateName", CategoryBean.CateName);
                    params.put("newCateName",inputName);
                }

                CateManager.getInstnace().change_sendData(Login_Main.getContext(), params, new CateManager.OnResultListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        flag = true;
                        Toast.makeText(CateWizard_Modify.this, "성공적", Toast.LENGTH_SHORT).show();
                        //projectinfo = result;
                        //intent = new Intent(CateWizard_Modify.this, CateWizard_Modify_List.class);
                        intent = new Intent(CateWizard_Modify.this, CategoryActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFail(int code) {
                        Toast.makeText(CateWizard_Modify.this, "실패함", Toast.LENGTH_SHORT).show();
                    }
                });

                /*if(flag.equals(true)) {
                    intent = new Intent(CateWizard.this, CategoryActivity.class);
                    startActivity(intent);
                }*/
        }
    }

    protected void setExecute(String str){
        String CateName;
        try{
            JSONObject root = new JSONObject(str);
            JSONArray ja = root.getJSONArray("results"); //get the JSONArray which I made in the php file. the name of JSONArray is "results"

            for(int i=0;i<ja.length();i++){
                JSONObject jo = ja.getJSONObject(i);
                CateName = jo.getString("CateName");
                listitem.add(new ListItem(CateName));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
