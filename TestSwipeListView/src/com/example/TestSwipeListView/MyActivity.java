package com.example.TestSwipeListView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.TestSwipeListView.swipelistview.SwipeMenu;
import com.example.TestSwipeListView.swipelistview.SwipeMenuItem;
import com.example.TestSwipeListView.swipelistview.SwipeMenuListView;
import com.example.TestSwipeListView.swipelistview.SwipeMenuView;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {
	private SwipeMenuListView listview;
	private Context context;
	private ManualListAdapter listAdapter;

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

		listAdapter = new ManualListAdapter(this, R.layout
				.manual_listview_item, list);
		listview.setAdapter(listAdapter);

		// step 2. listener item click event
		listview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(int position, SwipeMenuView parent, SwipeMenu
					menu, int index) {

				LinearLayout image = (LinearLayout) parent.findViewById(50000);
				image.setVisibility(View.VISIBLE);
				return false;
			}
		});

		// set SwipeListener

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
