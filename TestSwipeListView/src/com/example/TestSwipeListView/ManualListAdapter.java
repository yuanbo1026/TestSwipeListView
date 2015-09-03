package com.example.TestSwipeListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by b.yuan on 28.07.2015.
 */
public class ManualListAdapter extends ArrayAdapter<String> {

	private LayoutInflater mInflater;
	private Context mContext;
	private List<String> mStringList;
	private int mLayoutId;


	public ManualListAdapter(Context context, int layoutId, List<String> objects) {
		super(context, layoutId, objects);
		mInflater = LayoutInflater.from(context);
		mContext = context;
		mStringList = new ArrayList<String>(objects);
		mLayoutId = layoutId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Item item = null;
		View v = convertView;
		if (v == null) {
			v = mInflater.inflate(mLayoutId, parent, false);
			item = new Item();
			item.manualName = (TextView) v.findViewById(R.id.manual_listview_item_size);
			item.manualName.setText(mStringList.get(position));
			v.setTag(item);
		} else {
			item = (Item) v.getTag();
		}
		return v;
	}

	@Override
	public int getItemViewType(int position) {

		return 0;
	}

	@Override
	public int getCount() {
		return mStringList.size();

	}

	@Override
	public String getItem(int position) {
		return mStringList.get(position);
	}

	private static class Item {
		TextView manualName;
	}

}
