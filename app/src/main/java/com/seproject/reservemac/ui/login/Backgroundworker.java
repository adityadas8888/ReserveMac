package com.seproject.reservemac.ui.login;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.HttpGet;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.protocol.HTTP;

public class Backgroundworker extends AsyncTask<String,Void,String> {
    private static final String TAG = "MyActivity";
    Context context;
    AlertDialog alertDialog;
    JSONObject jsonObj;
    String url="http://zenithwrites.org/login.php?";

    Backgroundworker (Context ctx){
        context = ctx;
    }
//    @Override
//    protected String doInBackground(String... params) {
//        String type = params[0];
//        String link="http://zenithwrites.org/login.php?";
//        if(type.equals("login")){
//
//            try {
//                Log.i(TAG, "inside try");
//                URL  url = new URL(link);
//                String username = params[1];
//                String password = params[2];
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setRequestMethod("GET");
//                httpURLConnection.setDoOutput(true);
//                httpURLConnection.setDoInput(true);
//                OutputStream outputStream = httpURLConnection.getOutputStream();
//                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
////                String post_data = URLEncoder.encode("username")+"="+URLEncoder.encode(username)+"&"
////                        +URLEncoder.encode("password")
////                        +"="+URLEncoder.encode(password);
//                String post_data ="username"+"="+username+"&"+"password"+"="+password;
//                Log.e(TAG, "post data");
//                bufferedWriter.write(post_data);
//                Log.i(TAG, "post data"+post_data);
//                bufferedWriter.flush();
//                bufferedWriter.close();
//                outputStream.close();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
//                String result = "";
//                String line="";
//
//                while ((line = bufferedReader.readLine())!=null){
//                    result+=line;
//                }
//                bufferedReader.close();
//                inputStream.close();
//                httpURLConnection.disconnect();
//
//                return result;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return null;
//    }

    @Override
    protected String doInBackground(String... strings) {
        HttpClient httpClient = new DefaultHttpClient();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String username = strings[1];
            String password = strings[2];
            String post_data =url+"username"+"="+username+"&"+"password"+"="+password;
            StringEntity se = new StringEntity(post_data.toString(), HTTP.UTF_8);
            se.setContentType("text/xml");
            HttpGet httpGet = new HttpGet(url.toString());
            httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpGet.setHeader("Content-Type", "application/json");
//            if (HasHeader) {
//                httpPost.setHeader("Authorization", "Bearer " + Token);
//            }
            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream inputStream = httpResponse.getEntity().getContent();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String bufferedStrChunk = null;
            while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
                stringBuilder.append(bufferedStrChunk);
            }
            try {
                jsonObj = new JSONObject(stringBuilder.toString().trim());
            } catch (Exception e) {
            }
            return stringBuilder.toString();
        } catch (ClientProtocolException cpe) {
            System.out.println("Protocol HttpResponese :" + cpe);
            stringBuilder.append("Failed to connect? ClientProtocolException").append(cpe.getMessage());
            // Toast.makeText(context, "Failed to connect? ClientProtocolException", Toast.LENGTH_SHORT).show();
            cpe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("IO Exception HttpResponse :" + ioe);
            stringBuilder.append("Failed to connect? ClientProtocolException").append(ioe.getMessage());
            //Toast.makeText(context, "Failed to connect? IO Exception", Toast.LENGTH_SHORT).show();
            ioe.printStackTrace();
        } catch (Exception ignored) {
            stringBuilder.append("Failed to connect?").append(ignored.getMessage());
            //Toast.makeText(context, "Failed to connect? Exception", Toast.LENGTH_SHORT).show();
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
       alertDialog.setTitle("Login Status");

    }

    @Override
    protected void onPostExecute(String result) {
       alertDialog.setMessage(result);
       alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
