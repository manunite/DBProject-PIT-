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

public class CateManager {
    private static CateManager instance;
    public static CateManager getInstnace() {
        if (instance == null) {
            instance = new CateManager();
        }
        return instance;
    }

    public AsyncHttpClient client;
    private CateManager() {

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

    public HttpClient getHttpClient() {
        return client.getHttpClient();
    }

    public interface OnResultListener<T> {
        public void onSuccess(T result);
        public void onFail(int code);
    }

    ///////////////////WIFI설정!!!
    //String Address = "http://192.168.0.37:8080/DBProject";
    //String Address = "http://117.16.198.171:8080/DBProject";
    //String Address = "http://192.168.0.51:8080/DBProject";

    /*public void list_sendData(Context context,RequestParams params ,final OnResultListener<Vector> listener){
        //String SERVER = "http://210.118.74.191:4389/TIP_server/category_list";
        String SERVER = Address + "/category_list";
        client.post(context, SERVER, params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseString, Throwable throwable) {
                Log.i("NetworkManager.setUserInfo", "fail:" + responseString);
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  String responseString) {
                Log.i("NetworkManager.setUserInfo", "success:" + "success");
                Gson gson = new Gson();
                Vector result = gson.fromJson(responseString, Vector.class);
                listener.onSuccess(result);
            }
        });
    }

    public void item_sendData(Context context,RequestParams params,final OnResultListener<CategoryBean> listener){
        //String SERVER = "http://210.118.74.191:4389/TIP_server/category_item";
        String SERVER = Address + "/category_item";

        client.post(context, SERVER, params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseString, Throwable throwable) {
                Log.i("NetworkManager.setUserInfo", "fail:" + responseString);
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  String responseString) {
                Log.i("NetworkManager.setUserInfo", "success:" + "success");
                Gson gson = new Gson();
                CategoryBean result = gson.fromJson(responseString, CategoryBean.class);
                listener.onSuccess(result);
            }

        });
    }*/

    public void create_sendData(Context context,RequestParams params,final OnResultListener<Boolean> listener){
        //String SERVER = "http://210.118.74.191:4389/TIP_server/create_category";
        String SERVER  = AddressBean.Address + "/category_create";

        client.post(context, SERVER, params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  String responseString, Throwable throwable) {
                //Log.i("NetworkManager.setUserInfo", "fail:" + responseString);
                listener.onFail(statusCode);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  String responseString) {
                //Log.i("NetworkManager.setUserInfo", "success:" + "success");
                Gson gson = new Gson();
                boolean result = gson.fromJson(responseString,boolean.class);
                listener.onSuccess(result);
            }
        });
    }

    public void delete_sendData(Context context,RequestParams params,final OnResultListener<Boolean> listener){
        //String SERVER = "http://210.118.74.191:4389/TIP_server/delete_category";
        String SERVER  = AddressBean.Address + "/category_delete";

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

    public void change_sendData(Context context,RequestParams params,final OnResultListener<Boolean> listener){
        //String SERVER = "http://210.118.74.191:4389/TIP_server/change_category";
        String SERVER  = AddressBean.Address + "/category_change";

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