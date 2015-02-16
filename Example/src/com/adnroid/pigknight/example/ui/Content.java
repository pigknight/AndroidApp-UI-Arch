package com.adnroid.pigknight.example.ui;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.adnroid.pigknight.example.R;
import com.android.pigknight.UI;


public class Content extends UI {
   private final static String TAG = Content.class.getSimpleName();
	
	protected static Content mInstance = null;
	
	public static final String CHILD_ID_CATEGORY = "1";
	public static final String CHILD_ID_ALBUM_LIST = "2";
	public static final String CHILD_ID_MUSIC_LIST = "3";
	
	protected Content(Context context, boolean isRoot) {
		
		super(context, isRoot);
		// TODO Auto-generated constructor stub
	}
	
	public static void initInstance(Context paramContext,boolean isRoot){
	    mInstance = new Content(paramContext, isRoot);
	}
	
	public static Content getInstance(){
	    if (mInstance == null)
	      throw new RuntimeException(TAG+": Must called the initInstance() before to call getInstance().");
	    return mInstance;
	}

	@Override
	public ViewGroup onCreateContainer() {
		// TODO Auto-generated method stub
		return (ViewGroup)View.inflate(mContext, R.layout.content, null);
	}

	@Override
	public ViewGroup onCreateChildUIContainer() {
		// TODO Auto-generated method stub
		return (ViewGroup)mContainer.findViewById(R.id.child_ui_container);
	}

	@Override
	public void onInitialize() {
		// TODO Auto-generated method stub
		mContainer.findViewById(R.id.play_control_bar).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Example.getInstance().switchChildUI(Example.CHILD_ID_PLAYING, false);
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
		return false;
	}
}
