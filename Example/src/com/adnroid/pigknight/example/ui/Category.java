package com.adnroid.pigknight.example.ui;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.adnroid.pigknight.example.R;
import com.android.pigknight.UI;

public class Category extends UI {
	private final static String TAG = Category.class.getSimpleName();
	
	protected static Category mInstance = null;
	
	public static final String CHILD_ID_LOCAL = "1";
	public static final String CHILD_ID_NETWORK = "2";
	public static final String CHILD_ID_FAVORITE = "3";
	
	private View.OnClickListener mOnClickListener = new View.OnClickListener() {
		private View mLastView = null;
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if( mLastView == v )
				return;
			switch( v.getId() ){
			case R.id.btn_local:
				Category.getInstance().switchChildUI(Category.CHILD_ID_LOCAL, false);
				break;
			case R.id.btn_network:
				Category.getInstance().switchChildUI(Category.CHILD_ID_NETWORK, false);
				break;
			case R.id.btn_favorite:
				Category.getInstance().switchChildUI(Category.CHILD_ID_FAVORITE, false);
				break;
			}
			
			if( mLastView != null )
				mLastView.setSelected(false);
			v.setSelected(true);
			mLastView = v;
		}
	};
	
	protected Category(Context context, boolean isRoot) {
		
		super(context, isRoot);
		// TODO Auto-generated constructor stub
	}
	
	public static void initInstance(Context paramContext,boolean isRoot){
	    mInstance = new Category(paramContext, isRoot);
	}
	
	public static Category getInstance(){
	    if (mInstance == null)
	      throw new RuntimeException(TAG+": Must called the initInstance() before to call getInstance().");
	    return mInstance;
	}

	@Override
	public ViewGroup onCreateContainer() {
		// TODO Auto-generated method stub
		return (ViewGroup)View.inflate(mContext, R.layout.category, null);
	}

	@Override
	public ViewGroup onCreateChildUIContainer() {
		// TODO Auto-generated method stub
		return (ViewGroup)mContainer.findViewById(R.id.child_ui_container);
	}

	@Override
	public void onInitialize() {
		// TODO Auto-generated method stub
		mContainer.findViewById(R.id.btn_local).setOnClickListener(mOnClickListener);
		mContainer.findViewById(R.id.btn_network).setOnClickListener(mOnClickListener);
		mContainer.findViewById(R.id.btn_favorite).setOnClickListener(mOnClickListener);
		
		//set default category
		mContainer.findViewById(R.id.btn_local).performClick();
	}

	@Override
	public void onFinalize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}
