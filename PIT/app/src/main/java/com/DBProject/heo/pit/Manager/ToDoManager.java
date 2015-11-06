package com.DBProject.heo.pit.Manager;

/**
 * Created by Heo on 15. 9. 21..
 */

import android.content.Context;

import com.DBProject.heo.pit.InfoClass.AddressBean;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class ToDoManager {
    private static ToDoManager instance_Todo;
    public static ToDoManager getInstnace() {
        if (instance_Todo == null) {
            instance_Todo = new ToDoManager();
        }
        return instance_Todo;
    }

    public AsyncHttpClient client;
    private ToDoManager() {

        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            MySSLSocketFactory socketFactory = new MySSLSocketFactory(trustStore);
            socketFactory.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            client = new AsyncHttpClient();
            client.setSSLSocketFactory(socketFactory);
            client.setCookieStore(new PersistentCookieStore(Login_Main.getContext()));
            client.setTimeout(30000);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }


    }
    //String Address = "http://192.168.0.37:8080/DBProject";
    //String Address = "http://192.168.0.51:8080/DBProject";
    public HttpClient getHttpClient() {
        return client.getHttpClient();
    }

    public interface OnResultListener<T> {
        public void onSuccess(T result);
        public void onFail(int code);
    }

    /*public void ToDolist_sendData(Context context,RequestParams params ,final OnResultListener<Vector> listener){
        String SERVER = "http://210.118.74.191:4389/TIP_server/todo_list";

        client.post(context, SERVER, params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  String responseString) {
                Gson gson = new Gson();
                Vector result = gson.fromJson(responseString, Vector.class);
                listener.onSuccess(result);
            }
        });
    }

    public void ToDoitem_sendData(Context context,RequestParams params,final OnResultListener<ToDoBean> listener){
        String SERVER = "http://210.118.74.191:4389/TIP_server/todo_item";

        client.post(context, SERVER, params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  String responseString) {
                Gson gson = new Gson();
                ToDoBean result = gson.fromJson(responseString,ToDoBean.class);
                listener.onSuccess(result);
            }

        });
    }*/
    public void ToDochange_sendData(Context context,RequestParams params,final OnResultListener<Boolean> listener){
        String SERVER  = AddressBean.Address + "/todo_change";

        client.post(context, SERVER, params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  String responseString) {
                Gson gson = new Gson();
                boolean result = gson.fromJson(responseString,boolean.class);
                listener.onSuccess(result);
            }
        });
    }

    public void ToDocreate_sendData(Context context,RequestParams params,final OnResultListener<Boolean> listener){
        String SERVER  = AddressBean.Address + "/todo_create";

        client.post(context, SERVER, params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  String responseString) {
                Gson gson = new Gson();
                boolean result = gson.fromJson(responseString,boolean.class);
                listener.onSuccess(result);
            }
        });
    }

    public void ToDodelete_sendData(Context context,RequestParams params,final OnResultListener<Boolean> listener){
        String SERVER  = AddressBean.Address + "/todo_delete";

        client.post(context, SERVER, params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseString, Throwable throwable) {
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  String responseString) {
                Gson gson = new Gson();
                boolean result = gson.fromJson(responseString,boolean.class);
                listener.onSuccess(result);
            }
        });
    }

}