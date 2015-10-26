package com.example.gh.nearest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class AllProductsActivity extends Activity {

    private static final String DB_FILE = "friends.db",
            DB_TABLE = "friends";
    private SQLiteDatabase mFriDbRW;
    private TextView myTextView;
    public static String lll = "";
    private Button mBtnList;
    private EditText mEdtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        createFriendDbHelper();
        myTextView = (TextView)findViewById(R.id.Text2);
        mBtnList = (Button)findViewById(R.id.button22);
        mEdtList = (EditText)findViewById(R.id.editText2);
        mBtnList.setOnClickListener(onClickBtnList);

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
                "sex TEXT," +
                "address TEXT);";

        // 取得上面指定的檔名資料庫，如果該檔名不存在就會自動建立一個資料庫檔案
        mFriDbRW = friDbHp.getWritableDatabase();
    }

    public void insertDbData(String aa , String bb){
        ContentValues newRow = new ContentValues();
        newRow.put("name", aa);
        newRow.put("sex", bb);
        mFriDbRW.insert(DB_TABLE, null, newRow);
    }

    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {

        final String TAG = "AsyncTaskParseJson.java";

        // set your json string url here
        //String yourJsonStringUrl = "http://charleslin74.esy.es/contact/index.php";
        String yourJsonStringUrl = "http://gis.taiwan.net.tw/XMLReleaseALL_public/scenic_spot_C_f.json";
        //String yourJsonStringUrl = "http://140.131.114.167/~travel/20151020test.php";

        // contacts JSONArray
        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {}

        @Override
        protected String doInBackground(String... arg0) {

            try {

                // instantiate our json parser
                JSONParser jParser = new JSONParser();
                Log.e("asdfasdf","95");
                // get json string from url
                JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl);
                Log.e("asdfasdf","98");
                // get the array of users
                //dataJsonArr = json.getJSONArray("KHtravel");
                dataJsonArr = json.getJSONArray("Infos");
                //dataJsonArr = json.getJSONArray("products");
                Log.e("asdfasdf","102");
                // loop through all users
                //for (int i = 0; i < dataJsonArr.length(); i++) {
                for (int i = 0; i < 10; i++) {
                    JSONObject c = dataJsonArr.getJSONObject(i);

                    // Storing each json item in variable
                    String id = c.getString("pid");
                    String name = c.getString("name");
                    //String username = c.getString("username");
                    insertDbData(id , name);
                    // show the values in our logcat
                    Log.e(TAG, "id: " + id
                            + ", name: " + name);

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

    private Button.OnClickListener onClickBtnList = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Cursor c = mFriDbRW.query(true, DB_TABLE, new String[]{"name", "sex"},
                    null, null, null, null, null, null);

            if (c == null)
                return;

            if (c.getCount() == 0) {
                mEdtList.setText("");
                Toast.makeText(AllProductsActivity.this, "1234", Toast.LENGTH_LONG)
                        .show();
            }
            else {
                c.moveToFirst();
                mEdtList.setText(c.getString(0) + c.getString(1)  );

                while (c.moveToNext())
                    mEdtList.append("\n" + c.getString(0) + c.getString(1)  );
            }
        }
    };
}