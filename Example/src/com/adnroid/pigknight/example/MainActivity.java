package com.adnroid.pigknight.example;

import com.adnroid.pigknight.example.ui.AlbumList;
import com.adnroid.pigknight.example.ui.Category;
import com.adnroid.pigknight.example.ui.CategoryFavorite;
import com.adnroid.pigknight.example.ui.CategoryLocal;
import com.adnroid.pigknight.example.ui.CategoryNetwork;
import com.adnroid.pigknight.example.ui.Content;
import com.adnroid.pigknight.example.ui.Example;
import com.adnroid.pigknight.example.ui.MusicList;
import com.adnroid.pigknight.example.ui.Playing;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	private View mMainContainer = null;
	
	private boolean mWaitForSecondKey = false;
	private static final int MSG_ID_DELAYED_TO_WAIT_SECOND_BACK_KEY = 1;
    
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //Log.i(TAG, "handleMessage: " + msg.what);
            switch (msg.what) {
                case MSG_ID_DELAYED_TO_WAIT_SECOND_BACK_KEY:
                    mHandler.removeMessages(MSG_ID_DELAYED_TO_WAIT_SECOND_BACK_KEY);
                    mWaitForSecondKey = false;
                    break;
            }
        }
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mMainContainer = View.inflate(this, R.layout.activity_main, null);
		setContentView(mMainContainer);
		
		
		initUI();
	}
	
	@Override
	public void onPause(){
		super.onPause();
		
		Example.getInstance().dispatchOnPause();
	}
	
	@Override
	public void onResume(){
		super.onPause();
		
		Example.getInstance().dispatchOnResume();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig){
		
		Example.getInstance().dispatchOrientationChanged(newConfig);
		
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {    	
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	if( Example.getInstance().dispatchKeyEvent(event) )
    			return true;
        	
            if (mWaitForSecondKey == false) {
                mWaitForSecondKey = true;

                Toast.makeText(this, "Press again to exit!", Toast.LENGTH_SHORT).show();

                mHandler.removeMessages(MSG_ID_DELAYED_TO_WAIT_SECOND_BACK_KEY);
                mHandler.sendEmptyMessageDelayed(MSG_ID_DELAYED_TO_WAIT_SECOND_BACK_KEY, 2000);
            } else
                finish();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
	
	public View getMainContainer(){
		return mMainContainer;
	}
	
	private void initUI(){
		//init 
		Example.initInstance(this, true);
		    Content.initInstance(this, false);
		        Category.initInstance(this, false);
		        	CategoryLocal.initInstance(this, false);
		        	CategoryNetwork.initInstance(this, false);
		        	CategoryFavorite.initInstance(this, false);
		        AlbumList.initInstance(this, false);
		        MusicList.initInstance(this, false);
		    Playing.initInstance(this, false);
		//register
		Example.getInstance().registerChildUI(Content.getInstance(),Example.CHILD_ID_CONTENT);
		    Content.getInstance().registerChildUI(Category.getInstance(),Content.CHILD_ID_CATEGORY);
		        Category.getInstance().registerChildUI(CategoryLocal.getInstance(), Category.CHILD_ID_LOCAL);
		        Category.getInstance().registerChildUI(CategoryNetwork.getInstance(), Category.CHILD_ID_NETWORK);
		        Category.getInstance().registerChildUI(CategoryFavorite.getInstance(), Category.CHILD_ID_FAVORITE);
		    Content.getInstance().registerChildUI(AlbumList.getInstance(), Content.CHILD_ID_ALBUM_LIST);
		    Content.getInstance().registerChildUI(MusicList.getInstance(), Content.CHILD_ID_MUSIC_LIST);
		Example.getInstance().registerChildUI(Playing.getInstance(), Example.CHILD_ID_PLAYING);
		
		//set default Animation
		Example.getInstance().setDefaultAnimation(R.anim.push_bottom_in, R.anim.push_top_out);
		Content.getInstance().setDefaultAnimation(R.anim.push_right_in, R.anim.push_left_out);
		Category.getInstance().setDefaultAnimation(R.anim.push_right_in, R.anim.push_left_out);
		
		//set default ui
		Example.getInstance().switchChildUI(Example.CHILD_ID_CONTENT, false);
		Content.getInstance().switchChildUI(Content.CHILD_ID_CATEGORY, false);
	}

}
