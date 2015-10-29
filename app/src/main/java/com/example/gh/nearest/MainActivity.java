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
    private String[] fieldName = new String[34];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFriendDbHelper();
    }

    public void createFriendDbHelper(){
        // 建立自訂的 FriendDbHelper 物件
        FriendDbHelper friDbHp = new FriendDbHelper(
                getApplicationContext(), DB_FILE,
                null, 1);

        // 設定建立 table 的指令
        friDbHp.sCreateTableCommand = "CREATE TABLE " + DB_TABLE + "(" +
                "_id INTEGER PRIMARY KEY," +
                "sid TEXT NOT NULL," +
                "name TEXT NOT NULL," +
                "zone TEXT," +
                "transcribe TEXT," +
                "descript TEXT," +
                "tel TEXT," +
                "addr TEXT," +
                "zipcode TEXT," +
                "travellinginfo TEXT," +
                "onetime TEXT," +
                "picture TEXT," +
                "picdescribe TEXT," +
                "picture2 TEXT," +
                "picdescribe2 TEXT," +
                "picture3 TEXT," +
                "picdescribe3 TEXT," +
                "map TEXT," +
                "gov TEXT," +
                "px TEXT," +
                "py TEXT," +
                "orgclass TEXT," +
                "class1 TEXT," +
                "class2 TEXT," +
                "class3 TEXT," +
                "levelstr TEXT," +
                "website TEXT," +
                "parkinginfo_px TEXT," +
                "parkinginfo_py TEXT," +
                "ticketinfo TEXT," +
                "remarks TEXT," +
                "keyword TEXT," +
                "region TEXT," +
                "town TEXT);";

        // 取得上面指定的檔名資料庫，如果該檔名不存在就會自動建立一個資料庫檔案
        mFriDbRW = friDbHp.getWritableDatabase();
        new AsyncTaskParseJson().execute();
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
        Intent intRecommend = new Intent(MainActivity.this,SearchActivity.class);
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
                dataJsonArr = json.getJSONArray("products");
                //dataJsonArr.length()
                for (int i = 0; i < 10; i++) {
                    JSONObject c = dataJsonArr.getJSONObject(i);
                    fieldName[0] = c.getString("id");             //id
                    fieldName[1] = c.getString("name");           //name
                    //fieldName[2] = c.getString("zone");           //zone
                    //fieldName[3] = c.getString("transcribe");     //transcribe
                    //fieldName[4] = c.getString("descript");       //descript
                    fieldName[5] = c.getString("tel");            //tel
                    fieldName[6] = c.getString("addr");           //addr
                    //fieldName[7] = c.getString("zipcode");        //zipcode
                    //fieldName[8] = c.getString("travellinginfo"); //travellinginfo
                    fieldName[9] = c.getString("opentime");        //onetime
                    fieldName[10] = c.getString("picture");       //picture
                    fieldName[11] = c.getString("picdescribe");   //picdescribe
                    //fieldName[12] = c.getString("picture2");      //picture2
                    //fieldName[13] = c.getString("picdescribe2");  //picdescribe2
                    //fieldName[14] = c.getString("picture3");      //picture3
                    //fieldName[15] = c.getString("picdescribe3");  //picdescribe3
                    //fieldName[16] = c.getString("map");           //map
                    //fieldName[17] = c.getString("gov");           //gov
                    fieldName[18] = c.getString("px");            //px
                    fieldName[19] = c.getString("py");            //py
                    //fieldName[20] = c.getString("orgclass");      //orgclass
                    //fieldName[21] = c.getString("class1");        //class1
                    //fieldName[22] = c.getString("class2");        //class2
                    //fieldName[23] = c.getString("class3");        //class3
                    //fieldName[24] = c.getString("levelstr");      //levelstr
                    fieldName[25] = c.getString("website");       //website
                    //fieldName[26] = c.getString("parkinginfo_px");//parkinginfo_px
                    //fieldName[27] = c.getString("parkinginfo_py");//parkinginfo_py
                    fieldName[28] = c.getString("ticketinfo");    //ticketinfo
                    //fieldName[29] = c.getString("remarks");       //remarks
                    //fieldName[30] = c.getString("keyword");       //keyword
                    fieldName[31] = c.getString("region");        //region
                    fieldName[32] = c.getString("town");          //town
                    //Log.e("asdfasdf", "1");
                    //String username = c.getString("username");
                    insertDbData(fieldName);
                    // show the values in our logcat


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

    public void insertDbData(String[] field){
        ContentValues newRow = new ContentValues();

        newRow.put("sid",field[0]);
        newRow.put("name",field[1]);
        //newRow.put("zone",field[2]);
        //newRow.put("transcribe",field[3]);
        //newRow.put("descript",field[4]);
        newRow.put("tel",field[5]);
        newRow.put("addr",field[6]);
        //newRow.put("zipcode",field[7]);
        //newRow.put("travellinginfo",field[8]);
        newRow.put("onetime",field[9]);
        newRow.put("picture",field[10]);
        newRow.put("picdescribe",field[11]);
        //newRow.put("picture2",field[12]);
        //newRow.put("picdescribe2",field[13]);
        //newRow.put("picture3",field[14]);
        //newRow.put("picdescribe3",field[15]);
        //newRow.put("map",field[16]);
        //newRow.put("gov",field[17]);
        newRow.put("px",field[18]);
        newRow.put("py",field[19]);
        //newRow.put("orgclass",field[20]);
        //newRow.put("class1",field[21]);
        //newRow.put("class2",field[22]);
        //newRow.put("class3",field[23]);
        //newRow.put("levelstr",field[24]);
        newRow.put("website",field[25]);
        //newRow.put("parkinginfo_px",field[26]);
        //newRow.put("parkinginfo_py",field[27]);
        newRow.put("ticketinfo",field[28]);
        //newRow.put("remarks",field[29]);
        //newRow.put("keyword",field[30]);
        newRow.put("region",field[31]);
        newRow.put("town",field[32]);
        Log.e("asdfasdf", field[0]);
        mFriDbRW.insert(DB_TABLE, null, newRow);
    }
}
