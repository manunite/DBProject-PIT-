package com.DBProject.heo.pit.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.DBProject.heo.pit.InfoClass.AddressBean;
import com.DBProject.heo.pit.InfoClass.CategoryBean;
import com.DBProject.heo.pit.InfoClass.UserBean;
import com.DBProject.heo.pit.Manager.ListItem;
import com.DBProject.heo.pit.Manager.getMySQL_Project_ToDo;
import com.DBProject.heo.pit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Heo on 15. 10. 31..
 */
public class CateWizard_Modify_List extends Activity {

    private int temp_idx ;
    private ListView m_ListView;
    private ArrayAdapter<String> m_Adapter;
    private String URL = new String();
    private String Address = new String();
    private getMySQL_Project_ToDo db = new getMySQL_Project_ToDo();
    private String result = new String();
    private ArrayList<ListItem> listitem = new ArrayList<ListItem>();
    private Intent intent;
    private Boolean flag = false;
    private String temp_CateName = new String();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifycate_list);

        m_Adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_list_item_single_choice);

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.listview_C);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        // ListView 아이템 터치 시 이벤트 추가
        m_ListView.setOnItemClickListener(onClickListItem);
        makelist();
        Log.i("EEEE", "KYUKYU");
    }



    private OnItemClickListener onClickListItem = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Log.i("EEEE", "EEEWWQAAA");
            temp_idx = arg2;
            // 이벤트 발생 시 해당 아이템 위치의 텍스트를 출력
            temp_CateName = m_Adapter.getItem(temp_idx);
            Log.i("EEEE", "SS");
            CategoryBean.CateName = temp_CateName;
            Intent intent;
            intent = new Intent(CateWizard_Modify_List.this,CateWizard_Modify.class);
            startActivity(intent);

        }
        //////////////////////////////////////////////////////////////////////////////////////////
    };

    protected void setExecute(String str){
        String CateName;
        //String ProjectStartDate;
        //String ProjectEndDate;
        //String ProjectBriefy;
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

    private void makelist()
    {
        //Address = "http://192.168.0.37:8080/DBProject/category_list";

        //Address = "http://117.16.198.171:8080/DBProject/todo_list";
        URL = AddressBean.Address + "/category_list" + "?Email="+ UserBean.Email;

        try {
            result = db.execute(URL).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        setExecute(result);

        for(int i=0;i<listitem.size();i++)
        {
            m_Adapter.add(listitem.get(i).getData(0));
        }
        listitem.clear();
    }

    /*private boolean checkChildList()
    {
        String Address_S = new String();
        String URL_S = new String();
        String result_S = new String();
        getMySQL_Project_ToDo db_s = new getMySQL_Project_ToDo();
        Address_S = "http://192.168.0.37:8080/DBProject/todo_list";
        //Address = "http://117.16.198.171:8080/DBProject/todo_list";
        URL_S = Address_S + "?Email="+ UserBean.Email + "&CateName=" + CategoryBean.CateName + "&ProjectName=" + temp_CateName;
        try {
            result_S = db_s.execute(URL_S).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(result_S.length() > 50)
        {
            Toast.makeText(CateWizard_Modify_List.this, "하위에 ToDoList가 존재합니다.", Toast.LENGTH_SHORT).show();
        }
        else
            return true;

        return false;
    }*/
}
