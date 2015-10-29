package com.example.gh.nearest;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 101460 on 2015/5/4.
 */
public class Recommend  extends ExpandableListActivity {

    private static final String ITEM_NAME = "Item Name";
    private static final String ITEM_SUBNAME = "Item Subname";



    private ExpandableListAdapter mExpaListAdap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        List<Map<String, String>> groupList = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> childList2D = new ArrayList<List<Map<String, String>>>();

        Map<String, String> group = new HashMap<String, String>();
        group.put(ITEM_NAME, "北投溫泉旅");
//        group.put(ITEM_SUBNAME, "北投溫泉博物館 -> 北投文物館 -> 新北投溫泉區 ");
        groupList.add(group);

        List<Map<String, String>> childList = new ArrayList<Map<String, String>>();
        Map<String, String> child = new HashMap<String, String>();
        child.put(ITEM_NAME, "北投溫泉博物館 ");
        child.put(ITEM_SUBNAME, "說明");
        childList.add(child);
        Map<String, String> child1 = new HashMap<String, String>();
        child1.put(ITEM_NAME, "北投文物館" );
        child1.put(ITEM_SUBNAME, "說明");
        childList.add(child1);
        Map<String, String> child2 = new HashMap<String, String>();
        child2.put(ITEM_NAME, "新北投溫泉區" );
        child2.put(ITEM_SUBNAME, "說明" );
        childList.add(child2);
        childList2D.add(childList);

        //設定ExpandableListAdapter
        mExpaListAdap = new SimpleExpandableListAdapter(
                this,
                groupList,
                android.R.layout.simple_expandable_list_item_2,
                new String[] {ITEM_NAME, ITEM_SUBNAME},
                new int[] {android.R.id.text1, android.R.id.text2},
                childList2D,
                android.R.layout.simple_expandable_list_item_2,
                new String[] {ITEM_NAME, ITEM_SUBNAME},
                new int[] {android.R.id.text1, android.R.id.text2}
        );

        setListAdapter(mExpaListAdap);

    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {
        // TODO Auto-generated method stub
        Intent intRecommend = new Intent(Recommend.this,Information.class);
        startActivity(intRecommend);

        return super.onChildClick(parent, v, groupPosition, childPosition, id);
    }

    public void btnSentClick(View set){
        Intent intRecommend = new Intent(Recommend.this,MapGuide.class);
        startActivity(intRecommend);
        Double aaa = (double) 25.047658;
        //new一個Bundle物件，並將要傳遞的資料傳入
        Bundle bundle = new Bundle();
        bundle.putDouble("height",aaa);

        //將Bundle物件assign給intent
        intRecommend.putExtras(bundle);

        //切換Activity
        startActivity(intRecommend);
    }
}
