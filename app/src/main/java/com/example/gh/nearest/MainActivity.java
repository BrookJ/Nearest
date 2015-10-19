package com.example.gh.nearest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    private static final String DB_FILE = "friends.db",
                                DB_TABLE = "friends";
    private SQLiteDatabase mFriDbRW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
/*
    public void insertDbData(){
        ContentValues newRow = new ContentValues();
        newRow.put("name", mEdtName.getText().toString());
        newRow.put("sex", mEdtSex.getText().toString());
        newRow.put("address", mEdtAddr.getText().toString());
        mFriDbRW.insert(DB_TABLE, null, newRow);
    }
*/
    public void RecommendClick(View rec){
       Intent intRecommend = new Intent(MainActivity.this,Recommend.class);
       startActivity(intRecommend);
    }

    public void CustomClick(View cus){
        Intent intRecommend = new Intent(MainActivity.this,Custom.class);
        startActivity(intRecommend);
    }

    public void SearchClick(View cus){
        Intent intRecommend = new Intent(MainActivity.this,AllProductsActivity.class);
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
}
