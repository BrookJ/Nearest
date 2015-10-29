package com.example.gh.nearest;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class SearchActivity extends ListActivity {

    private static final String DB_FILE = "friends.db",
            DB_TABLE = "friends";
    private SQLiteDatabase mFriDbRW;
    private TextView mTxtResult;
    private String[] listName = new String[11];
    List<Map<String, Object>> mList;
    int dataQuantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mTxtResult = (TextView)findViewById(R.id.txtResult);

        createFriendDbHelper();
        getFriendDbData();
        //setupViewComponent();


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

    public void getFriendDbData(){

        Cursor c = mFriDbRW.query(true, DB_TABLE, new String[]{"sid", "name"},
                null, null, null, null, null, null);

        c.moveToFirst();
        listName[dataQuantity]=c.getString(0);

        while (c.moveToNext()) {
            dataQuantity = dataQuantity + 1;
            listName[dataQuantity] = c.getString(1);
            Log.e("asdfasdf", listName[dataQuantity]);
        }

        //listview();
    }
/*
    public void listview(){
        ListView listview = getListView();
        listview.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listName));
        listview.setTextFilterEnabled(true);
    }
*/


    public void createFriendDbHelper(){
        // 建立自訂的 FriendDbHelper 物件
        FriendDbHelper friDbHp = new FriendDbHelper(
                getApplicationContext(), DB_FILE,
                null, 1);
        mFriDbRW = friDbHp.getWritableDatabase();
    }
/*
    private void setupViewComponent() {

        //ArrayAdapter<CharSequence> adapWeekday = ArrayAdapter.createFromResource(
        //        this, R.array.city, android.R.layout.simple_list_item_1);
        //ArrayAdapter adapWeekday = new ArrayAdapter();
        //adapWeekday.addAll(listName);
        setListAdapter(adapWeekday);

        ListView listview = getListView();
        listview.setTextFilterEnabled(true);

        listview.setOnItemClickListener(listviewOnItemClkLis);
    }

    AdapterView.OnItemClickListener listviewOnItemClkLis = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
            // TODO Auto-generated method stub
            mTxtResult.setText(((TextView) view).getText());
        }
    };
*/
}