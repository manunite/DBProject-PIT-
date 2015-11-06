package com.DBProject.heo.pit.Manager;

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

public class adminLogin_Manager {
    private static adminLogin_Manager instance;
    public static adminLogin_Manager getInstnace() {
        if (instance == null) {
            instance = new adminLogin_Manager();
        }
        return instance;
    }

    public AsyncHttpClient client;
    //String Address = "http://192.168.0.37:8080";
    //String Address = "http://117.16.198.171:8080";
    //String Address = "http://117.16.198.249:8080";
    //String Address = "http://192.168.0.51:8080";

    private adminLogin_Manager() {

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



    public void adminLogin_sendData(Context context,String S_Ema,String S_Pass, final OnResultListener<Boolean> listener){
        String SERVER = AddressBean.Address + "/adminlogin";
        RequestParams params = new RequestParams();
        params.put("S_Email", S_Ema);
        params.put("S_PassWord", S_Pass);
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
                boolean result = gson.fromJson(responseString, boolean.class);
                listener.onSuccess(result);
            }

        });
    }

}
