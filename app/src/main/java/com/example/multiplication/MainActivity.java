package com.example.multiplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

	private int x;
	private int y;
	private String sCalcul;
	private int nbOfCalls = 0;
	private int score = 0;
	private String ratio;
	private int nbOperation = 0;

	private String difficulty = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent intent = getIntent();
		this.difficulty = intent.getStringExtra("diff");

		/*
		nbOfCalls++;
		Toast myToast;
		myToast = Toast.makeText(this, new String("onCreate() " + nbOfCalls ), Toast.LENGTH_LONG);
		myToast.setGravity(Gravity.CENTER, 0, 0);
		myToast.show();
		*/

		TextView theOperation = (TextView) findViewById(R.id.operation) ;
		if(savedInstanceState != null){
			theOperation.setText(savedInstanceState.getString("sCal")) ;
		}
		else {
			switch (difficulty){
				case "Facile" :
					this.x = (int) (Math.random() * 9 + 1);
					this.y = (int) (Math.random() * 9 + 1);
					sCalcul = this.x + " x " + this.y + " = ";
					theOperation.setText(sCalcul) ;
					break;
				case "Moyen" :
					this.x = (int) (Math.random() * 9 * 10 + Math.random() * 9 );
					this.y = (int) (Math.random() * 9 * 10 + Math.random() * 9 );
					sCalcul = this.x + " x " + this.y + " = ";
					theOperation.setText(sCalcul) ;
					break;
				case "Difficile" :
					this.x = (int) (Math.random() * 9 * 100 + Math.random() * 9 * 10 + Math.random() * 9 );
					this.y = (int) (Math.random() * 9 * 100 + Math.random() * 9 * 10 + Math.random() * 9 );
					sCalcul = this.x + " x " + this.y + " = ";
					theOperation.setText(sCalcul) ;
					break;
			}

		}

		start();
	}

	public void onSaveInstanceState(Bundle bagOfData){
		bagOfData.putInt("nb_calls", nbOfCalls);
		bagOfData.putString("sCal", sCalcul);
		super.onSaveInstanceState(bagOfData);
	}

	public void onRestoreInstanceState(Bundle bagOfData){
		super.onRestoreInstanceState(bagOfData);
		nbOfCalls = bagOfData.getInt("nb_calls");
		sCalcul = bagOfData.getString("sCal");
	}

	public void verifyOperation(View view){
		int res = this.x * this.y;
		EditText answerEditText = findViewById(R.id.answer);
		TextView messageTextView = findViewById(R.id.message);
		try {

			int userInput = Integer.parseInt(answerEditText.getText().toString());

			if ( res == userInput  ){
				messageTextView.setText("Bravo");
				score++;
				reset(findViewById(R.id.reset));

			}
			else {
				messageTextView.setText("C'est faux" + "\n" + "La réponse : " + res);
				reset(findViewById(R.id.reset));
			}

		}catch (NumberFormatException e) {
			messageTextView.setText("Veuillez entrez une réponse");
		}

	}

	public void close(View view){
		finish();
	}

	public void reset(View view){
		EditText answerEditText = findViewById(R.id.answer);
		answerEditText.setText("");
		TextView theOperation = (TextView) findViewById(R.id.operation) ;
		nbOperation++;

		TextView theScore = (TextView) findViewById(R.id.score);
		this.ratio = score + "/" + nbOperation;
		theScore.setText(this.ratio);

		switch (difficulty){
			case "Facile" :
				this.x = (int) (Math.random() * 9 + 1);
				this.y = (int) (Math.random() * 9 + 1);
				sCalcul = this.x + " x " + this.y + " = ";
				theOperation.setText(sCalcul) ;
				break;
			case "Moyen" :
				this.x = (int) (Math.random() * 9 * 10 + Math.random() * 9 );
				this.y = (int) (Math.random() * 9 * 10 + Math.random() * 9 );
				sCalcul = this.x + " x " + this.y + " = ";
				theOperation.setText(sCalcul) ;
				break;
			case "Difficile" :
				this.x = (int) (Math.random() * 9 * 100 + Math.random() * 9 * 10 + Math.random() * 9 );
				this.y = (int) (Math.random() * 9 * 100 + Math.random() * 9 * 10 + Math.random() * 9 );
				sCalcul = this.x + " x " + this.y + " = ";
				theOperation.setText(sCalcul) ;
				break;
		}
	}

	public void start(){
		TextView timer = (TextView) findViewById(R.id.timer);
		new CountDownTimer(3000, 1000) { // 60 secondes, avec une mise à jour chaque seconde (1000 millisecondes)
			public void onTick(long millisUntilFinished) {
				// Ce code est exécuté à chaque seconde pendant le compte à rebours
				String secondesRestantes = String.valueOf(millisUntilFinished / 1000);
				// Faites quelque chose avec les secondes restantes, comme les afficher dans une TextView
				timer.setText(secondesRestantes);
			}

			public void onFinish() {
				// Ce code est exécuté lorsque le compte à rebours est terminé
				timer.setText("Fini");
				Intent intent = new Intent(MainActivity.this, DialogScoreName.class);
				intent.putExtra("score", score);
				intent.putExtra("nbOperation", nbOperation);
				intent.putExtra("diff", difficulty);
				startActivity(intent);
				finish();

			}
		}.start();
	}

	/*
	protected void onStart(){
		super.onStart();
		nbOfCalls++;
		Toast myToast;
		myToast = Toast.makeText(this, new String("onStart() " + nbOfCalls ), Toast.LENGTH_LONG);
		myToast.setGravity(Gravity.CENTER, 0, 0);
		myToast.show();
	}

	protected void onRestart(){
		super.onRestart();
		nbOfCalls++;
		Toast myToast;
		myToast = Toast.makeText(this, new String("onRestart() " + nbOfCalls ), Toast.LENGTH_LONG);
		myToast.setGravity(Gravity.CENTER, 0, 0);
		myToast.show();
	}

	protected void onResume(){
		super.onResume();
		nbOfCalls++;
		Toast myToast;
		myToast = Toast.makeText(this, new String("onResume() " + nbOfCalls ), Toast.LENGTH_LONG);
		myToast.setGravity(Gravity.CENTER, 0, 0);
		myToast.show();
	}

	protected void onPause(){
		super.onPause();
		nbOfCalls++;
		Toast myToast;
		myToast = Toast.makeText(this, new String("onPause() " + nbOfCalls ), Toast.LENGTH_LONG);
		myToast.setGravity(Gravity.CENTER, 0, 0);
		myToast.show();
	}

	protected void onStop(){
		super.onStop();
		nbOfCalls++;
		Toast myToast;
		myToast = Toast.makeText(this, new String("onStop() " + nbOfCalls ), Toast.LENGTH_LONG);
		myToast.setGravity(Gravity.CENTER, 0, 0);
		myToast.show();
	}

	protected void onDestroy(){
		super.onDestroy();
		nbOfCalls++;
		Toast myToast;
		myToast = Toast.makeText(this, new String("onDestroy() " + nbOfCalls ), Toast.LENGTH_LONG);
		myToast.setGravity(Gravity.CENTER, 0, 0);
		myToast.show();
	}
	*/

}