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
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.protocol.HTTP;

public class Backgroundworker extends AsyncTask<String, Void, String> {
    private static final String TAG = "MyActivity";
    Context context;
    AlertDialog alertDialog;
    JSONObject jsonObj;

    String url, Identity;
    public AsyncResponse delegate = null;


    //    http://mohammedmurtuzabhaiji.uta.cloud/se1project/login.php?username=mohammed&password=pass@123
    public static final String BASE_URL = "http://mohammedmurtuzabhaiji.uta.cloud/se1project/";

    public Backgroundworker(Context ctx) {
        this.context = ctx;
    }


    public interface AsyncResponse {
        void ProcessFinish(String output, JSONObject jsonObject, String Identity);
    }

    public Backgroundworker(AsyncResponse delegate, String url, Context context, String Identity) {
        this.delegate = delegate;
        this.url = url;
        this.context = context;
        this.Identity = Identity;

    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");

    }

    @Override
    protected String doInBackground(String... strings) {
        HttpClient httpClient = new DefaultHttpClient();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String post_data = BASE_URL.concat(url);
            StringEntity se = new StringEntity(post_data.toString(), HTTP.UTF_8);
            se.setContentType("text/xml");
            HttpGet httpGet = new HttpGet(post_data);
            httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpGet.setHeader("Content-Type", "application/json");

            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream inputStream = httpResponse.getEntity().getContent();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String bufferedStrChunk = null;
            while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
                stringBuilder.append(bufferedStrChunk);
            }
            try {
                String jsonString = stringBuilder.toString().replace("Array", "");

//                jsonObj = new JSONObject();
                jsonObj = new JSONObject(jsonString);
//                jsonObj.getJSONObject(jsonString);
            } catch (Exception e) {
                Toast.makeText(context, "Json Error Occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);

        alertDialog.show();


        super.onPostExecute(result);
        try {
//            JSONObject json = new JSONObject();
//            json.getJSONArray(result);
            delegate.ProcessFinish(result, jsonObj, Identity);
        } catch (Exception e) {
            Toast.makeText(context, "error Occurred" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
