package com.DBProject.heo.pit.Manager;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.DBProject.heo.pit.Activity.CategoryActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Heo on 15. 10. 12..
 */

public class getMySQL_Category extends AsyncTask<String, Integer,String> {
    public ArrayList<ListItem> listitem = new ArrayList<ListItem>();
    public ArrayList<ListItem> returnVal = new ArrayList<ListItem>();
    public int re_UserId;
    static public CategoryActivity CA = new CategoryActivity();

    // Activity context
    private Context context;

    public void setContext(Context _context)
    {
        this.context = _context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try{
                // 연결 url 설정
                URL url = new URL(urls[0]);
                // 커넥션 객체 생성
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                // 연결되었으면.
                if(conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    // 연결되었음 코드가 리턴되면.
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        for(;;){
                            // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                            String line = br.readLine();
                            if(line == null) break;
                            // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
            //Log.i("INCHEON",jsonHtml.toString());
            return jsonHtml.toString();
        }

        protected void onPostExecute(String str){
            String CateName;
            int UserId=0;
            try{
                JSONObject root = new JSONObject(str);
                JSONArray ja = root.getJSONArray("results"); //get the JSONArray which I made in the php file. the name of JSONArray is "results"

                for(int i=0;i<ja.length();i++){
                    JSONObject jo = ja.getJSONObject(i);
                    CateName = jo.getString("CateName");
                    UserId = jo.getInt("UserId");
                    listitem.add(new ListItem(CateName));
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
            returnVal = listitem;
            re_UserId = UserId;
            if(context != null)
                context.sendBroadcast(new Intent("Cate_action"));

    }
}