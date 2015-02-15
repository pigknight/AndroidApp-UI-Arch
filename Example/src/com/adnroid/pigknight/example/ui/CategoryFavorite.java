package com.adnroid.pigknight.example.ui;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.adnroid.pigknight.example.R;
import com.android.pigknight.UI;


public class CategoryFavorite extends UI {
	private final static String TAG = CategoryFavorite.class.getSimpleName();
	
	protected static CategoryFavorite mInstance = null;
	
	protected CategoryFavorite(Context context, boolean isRoot) {
		
		super(context, isRoot);
		// TODO Auto-generated constructor stub
	}
	
	public static void initInstance(Context paramContext,boolean isRoot){
	    mInstance = new CategoryFavorite(paramContext, isRoot);
	}
	
	public static CategoryFavorite getInstance(){
	    if (mInstance == null)
	      throw new RuntimeException(TAG+": Must called the initInstance() before to call getInstance().");
	    return mInstance;
	}

	@Override
	public ViewGroup onCreateContainer() {
		// TODO Auto-generated method stub
		return (ViewGroup)View.inflate(mContext, R.layout.category_favorite, null);
	}

	@Override
	public ViewGroup onCreateChildUIContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onInitialize() {
		// TODO Auto-generated method stub
		mContainer.findViewById(R.id.btn_view_album_favorited).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Content.getInstance().switchChildUI(Content.CHILD_ID_ALBUM_LIST, false);
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
