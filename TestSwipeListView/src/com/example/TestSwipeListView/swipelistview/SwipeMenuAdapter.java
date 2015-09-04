package com.example.TestSwipeListView.swipelistview;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.TestSwipeListView.MyActivity;
import com.example.TestSwipeListView.R;

public class SwipeMenuAdapter extends BaseAdapter implements
		SwipeMenuView.OnSwipeItemClickListener {

	private ListAdapter mAdapter;
	private Context mContext;
	private SwipeMenuListView.OnMenuItemClickListener onMenuItemClickListener;

	public SwipeMenuAdapter(Context context, ListAdapter adapter) {
		mAdapter = adapter;
		mContext = context;
	}

	@Override
	public int getCount() {
		return mAdapter.getCount();
	}

	@Override
	public Object getItem(int position) {
		return mAdapter.getItem(position);
	}

	@Override
	public long getItemId(int position) {
		return mAdapter.getItemId(position);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		SwipeMenuLayout layout = null;
		ViewHolder holder = null;
		if (convertView == null) {
			View contentView = mAdapter.getView(position, convertView, parent);
			SwipeMenu menu = new SwipeMenu(mContext);
			menu.setViewType(mAdapter.getItemViewType(position));
			createMenu(menu);//invoke createMenu callback
			SwipeMenuView menuView = new SwipeMenuView(menu,
					(SwipeMenuListView) parent);
			menuView.setOnSwipeItemClickListener(this);
			SwipeMenuListView listView = (SwipeMenuListView) parent;
			layout = new SwipeMenuLayout(contentView, menuView,
					listView.getCloseInterpolator(),
					listView.getOpenInterpolator());
			layout.setPosition(position);
			layout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					SwipeMenuLayout menuLayout = (SwipeMenuLayout) v;
					if (menuLayout.isOpen()) {
						menuLayout.closeMenu();
						if(menuLayout.getChildAt(1) instanceof SwipeMenuView){
							SwipeMenuView menuView = (SwipeMenuView) menuLayout
									.getChildAt(1);
							LinearLayout imageLayout = (LinearLayout) menuView
									.getChildAt(0);
							ImageView image =(ImageView)imageLayout.getChildAt(0);
							image.setImageResource(R.drawable.ic_list_download);

						}
					} else {
						SwipeMenuListView listview = (SwipeMenuListView) v.getParent();
						listview.smoothOpenMenu(position);
					}
					SwipeMenu menu = new SwipeMenu(mContext);
					menu.setViewType(mAdapter.getItemViewType(position));
				}
			});
		} else {
			layout = (SwipeMenuLayout) convertView;
			layout.closeMenu();
			layout.setPosition(position);
		}
		return layout;
	}

	public void createMenu(SwipeMenu menu) {
		SwipeMenuItem download = new SwipeMenuItem(mContext);
		download.setId(30000);
		download.setBackground(new ColorDrawable(Color.rgb(0xF3, 0xF3, 0xF3)));
		download.setWidth(MyActivity.dp2px(mContext, 90));
		download.setIcon(R.drawable.ic_list_trash);
		download.setIsVisiable(false);
		menu.addMenuItem(download);

		SwipeMenuItem view = new SwipeMenuItem(mContext);
		view.setId(40000);
		view.setBackground(new ColorDrawable(Color.rgb(0xE5, 0xF5, 0xFF)));
		view.setWidth(MyActivity.dp2px(mContext, 90));
		view.setIcon(R.drawable.ic_list_view);
		view.setIsVisiable(true);
		menu.addMenuItem(view);
	}

	@Override
	public void onItemClick(SwipeMenuView view, SwipeMenu menu, int index) {
		if (onMenuItemClickListener != null) {
			onMenuItemClickListener.onMenuItemClick(view.getPosition(), view, menu,
					index);
		}
	}

	public void setOnMenuItemClickListener(
			SwipeMenuListView.OnMenuItemClickListener onMenuItemClickListener) {
		this.onMenuItemClickListener = onMenuItemClickListener;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		mAdapter.registerDataSetObserver(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		mAdapter.unregisterDataSetObserver(observer);
	}

	@Override
	public boolean areAllItemsEnabled() {
		return mAdapter.areAllItemsEnabled();
	}

	@Override
	public boolean isEnabled(int position) {
		return mAdapter.isEnabled(position);
	}

	@Override
	public boolean hasStableIds() {
		return mAdapter.hasStableIds();
	}

	@Override
	public int getItemViewType(int position) {
		return mAdapter.getItemViewType(position);
	}

	@Override
	public int getViewTypeCount() {
		return mAdapter.getViewTypeCount();
	}

	@Override
	public boolean isEmpty() {
		return mAdapter.isEmpty();
	}

	private static class ViewHolder{
		TextView text;
		ImageView downlload_button;
		ImageView view_button;
	}

}
