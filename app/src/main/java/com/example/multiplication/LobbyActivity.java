package com.example.multiplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LobbyActivity extends AppCompatActivity {

	private String difficulty = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lobby);

		Intent intent = getIntent();
		this.difficulty = intent.getStringExtra("diff");


	}

	public void start(View view) {

		Intent intent = new Intent(LobbyActivity.this, MainActivity.class);
		intent.putExtra("diff", difficulty);
		startActivity(intent);
	}
}
