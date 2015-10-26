package com.example.gh.nearest;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 0935040741
 */
public class MainActivity extends ActionBarActivity {

    private static final String DB_FILE = "friends.db",
                                DB_TABLE = "friends";
    private SQLiteDatabase mFriDbRW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFriendDbHelper();
        new AsyncTaskParseJson().execute();
    }

    public void createFriendDbHelper(){
        // 建立自訂的 FriendDbHelper 物件
        FriendDbHelper friDbHp = new FriendDbHelper(
                getApplicationContext(), DB_FILE,
                null, 1);

        // 設定建立 table 的指令
        friDbHp.sCreateTableCommand = "CREATE TABLE " + DB_TABLE + "(" +
                "_id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "px TEXT," +
                "py TEXT);";

        // 取得上面指定的檔名資料庫，如果該檔名不存在就會自動建立一個資料庫檔案
        mFriDbRW = friDbHp.getWritableDatabase();
    }

    public void RecommendClick(View rec){
       Intent intRecommend = new Intent(MainActivity.this,Recommend.class);
       startActivity(intRecommend);
    }

    public void CustomClick(View cus){
        Intent intRecommend = new Intent(MainActivity.this,Custom.class);
        startActivity(intRecommend);
    }

    public void SearchClick(View cus){
        Intent intRecommend = new Intent(MainActivity.this,SearchSites.class);
        startActivity(intRecommend);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {

        final String TAG = "AsyncTaskParseJson.java";

        // set your json string url here
        //String yourJsonStringUrl = "http://charleslin74.esy.es/contact/index.php";
        String yourJsonStringUrl = "http://140.131.114.167/~travel/20151023test.php";

        // contacts JSONArray
        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {}

        @Override
        protected String doInBackground(String... arg0) {

            try {

                // instantiate our json parser
                JSONParser jParser = new JSONParser();

                // get json string from url
                JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl);

                // get the array of users
                //dataJsonArr = json.getJSONArray("KHtravel");
                dataJsonArr = json.getJSONArray("products");

                // loop through all users
                for (int i = 0; i < dataJsonArr.length(); i++) {
                //for (int i = 0; i < 100; i++) {
                    JSONObject c = dataJsonArr.getJSONObject(i);

                    // Storing each json item in variable
                    String id = c.getString("id");
                    String name = c.getString("name");
                    String px = c.getString("px");
                    String py = c.getString("py");
                    //String username = c.getString("username");
                    insertDbData(id , name , px , py);
                    // show the values in our logcat
                    Log.e(TAG, "id: " + i
                            + ", name: " + name
                            + ", px: " + px
                            + ", py: " + py);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String strFromDoInBg) {

        }
    }

    public void insertDbData(String aa , String bb, String cc, String dd){
        ContentValues newRow = new ContentValues();
        newRow.put("id", aa);
        newRow.put("name", bb);
        newRow.put("px", cc);
        newRow.put("py", dd);
        mFriDbRW.insert(DB_TABLE, null, newRow);
    }
}
