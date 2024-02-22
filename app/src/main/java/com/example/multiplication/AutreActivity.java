package com.example.multiplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AutreActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_autre);

	}


	public void changeToDessin(View view){
		// Obtenez le bouton cliqué
		Button boutonClique = (Button) view;

		// Obtenez le texte du bouton
		String difficulty = boutonClique.getText().toString();

		Intent intent = new Intent(AutreActivity.this, DessinActivity.class);
		startActivity(intent);
	}

	public void changeToTPDessinIntent(View view){
		// Obtenez le bouton cliqué
		Button boutonClique = (Button) view;

		// Obtenez le texte du bouton
		String difficulty = boutonClique.getText().toString();

		Intent intent = new Intent(AutreActivity.this, TPDessinIntentActivity.class);
		startActivity(intent);
	}

}
