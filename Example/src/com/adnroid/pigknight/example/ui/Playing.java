package com.adnroid.pigknight.example.ui;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adnroid.pigknight.example.R;
import com.android.pigknight.UI;

public class Playing extends UI {
    private final static String TAG = Playing.class.getSimpleName();
	
	protected static Playing mInstance = null;
	
	protected Playing(Context context, boolean isRoot) {
		
		super(context, isRoot);
		// TODO Auto-generated constructor stub
	}
	
	public static void initInstance(Context paramContext,boolean isRoot){
	    mInstance = new Playing(paramContext, isRoot);
	}
	
	public static Playing getInstance(){
	    if (mInstance == null)
	      throw new RuntimeException(TAG+": Must called the initInstance() before to call getInstance().");
	    return mInstance;
	}

	@Override
	public ViewGroup onCreateContainer() {
		// TODO Auto-generated method stub
		return (ViewGroup)View.inflate(mContext, R.layout.playing, null);
	}

	@Override
	public ViewGroup onCreateChildUIContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onInitialize() {
		// TODO Auto-generated method stub
		((TextView)mContainer.findViewById(R.id.title)).setText("Playing");
		mContainer.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Example.getInstance().switchChildUI(Example.CHILD_ID_CONTENT, true);
			}
		});
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
		if( event.getKeyCode() == KeyEvent.KEYCODE_BACK ){
			Example.getInstance().switchChildUI(Example.CHILD_ID_CONTENT, true);
			
			return true;
		}
		return false;
	}
}
