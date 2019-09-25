package com.seproject.reservemac.ui.login;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


public  class LoginUserClass extends  AsyncTask<String, Void,String>{

    private Context context;


    //flag 0 means get and 1 means post.(By default it is get.)
    public LoginUserClass(Context context) {

        this.context = context;
    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            Toast.makeText(context,"inside try",Toast.LENGTH_LONG).show();
            String username = arg0[0];
            String password = arg0[1];

            String link="http://zenithwrites.org/login.php";
            String data  = URLEncoder.encode("username", "UTF-8") + "=" +
                    URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                    URLEncoder.encode(password, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;
            Toast.makeText(context,reader.readLine(),Toast.LENGTH_LONG).show();
            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }
            Toast.makeText(context,line.toString(),Toast.LENGTH_LONG).show();
            return sb.toString();
        } catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }

    }

    protected void onPreExecute(){
    }



    @Override
    protected void onPostExecute(String result){

    }
}




