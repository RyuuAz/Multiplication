package com.example.multiplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

	}


	public void changeToMain(View view){
		// Obtenez le bouton cliqué
		Button boutonClique = (Button) view;

		// Obtenez le texte du bouton
		String difficulty = boutonClique.getText().toString();

		Intent intent = new Intent(HomeActivity.this, LobbyActivity.class);
		intent.putExtra("diff", difficulty);
		startActivity(intent);
	}

}
