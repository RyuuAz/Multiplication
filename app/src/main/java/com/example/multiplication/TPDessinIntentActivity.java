package com.example.multiplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TPDessinIntentActivity extends AppCompatActivity {

	// Identifiants des composants de l'interface utilisateur
	private EditText editTextUrl, editTextText, editTextPattern;
	private Button buttonOpenBrowser, buttonCountOccurrences, buttonOpenDrawingActivity, buttonExit;
	private TextView textViewOccurrences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tp_dessin_intent);

		// Initialisation des composants
		editTextUrl = findViewById(R.id.editTextUrl);
		editTextText = findViewById(R.id.editTextText);
		editTextPattern = findViewById(R.id.editTextPattern);
		buttonOpenBrowser = findViewById(R.id.buttonOpenBrowser);
		buttonCountOccurrences = findViewById(R.id.buttonCountOccurrences);
		buttonOpenDrawingActivity = findViewById(R.id.buttonOpenDrawingActivity);
		buttonExit = findViewById(R.id.buttonExit);
		textViewOccurrences = findViewById(R.id.textViewOccurrences);

		// Action lors du clic sur le bouton "Ouvrir dans le navigateur"
		buttonOpenBrowser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String url = editTextUrl.getText().toString();
				openBrowser(url);
			}
		});

		// Action lors du clic sur le bouton "Compter les occurrences"
		buttonCountOccurrences.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = editTextText.getText().toString();
				String pattern = editTextPattern.getText().toString();
				int occurrences = countOccurrences(text, pattern);
				textViewOccurrences.setText("Occurrences: " + occurrences);
			}
		});

		// Action lors du clic sur le bouton "Lancer l'Activity de dessin"
		buttonOpenDrawingActivity.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openDrawingActivity();
			}
		});

		// Action lors du clic sur le bouton "Quitter"
		buttonExit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	// Méthode pour ouvrir le navigateur avec l'URL donnée
	private void openBrowser(String url) {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		startActivity(intent);
	}

	// Méthode pour compter les occurrences du motif dans le texte
	private int countOccurrences(String text, String pattern) {
		int count = 0;
		int index = 0;
		while ((index = text.indexOf(pattern, index)) != -1) {
			count++;
			index += pattern.length();
		}
		return count;
	}

	// Méthode pour ouvrir l'Activity de dessin
	private void openDrawingActivity() {
		Intent intent = new Intent(this, TPDSIDessinActivity.class);
		startActivity(intent);
	}

}
