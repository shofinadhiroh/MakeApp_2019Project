package com.example.bismillah;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//Key값에 따라 Info 가지고 오기

public class HttpConnector extends AsyncTask<String, Void, String> {

    String clientKey = null;
    private String str, receiveMsg;
    private String url_string = "";

    @Override
    protected String doInBackground(String... params) {
        URL url = null;

        try {

            int n = params.length;

            for (int i = 0; i < n; i++) {
                if (params[i] != "") {
                    if (i == 1) url_string += "?";
                    if (i > 1) url_string += "&";
                    url_string += params[i];
                }
            }

            url = new URL(url_string);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("x-waple-authorization", clientKey);

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.i("receiveMsg : ", receiveMsg);

                reader.close();
            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }


}
