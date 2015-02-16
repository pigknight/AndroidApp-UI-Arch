package com.adnroid.pigknight.example.ui;

import android.content.Context;
import android.content.res.Configuration;
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
				Content.getInstance().switchChildUI(Content.CHILD_ID_CATEGORY, true,R.anim.push_left_in,R.anim.push_right_out);
			}
		});
		
		mContainer.findViewById(R.id.album_1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Configuration mConfiguration = mContext.getResources().getConfiguration(); //获取设置的配置信息
				if( mConfiguration.orientation == mConfiguration.ORIENTATION_PORTRAIT ){
					Content.getInstance().switchChildUI(Content.CHILD_ID_MUSIC_LIST, false);
					MusicList.getInstance().setTitle("Album 1");
				}
			}
		});
		
		mContainer.findViewById(R.id.album_2).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Configuration mConfiguration = mContext.getResources().getConfiguration(); //获取设置的配置信息
				if( mConfiguration.orientation == mConfiguration.ORIENTATION_PORTRAIT ){
					Content.getInstance().switchChildUI(Content.CHILD_ID_MUSIC_LIST, false);
					MusicList.getInstance().setTitle("Album 2");
				}
			}
		});
		
		mContainer.findViewById(R.id.album_3).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Configuration mConfiguration = mContext.getResources().getConfiguration(); //获取设置的配置信息
				if( mConfiguration.orientation == mConfiguration.ORIENTATION_PORTRAIT ){
					Content.getInstance().switchChildUI(Content.CHILD_ID_MUSIC_LIST, false);
					MusicList.getInstance().setTitle("Album 3");
				}
			}
		});
		
		mContainer.findViewById(R.id.album_4).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Configuration mConfiguration = mContext.getResources().getConfiguration(); //获取设置的配置信息
				if( mConfiguration.orientation == mConfiguration.ORIENTATION_PORTRAIT ){
					Content.getInstance().switchChildUI(Content.CHILD_ID_MUSIC_LIST, false);
					MusicList.getInstance().setTitle("Album 4");
				}
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
			Content.getInstance().switchChildUI(Content.CHILD_ID_CATEGORY, true,R.anim.push_left_in,R.anim.push_right_out);
			
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isOrientationDependent(){
		return true;
	}
}
