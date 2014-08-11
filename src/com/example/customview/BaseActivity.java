package com.example.customview;

import android.app.Activity;

public abstract class BaseActivity extends Activity {
	
	// variable for animation time
	private static int animationTimer = 100;

	public static int getAnimationTimer() {
		return animationTimer;
	}

	public static void setAnimationTimer(int animationTimer) {
		BaseActivity.animationTimer = animationTimer;
	}

}
