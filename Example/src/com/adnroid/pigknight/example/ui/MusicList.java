package com.adnroid.pigknight.example.ui;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adnroid.pigknight.example.R;
import com.android.pigknight.UI;


public class MusicList extends UI {
	private final static String TAG = MusicList.class.getSimpleName();
	
	protected static MusicList mInstance = null;
	
	protected MusicList(Context context, boolean isRoot) {
		
		super(context, isRoot);
		// TODO Auto-generated constructor stub
	}
	
	public static void initInstance(Context paramContext,boolean isRoot){
	    mInstance = new MusicList(paramContext, isRoot);
	}
	
	public static MusicList getInstance(){
	    if (mInstance == null)
	      throw new RuntimeException(TAG+": Must called the initInstance() before to call getInstance().");
	    return mInstance;
	}

	@Override
	public ViewGroup onCreateContainer() {
		// TODO Auto-generated method stub
		return (ViewGroup)View.inflate(mContext, R.layout.music_list, null);
	}

	@Override
	public ViewGroup onCreateChildUIContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onInitialize() {
		// TODO Auto-generated method stub
		((TextView)mContainer.findViewById(R.id.title)).setText("Music List");
		mContainer.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Content.getInstance().switchChildUI(Content.CHILD_ID_ALBUM_LIST, true);
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
	public void onPause() {
		// TODO Auto-generated method stub
		Log.i(TAG,"===========================================onPause()");
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		Log.i(TAG,"===========================================onResume()");
	}

	@Override
	public boolean onKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		if( event.getKeyCode() == KeyEvent.KEYCODE_BACK ){
			Content.getInstance().switchChildUI(Content.CHILD_ID_ALBUM_LIST, true);
			
			return true;
		}
		return false;
	}
	
	public void setTitle(String title){
		((TextView)mContainer.findViewById(R.id.title)).setText("Music List - "+title);
	}
}
