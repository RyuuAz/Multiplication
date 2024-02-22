package com.example.multiplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DessinActivity extends AppCompatActivity {

	MyOwnView whatIdraw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		whatIdraw = new MyOwnView(this);
		setContentView(whatIdraw);

	}

	class MyOwnView extends View implements View.OnTouchListener, View.OnClickListener {

		Paint firstWayToDraw = new Paint();
		Paint secondWayToDraw = new Paint();

		float x,y,radius;
		Random rd = new Random();

		Vibrator vibor = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE) ;

		public MyOwnView (Context context){
			super(context);
			setFocusable(true);
			firstWayToDraw.setColor(Color.RED);
			firstWayToDraw.setAntiAlias(true);
			firstWayToDraw.setStyle(Paint.Style.STROKE);
			firstWayToDraw.setStrokeWidth(5);
			radius = 50;
			this.setOnTouchListener(this);
			this.setOnClickListener(this);
		}
		public void onDraw(Canvas canvas) {
			canvas.drawColor(Color.BLACK);
			/*
			Bitmap resizedImage = DessinActivity.resizeImage(getResources(), R.drawable.test, 100, 100);
			if (resizedImage != null)
				canvas.drawBitmap(resizedImage, 100, 100, null);

			 */
			canvas.drawCircle(x, y, radius, firstWayToDraw);

		}

		public boolean onTouch(View v, MotionEvent event){
			x = event.getX();
			y = event.getY();
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
				vibor.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
			}
			this.invalidate();
			return false;
		}

		public void onClick(View v){
			firstWayToDraw.setColor(Color.rgb(rd.nextInt(200), rd.nextInt(240), rd.nextInt(180)));
			this.invalidate();
		}


	}

	public static Bitmap resizeImage(Resources res, int resourceId, int targetWidth, int targetHeight) {
		// Charger l'image depuis les ressources
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resourceId, options);

		// Calculer les facteurs d'échelle pour redimensionner l'image
		int imageWidth = options.outWidth;
		int imageHeight = options.outHeight;
		int scaleFactor = Math.min(imageWidth / targetWidth, imageHeight / targetHeight);

		// Définir les options pour décoder l'image avec un facteur d'échelle approprié
		options.inJustDecodeBounds = false;
		options.inSampleSize = scaleFactor;

		// Charger l'image redimensionnée
		return BitmapFactory.decodeResource(res, resourceId, options);
	}

}

