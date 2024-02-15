package com.example.multiplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DialogScoreName extends AppCompatActivity {

	private int score;
	private int nbOperation;

	private String difficulty;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent intent = getIntent();
		this.score = intent.getIntExtra("score", 0);
		this.nbOperation = intent.getIntExtra("nbOperation", 0);
		this.difficulty = intent.getStringExtra("diff");

		// Appelez showCustomInputDialog lorsque vous le souhaitez, par exemple dans onCreate
		showCustomInputDialog(DialogScoreName.this);
	}

	// Méthode pour afficher le dialogue personnalisé demandant le nom du joueur
	private void showCustomInputDialog(Context context) {
		// Utiliser AlertDialog.Builder pour construire le dialogue
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Entrer le nom du joueur");

		// Utiliser LayoutInflater pour créer une vue personnalisée
		LayoutInflater inflater = getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.dialog_score_name, null);
		builder.setView(dialogView);

		// Récupérer les vues de la vue personnalisée
		EditText etPlayerName = dialogView.findViewById(R.id.name);
		Button btnOK = dialogView.findViewById(R.id.btnOK);
		Button btnCancel = dialogView.findViewById(R.id.btnCancel);

		// Définir les boutons du dialogue
		btnOK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Récupérer le nom du joueur entré par l'utilisateur
				String playerName = etPlayerName.getText().toString();

				// Ajouter le score avec le nom du joueur, le score et le nombre de tentatives
				HighScoreManager.addScore(context, playerName, score, nbOperation, difficulty);

				// Terminer l'activité ici
				finish();
			}
		});

		btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Annuler le dialogue et terminer l'activité
				finish();
			}
		});

		// Afficher le dialogue
		builder.create().show();
	}
}
