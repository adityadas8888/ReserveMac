package com.seproject.reservemac.ui.login;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class Backgroundworker extends AsyncTask<String,Void,String> {
    private static final String TAG = "MyActivity";
    Context context;
    AlertDialog alertDialog;
    JSONObject jsonObj;
//    http://mohammedmurtuzabhaiji.uta.cloud/se1project/login.php?username=mohammed&password=pass@123
    String url="http://mohammedmurtuzabhaiji.uta.cloud/se1project/login.php?";

    Backgroundworker (Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpClient httpClient = new DefaultHttpClient();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String username = strings[1];
            String password = strings[2];
            String post_data =url+"username"+"="+username+"&"+"password"+"="+password;
//            StringEntity se = new StringEntity(post_data.toString(), HTTP.UTF_8);
//            se.setContentType("text/xml");
            HttpGet httpGet = new HttpGet(post_data);
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
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
