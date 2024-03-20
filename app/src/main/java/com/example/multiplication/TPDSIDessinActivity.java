package com.example.multiplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TPDSIDessinActivity extends AppCompatActivity {

	private static DrawingView drawingView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		drawingView = new DrawingView(this);
		setContentView(drawingView);

		// Action lors du clic sur la vue de dessin
		drawingView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handleDrawingClick();
			}
		});
	}

	// Méthode pour traiter le clic sur la vue de dessin
	private void handleDrawingClick() {
		// Ajoutez ici la logique pour créer un nouveau cercle sur le clic
		// Vérifiez également si le clic est à l'intérieur d'un cercle existant
		if (drawingView.isInsideCircle()) {
			// L'utilisateur a cliqué à l'intérieur d'un cercle existant, terminez l'activité
			finish();
		} else {
			// L'utilisateur a cliqué à l'extérieur de tous les cercles, ajoutez un nouveau cercle
			drawingView.addCircle();
		}
	}

	public class DrawingView extends View {

		private List<Circle> circles = new ArrayList<>();
		private Paint paint, textPaint;
		private float x, y;

		public DrawingView(Context context) {
			super(context);
			init();
		}

		public DrawingView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init();
		}

		private void init() {
			paint = new Paint();
			paint.setColor(Color.RED);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(5);

			textPaint = new Paint();
			textPaint.setColor(Color.BLACK);

		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			// Dessinez tous les cercles existants
			for (Circle circle : circles) {
				canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), paint);
			}

			// Affichez un message si aucun cercle n'est présent
			if (circles.isEmpty()) {
				// Vous pouvez personnaliser le message ici
				textPaint.setTextSize(100);
				canvas.drawText("Il n'y a aucun cercle", getWidth() / 4, getHeight() / 2, textPaint);
			}
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			x = event.getX();
			y = event.getY();
			// Vous pouvez également gérer le clic ici si nécessaire
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				handleDrawingClick();
			}
			return true;
		}

		// Méthode pour ajouter un nouveau cercle
		public void addCircle() {
			Circle newCircle = new Circle(x, y, 40); // Vous pouvez ajuster le rayon selon vos besoins
			circles.add(newCircle);
			invalidate(); // Demande de redessiner la vue
		}

		// Méthode pour vérifier si le clic est à l'intérieur d'un cercle existant
		public boolean isInsideCircle() {
			for (Circle circle : circles) {
				float distance = (float) Math.sqrt(Math.pow(x - circle.getX(), 2) + Math.pow(y - circle.getY(), 2));
				if (distance <= circle.getRadius()) {
					// Le clic est à l'intérieur d'un cercle existant
					return true;
				}
			}
			// Le clic n'est à l'intérieur d'aucun cercle
			return false;
		}
	}

	// Classe pour représenter un cercle
	public static class Circle {
		private float x, y, radius;

		public Circle(float x, float y, float radius) {
			this.x = x;
			this.y = y;
			this.radius = radius;
		}

		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}

		public float getRadius() {
			return radius;
		}
	}
}
