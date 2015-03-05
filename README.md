# AndroidApp-UI-Arch
This is a rapid development architecture for Android UI. From now,you can say goodbye to Activity or Fragment.
For the detail,please read the file which named 'Readme.doc' under the git.

Feature:
1>.UI code modularization;
2>.Make the UI structure of your application more clear;
3>.Using static UI architecture mode;
4>.Support UI switching animation;
5>.Synchronizing the Activity's onPause() and onResume()(optional);
6>.Flexible customization,More rapid,lightweight, flexible and convenient compared with the Activity or Fragment;

Example:
-----
![screenshot](https://github.com/pigknight/AndroidApp-UI-Arch/blob/master/Example_UI_Structure.jpg "screenshot")

![screenshot](https://github.com/pigknight/AndroidApp-UI-Arch/blob/master/demo_animation.gif "screenshot")

Usage:
-----

First,you must be implements the all child UI which having a separate logic function & derived from the base class 'UI';

```java
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

  ................................	
  ................................
  ................................
}
```

Second, Init all child UI

```java
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
```

Third. Register the UI to a static tree structure.

```java
//register
Example.getInstance().registerChildUI(Content.getInstance(),Example.CHILD_ID_CONTENT);
    Content.getInstance().registerChildUI(Category.getInstance(),Content.CHILD_ID_CATEGORY);
        Category.getInstance().registerChildUI(CategoryLocal.getInstance(), Category.CHILD_ID_LOCAL);
        Category.getInstance().registerChildUI(CategoryNetwork.getInstance(), Category.CHILD_ID_NETWORK);
        Category.getInstance().registerChildUI(CategoryFavorite.getInstance(), Category.CHILD_ID_FAVORITE);
    Content.getInstance().registerChildUI(AlbumList.getInstance(), Content.CHILD_ID_ALBUM_LIST);
    Content.getInstance().registerChildUI(MusicList.getInstance(), Content.CHILD_ID_MUSIC_LIST);
Example.getInstance().registerChildUI(Playing.getInstance(), Example.CHILD_ID_PLAYING);
```

Fourth,set default Animation

```java
//set default Animation
Example.getInstance().setDefaultAnimation(R.anim.push_bottom_in, R.anim.push_top_out);
Content.getInstance().setDefaultAnimation(R.anim.push_right_in, R.anim.push_left_out);
Category.getInstance().setDefaultAnimation(R.anim.push_right_in, R.anim.push_left_out);
```

Fifth,set default ui and  this is the way to switch the child UI.

```java
//set default ui
Example.getInstance().switchChildUI(Example.CHILD_ID_CONTENT, false);
Content.getInstance().switchChildUI(Content.CHILD_ID_CATEGORY, false);
```
	
Other, In you Activity, dispatch key Event,onPause(),onResume(),onConfigurationChanged()

```java
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
```


#############################
Continuously updated ...
#############################
