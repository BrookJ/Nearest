package com.example.gh.nearest;

import android.app.ListActivity;
import android.content.Entity;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

/**
 * Created by how on 2015/9/14.
 */
public class TEST extends ListActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
    }

    private DefaultHttpClient getHttpClient(){
        HttpParams httpparam = new BasicHttpParams();
        int timeoutConnection = 5000;
        HttpConnectionParams.setConnectionTimeout(httpparam, timeoutConnection);
        int timeoutSocket = 3000;
        HttpConnectionParams.setSoTimeout(httpparam, timeoutSocket);
        DefaultHttpClient defaultHC = new DefaultHttpClient(httpparam);
        return defaultHC;
    }

    //private JSONArray getJsonData(){
    //    String url = "http://gis.taiwan.net.tw/XMLReleaseALL_public/scenic_spot_C_f.json";
    //    HttpGet httpget = new HttpGet(url);
    //    try{
    //        //HttpResponse httpresponse = getHttpClient().excute(httpget);
    //        //httpresponse.getEntity().toString();
    //        //String result = EntityUtils.toString(httpresponse.getEntity());
    //        //Log.e("result", result);
    //        //JSONArray jsonarr = new JSONArray(result);
    //        //return jsonarr;
    //    }catch(Exception arr){
    //        return null;
    //    }
    //
    //}
}
