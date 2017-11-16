package org.grupo21.Utils;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class FileUtils {

	private final static Logger LOGGER = Logger.getLogger(FileUtils.class.getName());

	public String getFile(String fileName) {
		StringBuilder result = new StringBuilder("");
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			LOGGER.info("El archivo " + fileName + " no existe");
		}
		return result.toString();
	}

	public void setFile(String fileName, String content) {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"))) {
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
