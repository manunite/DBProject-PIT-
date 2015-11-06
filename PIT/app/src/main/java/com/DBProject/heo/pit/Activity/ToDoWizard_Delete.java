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
import com.DBProject.heo.pit.InfoClass.CategoryBean;
import com.DBProject.heo.pit.InfoClass.ProjectBean;
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
import java.util.concurrent.ExecutionException;

/**
 * Created by Heo on 15. 10. 31..
 */
public class ToDoWizard_Delete extends Activity {

    private int temp_idx ;
    private ListView m_ListView;
    private ArrayAdapter<String> m_Adapter;
    private String URL = new String();
    private String Address = new String();
    private getMySQL_Project_ToDo db = new getMySQL_Project_ToDo();
    private String result = new String();
    static public ArrayList<ListItem_ToDoList> listitem = new ArrayList<ListItem_ToDoList>();
    private Intent intent;
    private Boolean flag = false;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletetodo);

        m_Adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_list_item_single_choice);

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.listview);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        // ListView 아이템 터치 시 이벤트 추가
        m_ListView.setOnItemClickListener(onClickListItem);

        makelist();

    }



    private OnItemClickListener onClickListItem = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            temp_idx = arg2;
            // 이벤트 발생 시 해당 아이템 위치의 텍스트를 출력
            Toast.makeText(getApplicationContext(), m_Adapter.getItem(arg2), Toast.LENGTH_SHORT).show();
            Log.i("LogTag","OHOHOH");
            ///////////////////////////////////////////////////////////////////////////////////////////////////팝업창 생성코드
            AlertDialog.Builder builder = new AlertDialog.Builder(ToDoWizard_Delete.this);
            builder.setTitle("삭제 확인 대화 상자")        // 제목 설정
                    .setMessage("정말 삭제하시겠습니까?")        // 메세지 설정
                    .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        // 확인 버튼 클릭시 설정

                        public void onClick(DialogInterface dialog, int whichButton) {
                            RequestParams params = new RequestParams();
                            flag = true;
                            params.put("Email", UserBean.Email);
                            params.put("CateName", CategoryBean.CateName);
                            params.put("ProjectName", ProjectBean.ProjectName);
                            params.put("ToDoName", m_Adapter.getItem(temp_idx));

                            ToDoManager.getInstnace().ToDodelete_sendData(Login_Main.getContext(), params, new ToDoManager.OnResultListener<Boolean>() {
                                @Override
                                public void onSuccess(Boolean result) {
                                    Toast.makeText(ToDoWizard_Delete.this, "성공적", Toast.LENGTH_SHORT).show();
                                    Intent intent;
                                    intent = new Intent(ToDoWizard_Delete.this,ProjectTimeline.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFail(int code) {
                                    Toast.makeText(ToDoWizard_Delete.this, "실패함", Toast.LENGTH_SHORT).show();
                                }
                            });
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
                Log.i("LogTag", ToDoState);
                Log.i("LogTag : test ", ToDoName + "," + ToDoStartDate + "," + ToDoEndDate);
                listitem.add(new ListItem_ToDoList(ToDoName, ToDoStartDate, ToDoEndDate, ToDoState));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void makelist()
    {
        //Address = "http://192.168.0.37:8080/DBProject/todo_list";
        //Address = "http://117.16.198.171:8080/DBProject/todo_list";
        URL = AddressBean.Address + "/todo_list" + "?Email="+ UserBean.Email + "&CateName=" + CategoryBean.CateName + "&ProjectName=" + ProjectBean.ProjectName;

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
