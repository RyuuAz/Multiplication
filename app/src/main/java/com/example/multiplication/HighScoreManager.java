package com.example.multiplication;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HighScoreManager {

	private static final String FILE_NAME_PREFIX = "highscores_";
	private static final String FILE_EXTENSION = ".txt";

	// Ajouter un nouveau score à la liste des highscores
	public static void addScore(Context context, String playerName, int score, int attempts, String difficulty) {
		List<HighScoreEntry> highScores = loadHighScores(context, difficulty);

		HighScoreEntry newScoreEntry = new HighScoreEntry(playerName, score, attempts);
		highScores.add(newScoreEntry);

		// Trier la liste des highscores par ordre décroissant
		Collections.sort(highScores, Collections.reverseOrder());

		// Sauvegarder la nouvelle liste des highscores
		saveHighScores(context, highScores, difficulty);
	}

	// Récupérer la liste des meilleurs scores pour une difficulté donnée
	public static List<HighScoreEntry> getHighScores(Context context, String difficulty) {
		return loadHighScores(context, difficulty);
	}

	// Effacer tous les scores pour une difficulté donnée
	public static void clearHighScores(Context context, String difficulty) {
		List<HighScoreEntry> emptyList = new ArrayList<>();
		saveHighScores(context, emptyList, difficulty);
	}

	// Charger la liste des highscores depuis le fichier pour une difficulté donnée
	private static List<HighScoreEntry> loadHighScores(Context context, String difficulty) {
		List<HighScoreEntry> highScores = new ArrayList<>();

		try {
			File file = new File(context.getFilesDir(), FILE_NAME_PREFIX + difficulty + FILE_EXTENSION);

			if (file.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;

				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(",");
					if (parts.length == 3) {
						String playerName = parts[0].trim();
						int score = Integer.parseInt(parts[1].trim());
						int attempts = Integer.parseInt(parts[2].trim());
						HighScoreEntry entry = new HighScoreEntry(playerName, score, attempts);
						highScores.add(entry);
					}
				}

				reader.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return highScores;
	}

	// Sauvegarder la liste des highscores dans le fichier pour une difficulté donnée
	private static void saveHighScores(Context context, List<HighScoreEntry> highScores, String difficulty) {
		try {
			File file = new File(context.getFilesDir(), FILE_NAME_PREFIX + difficulty + FILE_EXTENSION);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			for (HighScoreEntry entry : highScores) {
				writer.write(entry.getPlayerName() + "," + entry.getScore() + "," + entry.getAttempts());
				writer.newLine();
			}

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Classe représentant une entrée de highscore
	public static class HighScoreEntry implements Comparable<HighScoreEntry> {
		private String playerName;
		private int score;
		private int attempts;

		public HighScoreEntry(String playerName, int score, int attempts) {
			this.playerName = playerName;
			this.score = score;
			this.attempts = attempts;
		}

		public String getPlayerName() {
			return playerName;
		}

		public int getScore() {
			return score;
		}

		public int getAttempts() {
			return attempts;
		}

		@Override
		public int compareTo(HighScoreEntry other) {
			return Integer.compare(this.score, other.score);
		}
	}
}
