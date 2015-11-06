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

public class ProjectManager {
    private static ProjectManager instance_Project;
    public static ProjectManager getInstnace() {
        if (instance_Project == null) {
            instance_Project = new ProjectManager();
        }
        return instance_Project;
    }

    public AsyncHttpClient client;
    private ProjectManager() {

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
    //String Address = "http://192.168.0.51:8080/DBProject";
    //String Address = "http://192.168.0.37:8080/DBProject";
    public HttpClient getHttpClient() {
        return client.getHttpClient();
    }

    public interface OnResultListener<T> {
        public void onSuccess(T result);
        public void onFail(int code);
    }


    public void Projectchange_sendData(Context context,RequestParams params,final OnResultListener<Boolean> listener){
        String SERVER  = AddressBean.Address + "/project_change";

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

    public void Projectcreate_sendData(Context context,RequestParams params,final OnResultListener<Boolean> listener){
        String SERVER  = AddressBean.Address + "/project_create";

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

    public void Projectdelete_sendData(Context context,RequestParams params,final OnResultListener<Boolean> listener){
        String SERVER  = AddressBean.Address + "/project_delete";

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