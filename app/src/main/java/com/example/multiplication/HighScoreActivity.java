package com.example.multiplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class HighScoreActivity extends AppCompatActivity {
	private String difficulty = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_highscore);

		Intent intent = getIntent();
		this.difficulty = intent.getStringExtra("diff");

		TextView theDifficulty = findViewById(R.id.diff);
		theDifficulty.setText(this.difficulty);

		// Récupérer la ListView
		ListView highScoresListView = findViewById(R.id.highScoresListView);

		// Récupérer la liste des highscores
		List<HighScoreManager.HighScoreEntry> highScores = HighScoreManager.getHighScores(this, difficulty);

		// Créer un tableau de chaînes pour stocker les données à afficher
		String[] highScoreStrings = new String[highScores.size()];
		for (int i = 0; i < highScores.size(); i++) {
			HighScoreManager.HighScoreEntry entry = highScores.get(i);
			highScoreStrings[i] = entry.getPlayerName() + " - Score: " + entry.getScore() + " - Tentatives: " + entry.getAttempts();
		}

		// Utiliser un ArrayAdapter personnalisé pour spécifier la couleur du texte en blanc
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, highScoreStrings) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				TextView textView = view.findViewById(android.R.id.text1);
				textView.setTextColor(Color.WHITE);
				return view;
			}
		};

		// Appliquer l'adaptateur personnalisé à la ListView
		highScoresListView.setAdapter(adapter);
	}
}
