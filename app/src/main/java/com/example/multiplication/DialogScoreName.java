package com.example.multiplication;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class DialogScoreName extends AppCompatActivity {
	private String ratio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent intent = getIntent();
		this.ratio = intent.getStringExtra("score");

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

				// Faire quelque chose avec le nom du joueur (par exemple, l'afficher)
				Toast.makeText(DialogScoreName.this, "Nom du joueur : " + playerName, Toast.LENGTH_SHORT).show();
				FileWriter.writeToFile("highscore.txt",playerName + "\t" + ratio );
				finish();  // Terminer l'activité ici
			}
		});

		btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Annuler le dialogue
				finish();  // Terminer l'activité ici
			}
		});

		// Afficher le dialogue
		builder.create().show();
	}
}
