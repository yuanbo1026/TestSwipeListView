package com.example.TestSwipeListView.NexxooSwipeListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.TestSwipeListView.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by b.yuan on 04.09.2015.
 */
public class NexxooListViewAdapter extends ArrayAdapter<String> {

	private Context mContext;
	private LayoutInflater mInflater;
	private List<String> list;
	private List<String> mBaseEntityList;

	public NexxooListViewAdapter(Context context, int layoutId, List<String> objects) {
		super(context, layoutId, objects);
		mInflater = LayoutInflater.from(context);
		mContext = context;
		mBaseEntityList = new ArrayList<String>(objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Item item = null;
		View v = convertView;
		if (v == null) {
			v = mInflater.inflate(R.layout.listview_item_container, parent, false);
			v.setBackgroundColor(mContext.getResources().getColor(R.color.RealWhite));
			item = new Item();
			item.name = (TextView) v.findViewById(R.id.name);
			item.downlload_button = (ImageView) v.findViewById(R.id.download_button);
			item.view_button =(ImageView) v.findViewById(R.id.view_button);
			item.name.setText(mBaseEntityList.get(position));



			v.setTag(item);
		} else {
			item = (Item) v.getTag();
		}

		return v;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public String getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	private static class Item {
		TextView name;
		ImageView downlload_button;
		ImageView view_button;
	}

}
