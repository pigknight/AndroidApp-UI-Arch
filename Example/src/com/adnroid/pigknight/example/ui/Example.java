package com.adnroid.pigknight.example.ui;

import android.content.Context;
import android.view.KeyEvent;
import android.view.ViewGroup;

import com.adnroid.pigknight.example.MainActivity;
import com.adnroid.pigknight.example.R;
import com.android.pigknight.UI;

public class Example extends UI {
    private final static String TAG = Example.class.getSimpleName();
	
	protected static Example mInstance = null;
	
	public static final String CHILD_ID_CONTENT = "1";
	public static final String CHILD_ID_PLAYING = "2";
	
	protected Example(Context context, boolean isRoot) {
		
		super(context, isRoot);
		// TODO Auto-generated constructor stub
	}
	
	public static void initInstance(Context paramContext,boolean isRoot){
	    mInstance = new Example(paramContext, isRoot);
	}
	
	public static Example getInstance(){
	    if (mInstance == null)
	      throw new RuntimeException(TAG+": Must called the initInstance() before to call getInstance().");
	    return mInstance;
	}

	@Override
	public ViewGroup onCreateContainer() {
		// TODO Auto-generated method stub
		return (ViewGroup)((MainActivity)mContext).getMainContainer();
	}

	@Override
	public ViewGroup onCreateChildUIContainer() {
		// TODO Auto-generated method stub
		return (ViewGroup)mContainer.findViewById(R.id.child_ui_container);
	}

	@Override
	public void onInitialize() {
		// TODO Auto-generated method stub
		
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
