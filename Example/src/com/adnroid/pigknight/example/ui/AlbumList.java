package com.adnroid.pigknight.example.ui;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adnroid.pigknight.example.R;
import com.android.pigknight.UI;

public class AlbumList extends UI {
    private final static String TAG = AlbumList.class.getSimpleName();
	
	protected static AlbumList mInstance = null;
	
	protected AlbumList(Context context, boolean isRoot) {
		
		super(context, isRoot);
		// TODO Auto-generated constructor stub
	}
	
	public static void initInstance(Context paramContext,boolean isRoot){
	    mInstance = new AlbumList(paramContext, isRoot);
	}
	
	public static AlbumList getInstance(){
	    if (mInstance == null)
	      throw new RuntimeException(TAG+": Must called the initInstance() before to call getInstance().");
	    return mInstance;
	}

	@Override
	public ViewGroup onCreateContainer() {
		// TODO Auto-generated method stub
		return (ViewGroup)View.inflate(mContext, R.layout.album_list, null);
	}

	@Override
	public ViewGroup onCreateChildUIContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onInitialize() {
		// TODO Auto-generated method stub
		((TextView)mContainer.findViewById(R.id.title)).setText("Album List");
		mContainer.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Content.getInstance().switchChildUI(Content.CHILD_ID_CATEGORY, true);
			}
		});
		
		mContainer.findViewById(R.id.album_1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Content.getInstance().switchChildUI(Content.CHILD_ID_MUSIC_LIST, false);
			}
		});
		
		mContainer.findViewById(R.id.album_2).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Content.getInstance().switchChildUI(Content.CHILD_ID_MUSIC_LIST, false);
			}
		});
		
		mContainer.findViewById(R.id.album_3).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Content.getInstance().switchChildUI(Content.CHILD_ID_MUSIC_LIST, false);
			}
		});
		
		mContainer.findViewById(R.id.album_4).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Content.getInstance().switchChildUI(Content.CHILD_ID_MUSIC_LIST, false);
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
			Content.getInstance().switchChildUI(Content.CHILD_ID_CATEGORY, true);
			
			return true;
		}
		return false;
	}
}
