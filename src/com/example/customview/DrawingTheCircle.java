package com.example.customview;

import java.math.BigDecimal;
import java.math.RoundingMode;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class DrawingTheCircle extends View{

	private static final int  MAX_VALUE = 100;
	private static final float TURN_ANGLE = 270f;
	private static final double THREE_SIXTY = 360;

	// maximum points in the circle
	private int mMax = 100;

	// properties for background circle 
	// set this to baby blue from resources
	private final Paint mBgPaint;

	// properties for progress circle
	// set this to green from resources
	private final Paint mProgressPaint;

	// properties for the text
	private final Paint mTextPaint;

	// variable for measuring progress from 0 to 100
	private int mProgress;

	// variable for circles diameter
	private float mDiameter;

	// variable for layout margins
	private int mLayoutMargin;

	// progress arc variable
	private RectF mArcProgress;

	// variable to store text height
	private Rect mTextRectangle;


	public DrawingTheCircle(final Context context,final AttributeSet attrs) {

		this(context,attrs,-1);

	}


	public DrawingTheCircle(final Context context, final AttributeSet attrs, final int defStyle) {

		super(context,attrs,defStyle);

		final TypedArray args = context.obtainStyledAttributes(attrs,R.styleable.drawTheCircle );

		final float defaultDiameter = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64, this.getResources().getDisplayMetrics());
		final float defaultStrokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, this.getResources().getDisplayMetrics());
		final float defaultProgressStrokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, this.getResources().getDisplayMetrics());
		final float defaultMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, this.getResources().getDisplayMetrics());
		final float defaultTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, this.getResources().getDisplayMetrics());

		try
		{

			final int bgColor = args.getColor(R.styleable.drawTheCircle_bgColor, R.color.bg_color);
			final int bgStrokedWidth = args.getDimensionPixelSize(R.styleable.drawTheCircle_bgStrokeWidth, (int) defaultStrokeWidth);
			final int progressColor = args.getColor(R.styleable.drawTheCircle_progressColor, R.color.green);
			final int progressStrokeWidth = args.getDimensionPixelSize(R.styleable.drawTheCircle_progressStrokeWidth, (int) defaultProgressStrokeWidth);
			final int textSize = args.getDimensionPixelSize(R.styleable.drawTheCircle_android_textSize,
					(int) defaultTextSize);
			final int textColor = args.getInt(R.styleable.drawTheCircle_android_textColor, R.color.white);

			this.mLayoutMargin = args.getDimensionPixelSize(R.styleable.drawTheCircle_android_layout_margin,
					(int) defaultMargin);

			this.mMax = args.getInt(R.styleable.drawTheCircle_max, MAX_VALUE);

			this.mDiameter = args.getDimension(R.styleable.drawTheCircle_diameter, defaultDiameter);
			// initialize all the drawing variables
			this.mBgPaint = new Paint();
			this.mBgPaint.setColor(bgColor);
			this.mBgPaint.setAntiAlias(true);
			this.mBgPaint.setStrokeWidth(bgStrokedWidth);
			this.mBgPaint.setStyle(Style.STROKE);

			this.mProgressPaint = new Paint();
			this.mProgressPaint.setColor(progressColor);
			this.mProgressPaint.setAntiAlias(true);
			this.mProgressPaint.setStrokeWidth(progressStrokeWidth);
			this.mProgressPaint.setStyle(Style.STROKE);

			this.mTextPaint = new Paint();
			this.mTextPaint.setColor(textColor);
			this.mTextPaint.setAntiAlias(true);
			this.mTextPaint.setStyle(Style.STROKE);
			this.mTextPaint.setTextSize(textSize);
		}
		finally
		{
			args.recycle();
		}

	}




	/* (non-Javadoc)
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(final Canvas canvas) {
		if (this.mArcProgress == null)
		{
			this.mArcProgress = new RectF(this.mLayoutMargin, this.mLayoutMargin,
					this.mLayoutMargin + this.mDiameter,
					this.mLayoutMargin +this.mDiameter);
		}

		// get the variables to find the center of canvas
		final float radius = this.mDiameter/2;
		final float center = radius + this.mLayoutMargin;
		
		// draw a circle in the center
		canvas.drawCircle(center, center, radius, this.mBgPaint);
		
		final BigDecimal percentage = BigDecimal.valueOf(this.mProgress).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_DOWN);
		final BigDecimal sweepAngle = percentage.multiply(BigDecimal.valueOf(THREE_SIXTY));

		canvas.drawArc(this.mArcProgress, DrawingTheCircle.TURN_ANGLE, sweepAngle.floatValue(), false, this.mProgressPaint);

		if(this.mTextRectangle == null)
		{
			this.mTextRectangle = new Rect();
			this.mTextPaint.getTextBounds("0", 0, 1, this.mTextRectangle);
		}

		// display text in the center 
		// there should be a better way to this. :( 
		if(mProgress < 10)
		{
			canvas.drawText(String.valueOf(this.mProgress), center, center + (this.mTextRectangle.height() >> 1), this.mTextPaint);
		}
		else if(mProgress >= 10 )
		{
			canvas.drawText(String.valueOf(this.mProgress), this.mTextRectangle.width() * 2   , center + (this.mTextRectangle.height() >> 1), this.mTextPaint);
		}

	}

	@Override
	protected void onMeasure(final int widthMeasureSpec, final int heightMEasureScpec)
	{
		final int size =  (int) this.mDiameter + (this.mLayoutMargin * 2);
		this.setMeasuredDimension(size, size);
	}

	public void setProgress(int progress) {
		this.mProgress = progress;
		// make it redraw like a loop
		this.invalidate();
		
	}


}
