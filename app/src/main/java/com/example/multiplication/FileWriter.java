package com.example.multiplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class FileWriter {

	static void writeToFile(String filePath, String content) {
		try {
			// Créer un objet File associé au chemin du fichier
			File file = new File(filePath);

			// Vérifier si le fichier n'existe pas et le créer si nécessaire
			if (!file.exists()) {
				file.createNewFile();
			}

			// Initialiser FileWriter avec le fichier
			java.io.FileWriter fw = new java.io.FileWriter(file.getAbsoluteFile());

			// Initialiser BufferedWriter pour écrire dans le fichier
			BufferedWriter bw = new BufferedWriter(fw);

			// Écrire le contenu dans le fichier
			bw.write(content);

			// Fermer le BufferedWriter pour libérer les ressources
			bw.close();

			System.out.println("Écriture dans le fichier réussie.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
