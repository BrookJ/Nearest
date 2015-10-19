package com.example.gh.nearest;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by 101460 on 2015/5/4.
 */
public class Custom  extends ListActivity {

    private ListView listView;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        ArrayAdapter<CharSequence> arrAdapWeekday = ArrayAdapter.createFromResource(
                this, R.array.city, android.R.layout.simple_list_item_1);
        setListAdapter(arrAdapWeekday);

        ListView listview = getListView();
        listview.setOnItemClickListener(listViewOnItemClick);
    }

    private AdapterView.OnItemClickListener listViewOnItemClick = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
            // 在TextView元件中顯示使用者點選的項目名稱

        }
    };

    public void btnSentClick(View set){
        Intent intRecommend = new Intent(Custom.this,MapGuide.class);
        startActivity(intRecommend);
    }
}
