package com.example.TestSwipeListView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import com.example.TestSwipeListView.swipelistview.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {
    private SwipeMenuListView listview;
    private Context context;
    private SwipeMenuAdapter listAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;
        listview = (SwipeMenuListView) findViewById(R.id.manual_list);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            list.add("" + i);
        }

//        listAdapter = new ManualListAdapter(this, R.layout
//                .manual_listview_item, list);
        List<SwipeMenuItem> items = new ArrayList<SwipeMenuItem>();
        SwipeMenuItem download = new SwipeMenuItem(context);
        download.setBackground(new ColorDrawable(Color.rgb(0xF3, 0xF3, 0xF3)));
        download.setWidth(dp2px(context, 90));
        download.setIcon(R.drawable.ic_list_trash);
        items.add(download);


        SwipeMenuItem view = new SwipeMenuItem(context);
        view.setBackground(new ColorDrawable(Color.rgb(0xE5, 0xF5, 0xFF)));
        view.setWidth(dp2px(context, 90));
        view.setIcon(R.drawable.ic_list_view);
        items.add(view);

        listAdapter = new SwipeMenuAdapter(this,items);
        listview.setAdapter(listAdapter);
//        SwipeMenuCreator creator = new SwipeMenuCreator() {
//
//            @Override
//            public void create(SwipeMenu menu) {
//                /**
//                 * menu.getViewType
//                 * 0 : not downloaded
//                 * 1 : downloaded
//                 */
//
//                SwipeMenuItem download = new SwipeMenuItem(context);
//                download.setBackground(new ColorDrawable(Color.rgb(0xF3, 0xF3, 0xF3)));
//                download.setWidth(dp2px(context, 90));
//                download.setIcon(R.drawable.ic_list_trash);
//                menu.addMenuItem(download);
//
//
//                SwipeMenuItem view = new SwipeMenuItem(context);
//                view.setBackground(new ColorDrawable(Color.rgb(0xE5, 0xF5, 0xFF)));
//                view.setWidth(dp2px(context, 90));
//                view.setIcon(R.drawable.ic_list_view);
//                menu.addMenuItem(view);
//            }
//        };
        // set creator
//        listview.setMenuCreator(creator);

        // step 2. listener item click event
        listview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
//                switch (index) {
//                    case 0://download button
//
//                        break;
//                    case 1://watch button
//
//                        break;
//                }
                menu.getMenuItem(index).setBackground(R.drawable.ic_list_trash);

                return false;
            }
        });

        // set SwipeListener
        listview.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listview.smoothOpenMenu(position);
            }
        });
        listview.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                listview.closeAllMenu();
            }
        });

    }

    public static int dp2px(Context mContext, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext.getResources().getDisplayMetrics());
    }
}
