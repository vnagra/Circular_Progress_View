package com.example.customview;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends BaseActivity implements OnClickListener{

	// view elements
	private EditText mEditText1;
	private EditText mEditText2;
	private Button mAnimateButton;
	private DrawingTheCircle mCircularProgress;
	private int mGoal;
	private int mStart;

	// Incremental coundowntTimer for the progress
	private CountDownTimer mProgressText;

	private static  int mAnimationTimer  = BaseActivity.getAnimationTimer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set animation timer 
		
		
		// display the xml layout
		setContentView(R.layout.activity_main);

		// join the ui elements to the view
		this.mEditText1 = (EditText) findViewById(R.id.editTextCurrentNumber);
		this.mEditText2 = (EditText) findViewById(R.id.editTextGoalNumber);
		this.mAnimateButton = (Button) findViewById(R.id.button1);
		this.mCircularProgress = (DrawingTheCircle) findViewById(R.id.circular_bar);


		// animate when the Button is clicked
		this.mAnimateButton.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {

		super.onResume();
	}


	@Override
	protected void onPause() {

		super.onPause();

		if (this.mProgressText != null)
		{
			this.mProgressText.cancel();
		}
	}

	@Override
	public void onClick(View v) {
		// grab the lower bonds and upper bonds of animation
		String lowerBound = mEditText1.getText().toString();
		String upperBound = mEditText2.getText().toString();
		// make sure the editText are not emplty
		if(!lowerBound.matches(""))
		{
			mStart = intValueof(lowerBound);
		}
		if(!lowerBound.matches(""))
		{
			mGoal = intValueof(upperBound);
		}
		// set the progress to lower bounds
		
		
		this.mProgressText = new CountDownTimer((mGoal - mStart) * mAnimationTimer, mAnimationTimer) {

			@Override
			public void onTick(long millisUntilFinished) {
				
				final int secondsRemaining =  (mGoal - ((int) (millisUntilFinished/mAnimationTimer)));
				MainActivity.this.mCircularProgress.setProgress(secondsRemaining);
			}

			@Override
			public void onFinish() {
				MainActivity.this.mCircularProgress.setProgress(mGoal);

			}
		}.start();

	}
	private int intValueof(String s) {
		int result = Integer.parseInt(s);
		return result;
	}
}
