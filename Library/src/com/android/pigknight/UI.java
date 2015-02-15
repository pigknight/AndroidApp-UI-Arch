package com.android.pigknight;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewAnimator;
//=========================================================================================================
/*
 * --------------------------------Usage---------------------------------------
 * 1. Each UI which having a separate logic function must be derived from the base class 'UI'.
 * 2. The sub-UI must be rewrite all abstract methods of the Base UI.
 * 3. Define and instantiate a UI subclass as root UI. Each application can have one or more root UI(derived from the base class 'UI' too).
 * 5. After you define and instantiate a UI subclass,call registerChildUI(,,) to register it on the current UI.
 * 6. Call switchChildUI(,,) to switch the child UI of the current UI(parent UI).
 * 7. Call dispatchKeyEvent(event) to dispatch KeyEvent to the root UI.
 * 8. Before the application exit, call dispatchFinalize() to release all.(There is not necessary)
//=========================================================================================================
/*
 * The base class for the UI class which is a logic function unit.
 */
public abstract class UI {
    private static final String TAG = "UI";
    
    //Application Context
    protected Context mContext = null;
    
    //The show stack in order to back.   *************
    private Stack<UI> mShowStack = null;
    
    private UI mPreviousUI = null;
    private UI mNextUI = null;
    
    //The container view(in *.xml) which associated with the current UI
    protected ViewGroup mContainer = null;
    
    //A container view who will contain(or switch) children UI in current UI (mContainerView)
    private ViewGroup mChildUIContainer = null;
    
    private ViewAnimator mChildUIViewAnimator = null;
    
    private Animation mDefaultInAnimation = null;
    private Animation mDefaultOutAnimation = null;
    
    private Animation.AnimationListener mInAnimationListener = new Animation.AnimationListener(){
		@Override
		public void onAnimationEnd(Animation animation) {
			// TODO Auto-generated method stub	
			if( mNextUI != null ){
			    mNextUI.dispatchOnShow();
			    mNextUI = null;
			}
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationStart(Animation animation) {	
		}
    	
    };
    
    private Animation.AnimationListener mOutAnimationListener = new Animation.AnimationListener(){

		@Override
		public void onAnimationEnd(Animation animation) {
			// TODO Auto-generated method stub
			if( mPreviousUI != null ){
				boolean releaseOld = false;
				Boolean release_old = (Boolean)mPreviousUI.getContainer().getTag();
				if( release_old != null )
					releaseOld = release_old;
				if( releaseOld )
    	    	    mPreviousUI.dispatchRelease();
    	    }
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationStart(Animation animation) {	
		}
    	
    };
    /*
     * Saved the child UI object be registered,which will be switched
     * by the childKey registered.
     */
    private HashMap<String,UI> mChildUIMap = new  HashMap<String,UI>();
    /*
     * This ... you know.
     */
    public boolean mIsInitialized = false;
    /*
     * The childKey which corresponding the child UI who is showing
     */
    public String mCurChildKey = null;
    
    protected UI(Context context,boolean isRoot){
    	mContext = context;
    	mIsInitialized = false;
    	/*
    	 * The Root UI must be Initialized immediately
    	 */
    	if( isRoot == true ){
    		Initialize();
    		onShow();
    	}
    }
    
    public void setDefaultAnimation(int inAnimRes,int outAnimRes){
    	mDefaultInAnimation = AnimationUtils.loadAnimation(mContext, inAnimRes);
    	mDefaultOutAnimation = AnimationUtils.loadAnimation(mContext, outAnimRes);;
    }
    
    public void setDefaultAnimation(Animation in,Animation out){
    	mDefaultInAnimation = in;
    	mDefaultOutAnimation = out;
    }
    
    
    /*
     * This is called when the UI is Initializing.
     * You must implement this method and return the 
     * Container View for this UI.
     */
    public abstract ViewGroup onCreateContainer();
    /*
     * This is called when the UI is Initializing.
     * You must implement this method and return a 
     * Subclass of the ViewGroup which will contain the child UI.
     */
    public abstract ViewGroup onCreateChildUIContainer();
    /*
     * This is called when the UI is Initializing.
     * You can do some task to initialize the UI
     */
    public abstract void onInitialize();
    /*
     * This is called while the UI is no longer in use.
     * You can release the UI(layout) itself.
     */
    public abstract void onFinalize();
    /*
     * This is called while the UI is Initialized and prepare
     * to show.
     * You can prepare the default data for the UI to showing.
     * Or you can set the default focus
     */
    public abstract void onShow();
    /*
     * This is called while the UI is hide.
     */
    public abstract void onHide();
    /*
     * Activity.onPause();
     */
    public void onPause(){
    	
    }
    /*
     * Activity.onResume();
     */
    public void onResume(){
    	
    }
    /*
     * This is called while receive Key Event
     */
    public abstract boolean onKeyEvent(KeyEvent event);
    
    /*
     * Configuration.ORIENTATION_PORTRAIT
     * Or
     * Configuration.ORIENTATION_LANDSCAPE
     */
    public boolean isOrientationDependent(){
    	return false;
    }
    
    public final void Initialize(){
    	 mContainer = onCreateContainer();
    	 mChildUIContainer = onCreateChildUIContainer();
    	 if( mChildUIContainer != null ){
    		 //has Child,init the show stack   *************
    		 mShowStack = new Stack<UI>();
    		 
    		 mChildUIContainer.removeAllViews();
    		 
    		 if( mChildUIContainer instanceof ViewAnimator){
	    		 mChildUIViewAnimator = (ViewAnimator)mChildUIContainer;
	    	 }else{
	    		 mChildUIViewAnimator = new ViewAnimator(mContext);
	    		 LayoutParams lp = mChildUIContainer.getLayoutParams();
	    		 if( lp != null )
	    		     mChildUIContainer.addView(mChildUIViewAnimator, new ViewGroup.LayoutParams(lp.width, lp.height));
	    		 else
	    			 mChildUIContainer.addView(mChildUIViewAnimator, 
	    					 new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
	    	 }
    	 }
    	
    	 onInitialize();
    	 
    	 mIsInitialized = true;
    }
    
    public void removeAllChildUI(){
    	if( mChildUIMap.size() > 0 ){
    		Collection<UI> children = mChildUIMap.values();
    		for( UI child : children ){
    			if( child != null && child instanceof UI ){
    				child.onHide();
    				child.onFinalize();
    			}
    		}
    	}
    	
    	mChildUIViewAnimator.removeAllViews();
    	mCurChildKey = null;
    }
    
    private final void dispatchRelease(){
    	if( mCurChildKey != null && !mCurChildKey.equals("") ){
    	    UI curShow = mChildUIMap.get( mCurChildKey );
    	    if( curShow != null ){
    	    	curShow.dispatchRelease();
    	    }
    	}
    	
    	onHide();

	    onFinalize();
	
	    mIsInitialized = false;
	    mCurChildKey = null;
	    
	    mContainer = null;
   	    mChildUIContainer = null;
   	    mChildUIViewAnimator = null;
    }
    
    public final void dispatchOnShow(){
    	if( mCurChildKey != null && !mCurChildKey.equals("") ){
    	    UI curShow = mChildUIMap.get( mCurChildKey );
    	    if( curShow != null ){
    	    	curShow.dispatchOnShow();
    	    }
    	}
    	
    	onShow();
    }
    
    public final void dispatchOnHide(){
    	if( mCurChildKey != null && !mCurChildKey.equals("") ){
    	    UI curShow = mChildUIMap.get( mCurChildKey );
    	    if( curShow != null ){
    	    	curShow.dispatchOnHide();
    	    }
    	}
    	
    	onShow();
    }
    
    public final void dispatchOnPause(){
    	if( mCurChildKey != null && !mCurChildKey.equals("") ){
    	    UI curShow = mChildUIMap.get( mCurChildKey );
    	    if( curShow != null ){
    	    	curShow.dispatchOnPause();
    	    }
    	}
    	
    	onPause();
    }
    
    public final void dispatchOnResume(){
    	if( mCurChildKey != null && !mCurChildKey.equals("") ){
    	    UI curShow = mChildUIMap.get( mCurChildKey );
    	    if( curShow != null ){
    	    	curShow.dispatchOnResume();
    	    }
    	}
    	
    	 onResume();
    }
    
    /* Dispatch the KeyEvent form the Root UI to the child UI.
     * return true: if current UI handled the KeyEvent.
     * return false: if current UI not handled the KeyEvent.
     */
    public final boolean dispatchKeyEvent(KeyEvent event){
    	if( mCurChildKey != null && !mCurChildKey.equals("") ){
    		UI curShow = mChildUIMap.get( mCurChildKey );
    		if( curShow != null ){
    			if( curShow.dispatchKeyEvent(event) )
    				return true;
    		}
    	}
    	
    	return onKeyEvent(event);
    }
    
    public final ViewGroup getContainer(){
    	return mContainer;
    }
    
    public final ViewGroup getChildUIContainer(){
    	return mChildUIContainer;
    }
    
    /*
     * Register the child UI
     * childUI: the children logic & function class 
     * childKey: the key about the child UI,Must be Unique under the current UI
     */
    public final void registerChildUI(UI childUI,String childKey){
    	if( childUI != null && childKey != null ){
    		if( mChildUIMap.containsKey(childKey) ){
    			mChildUIMap.remove(childKey);
    		}
    		
    		mChildUIMap.put( childKey, childUI);
    	}else{
    		throw new RuntimeException(TAG + ": register failed! childUI or key is null!");
    	}
    }
    
    /*
     * Switch the child UI
     * childKey:  the key about the child UI
     * releaseOld: After switch to new child UI,release current UI.
     */
    public void switchChildUI( String childKey ,boolean releaseOld){
    	switchChildUI( childKey,releaseOld,null,null);
    }
    
    public void switchChildUI( String childKey ,boolean releaseOld,int inAnimRes,int outAnimRes){
    	Animation inAnimation = AnimationUtils.loadAnimation(mContext, inAnimRes);
    	Animation outAnimation = AnimationUtils.loadAnimation(mContext, outAnimRes);
    	switchChildUI( childKey,releaseOld,inAnimation,outAnimation);
    }
    
    public void switchChildUI( String childKey ,boolean releaseOld,Animation inAnimation,Animation outAnimation){    	
    	if( mChildUIViewAnimator == null ){
    		throw new RuntimeException(TAG + ": mChildUIContainer was null.");
    	}
    	
    	if( mCurChildKey != null && mCurChildKey.equals(childKey) )
    		return;
    	
    	if( mPreviousUI != null ){
    	    mChildUIViewAnimator.removeView(mPreviousUI.getContainer());
    	    mPreviousUI = null;
    	}
    	
    	if( mCurChildKey != null && !mCurChildKey.equals( childKey ) ){
    		mPreviousUI = mChildUIMap.get( mCurChildKey );
    	}

    	mNextUI =  mChildUIMap.get( childKey );
    	if( mNextUI != null ){
    		if( !mNextUI.isInitialized() ){
    			mNextUI.Initialize();
    		}
    		
    		mChildUIViewAnimator.addView( mNextUI.getContainer(),mChildUIViewAnimator.getChildCount(),new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
    		
    		if( inAnimation == null )
    			inAnimation = mDefaultInAnimation;
    		
    		if( inAnimation != null ){
    			inAnimation.setAnimationListener(mInAnimationListener);
    		    mChildUIViewAnimator.setInAnimation(inAnimation);
    		}else
    			mChildUIViewAnimator.setInAnimation(null);
    		
    		if( outAnimation == null )
    			outAnimation = mDefaultOutAnimation;
    		
    		if( outAnimation != null ){
    			outAnimation.setAnimationListener(mOutAnimationListener);
    		    mChildUIViewAnimator.setOutAnimation(outAnimation);
    		    if( mPreviousUI != null )
    		        mPreviousUI.getContainer().setTag(releaseOld);
    		}else{
    			mChildUIViewAnimator.setOutAnimation(null);
    			if( mPreviousUI != null ){
    				if( releaseOld )
	    	    	    mPreviousUI.dispatchRelease();
	    	    }
    		}
    		
    		mCurChildKey = childKey;
    		
    		mChildUIViewAnimator.showNext();
    		
    		if( inAnimation == null ){
    			if( mNextUI != null ){
				    mNextUI.dispatchOnShow();
				    mNextUI = null;
				}
    		}
    	}else
    		mPreviousUI = null;
    }
    
    public final void notifyOrientationChanged(Configuration newConfig){
    	if( isOrientationDependent() ){
    		//release old
    		this.onHide();
    		this.onFinalize();
    		
    		//create new 
    		this.Initialize();
    	}
    	
    	if( mChildUIMap.size() > 0 ){
    		Set<String> childrenKeySet = mChildUIMap.keySet();
    		for( String childKey : childrenKeySet ){
    			UI child = mChildUIMap.get(childKey);
    			if( child != null && child instanceof UI )
    			    child.notifyOrientationChanged(newConfig);
    		}
    	}
    	
    	if( this.mCurChildKey != null )
    	    this.switchChildUI(this.mCurChildKey, false,mChildUIViewAnimator.getInAnimation(),mChildUIViewAnimator.getOutAnimation());
    }
    
    public final String getCurrentChildKey(){
    	return mCurChildKey;
    }
    
    public final  boolean isInitialized(){
    	return mIsInitialized;
    }
    
    public final boolean isShowing(){
    	if( mContainer != null )
    	    return mContainer.getVisibility() == View.VISIBLE && mContainer.getWindowVisibility() == View.VISIBLE;
    	else
    		return false;
    }
}

