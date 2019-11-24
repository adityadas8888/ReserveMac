package com.seproject.reservemac.background;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.protocol.HTTP;

public class PostRequests extends AsyncTask<String, Void, String> {

    public static final String BASE_URL = "http://mohammedmurtuzabhaiji.uta.cloud/se1project/";

    Context context;
    AlertDialog alertDialog;
    SweetAlertDialog dialog;
    JSONObject jsonObject;
    List<NameValuePair> pairs;

    String url, Identity;
    public PostAsyncResponse delegate = null;


    public PostRequests(Context ctx) {
        this.context = ctx;
    }

    public PostRequests(PostAsyncResponse delegate, String url, Context context, String Identity, List<NameValuePair> pairs) {
        this.delegate = delegate;
        this.url = url;
        this.context = context;
        this.Identity = Identity;
        this.pairs = pairs;

    }

    public interface PostAsyncResponse {
        void ProcessFinish(String output, JSONObject jsonObject, String Identity);
    }


    @Override
    protected void onPreExecute() {
        dialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setTitleText("Loading");
        dialog.setCancelable(false);
        dialog.show();alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
    }


    @Override
    protected String doInBackground(String... strings) {

        InputStream inputStream = null;
        String result;

        try {
            HttpClient httpclient = new DefaultHttpClient();
            String post_data = BASE_URL.concat(url);
            StringEntity stringEntity = new StringEntity(post_data.toString(), HTTP.UTF_8);
            stringEntity.setContentType("text/xml");

            HttpPost httpPost = new HttpPost(post_data);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

//            httpPost
//            JSONObject jsonObject = new JSONObject();
//            JSONArray jsonArray = new JSONArray();
//            for (String s : imageArray) {
//                JSONObject jsonO = new JSONObject();
//                jsonO.accumulate("imagestring", s);
//                jsonArray.put(jsonO);
//            }

//            jsonObject.accumulate("AppSource", "MT");
//            jsonObject.accumulate("Status", "0");
//            jsonObject.accumulate("prelist", jsonArray.toString());
//            jsonObject.toJSONArray(jsonArray);

            httpPost.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));

            HttpResponse httpResponse = httpclient.execute(httpPost);
            inputStream = httpResponse.getEntity().getContent();

            if (inputStream != null) {
                result = convertInputStreamToString(inputStream);
            } else
                result = "Did not work!";
        } catch (Exception e) {
//            dialog.dismiss();
            result = e.getMessage();
            Log.d("InputStream", e.getLocalizedMessage());
        }


        return result;
    }


    @Override
    protected void onPostExecute(String result) {
//        alertDialog.setMessage(result);
        dialog.dismissWithAnimation();

        super.onPostExecute(result);
        try {
            JSONObject json = new JSONObject(result);

            delegate.ProcessFinish(result, json, Identity);
        } catch (Exception e) {
            Toast.makeText(context, "error Occurred" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null)
            result.append(line);

        inputStream.close();
        return result.toString().replace("Array", "");

    }

}


