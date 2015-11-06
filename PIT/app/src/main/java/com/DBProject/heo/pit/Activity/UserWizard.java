package com.DBProject.heo.pit.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.DBProject.heo.pit.InfoClass.AddressBean;
import com.DBProject.heo.pit.InfoClass.UserBean;
import com.DBProject.heo.pit.Manager.ListItem_User;
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
public class UserWizard extends Activity {

    private int temp_idx ;
    private ListView m_ListView;
    private ArrayAdapter<String> m_Adapter;
    private String URL = new String();
    private String Address = new String();
    private getMySQL_Project_ToDo db = new getMySQL_Project_ToDo();
    private String result = new String();
    private ArrayList<ListItem_User> listitem = new ArrayList<ListItem_User>();
    private Intent intent;
    //private Boolean flag = false;
    private String selected_Email = new String();
    //private String selected_PassWord = new String();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su_lists);

        m_Adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_list_item_single_choice);

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.listview_A);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        // ListView 아이템 터치 시 이벤트 추가
        m_ListView.setOnItemClickListener(onClickListItem);

        makelist();

    }



    private OnItemClickListener onClickListItem = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            selected_Email =  m_Adapter.getItem(arg2);
            temp_idx = arg2;
            // 이벤트 발생 시 해당 아이템 위치의 텍스트를 출력
            Toast.makeText(getApplicationContext(), m_Adapter.getItem(arg2), Toast.LENGTH_SHORT).show();
            Log.i("LogTag","OHOHOH");
            ///////////////////////////////////////////////////////////////////////////////////////////////////팝업창 생성코드
            AlertDialog.Builder builder = new AlertDialog.Builder(UserWizard.this);
            builder.setTitle("확인 상자")        // 제목 설정
                    .setMessage(m_Adapter.getItem(arg2) + "의 계정으로 이동하시겠습니까")        // 메세지 설정
                    .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        // 확인 버튼 클릭시 설정

                        public void onClick(DialogInterface dialog, int whichButton) {
                            UserBean.Email = selected_Email;
                            intent = new Intent(UserWizard.this, CategoryActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        // 취소 버튼 클릭시 설정
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }
                    });
            AlertDialog dialog = builder.create();    // 알림창 객체 생성
            dialog.show();
        }
        //////////////////////////////////////////////////////////////////////////////////////////
    };

    protected void setExecute(String str){
        String Email;
        String UserName;
        String PassWord;
        try{
            JSONObject root = new JSONObject(str);
            JSONArray ja = root.getJSONArray("results"); //get the JSONArray which I made in the php file. the name of JSONArray is "results"

            for(int i=0;i<ja.length();i++){
                JSONObject jo = ja.getJSONObject(i);
                Email = jo.getString("Email");
                UserName = jo.getString("UserName");
                PassWord = jo.getString("PassWord");
                //Log.i("LogTag", ToDoState);
                //Log.i("LogTag : test ", ToDoName + "," + ToDoStartDate + "," + ToDoEndDate);
                listitem.add(new ListItem_User(Email, UserName, PassWord));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void makelist()
    {
        //Address = "http://192.168.0.37:8080/DBProject/todo_list";
        //Address = "http://117.16.198.171:8080/DBProject/todo_list";
        URL = AddressBean.Address + "/user_list";

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
}
