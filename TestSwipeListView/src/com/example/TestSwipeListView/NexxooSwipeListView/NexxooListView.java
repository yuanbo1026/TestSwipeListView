package com.example.TestSwipeListView.NexxooSwipeListView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.example.TestSwipeListView.swipelistview.*;

/**
 * Created by b.yuan on 04.09.2015.
 */
public class NexxooListView extends ListView {

	private static final int TOUCH_STATE_NONE = 0;
	private static final int TOUCH_STATE_X = 1;
	private static final int TOUCH_STATE_Y = 2;

	public static final int DIRECTION_LEFT = 1;
	public static final int DIRECTION_RIGHT = -1;
	private int mDirection = 1;//swipe from right to left by default

	private int MAX_Y = 5;
	private int MAX_X = 3;
	private float mDownX;
	private float mDownY;
	private int mTouchState;
	private int mTouchPosition;
	private SwipeMenuLayout mTouchView;

	private SwipeMenuCreator mMenuCreator;
	private OnMenuItemClickListener mOnMenuItemClickListener;
	private Interpolator mCloseInterpolator;
	private Interpolator mOpenInterpolator;

	public NexxooListView(Context context) {
		super(context);
		init();
	}

	public NexxooListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public NexxooListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		MAX_X = dp2px(MAX_X);
		MAX_Y = dp2px(MAX_Y);
		mTouchState = TOUCH_STATE_NONE;
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(new SwipeMenuAdapter(getContext(), adapter) {
			/*@Override
			public void createMenu(SwipeMenu menu) {
				if (mMenuCreator != null) {
					mMenuCreator.create(menu);
				}
			}*/

			@Override
			public void onItemClick(SwipeMenuView view, SwipeMenu menu,
									int index) {
				boolean flag = false;
				if (mOnMenuItemClickListener != null) {
					flag = mOnMenuItemClickListener.onMenuItemClick(
							view.getPosition(), menu, index);
				}
				if (mTouchView != null && !flag) {
					mTouchView.smoothCloseMenu();
				}
			}

		});
	}



	public void setCloseInterpolator(Interpolator interpolator) {
		mCloseInterpolator = interpolator;
	}

	public void setOpenInterpolator(Interpolator interpolator) {
		mOpenInterpolator = interpolator;
	}

	public Interpolator getOpenInterpolator() {
		return mOpenInterpolator;
	}

	public Interpolator getCloseInterpolator() {
		return mCloseInterpolator;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev);
	}

	public void smoothOpenMenu(int position) {
		if (position >= getFirstVisiblePosition()
				&& position <= getLastVisiblePosition()) {
			View view = getChildAt(position - getFirstVisiblePosition());
			if (view instanceof SwipeMenuLayout) {
				mTouchPosition = position;
				if (mTouchView != null && mTouchView.isOpen()) {
					mTouchView.smoothCloseMenu();
				}
				mTouchView = (SwipeMenuLayout) view;
				mTouchView.setSwipeDirection(mDirection);
				mTouchView.smoothOpenMenu();
			}
		}
	}

	public void closeAllMenu() {
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View view = getChildAt(i);
			if (view instanceof SwipeMenuLayout) {
				mTouchView = (SwipeMenuLayout) view;
				if (mTouchView != null && mTouchView.isOpen()) {
					mTouchView.smoothCloseMenu();
				}
			}
		}
	}

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getContext().getResources().getDisplayMetrics());
	}

	public void setOnMenuItemClickListener(
			OnMenuItemClickListener onMenuItemClickListener) {
		this.mOnMenuItemClickListener = onMenuItemClickListener;
	}

	public static interface OnMenuItemClickListener {
		boolean onMenuItemClick(int position, SwipeMenu menu, int index);
	}

}
