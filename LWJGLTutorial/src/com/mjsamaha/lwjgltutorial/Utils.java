package com.mjsamaha.lwjgltutorial;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
	
	private Utils() {
		
	}
	
	public static String readFile(String fp) {
		String str;
		
		try {
			str = new String(Files.readAllBytes(Paths.get(fp)));
		} catch (IOException excp) {
			throw new RuntimeException("Error reading file[" + fp + "]", excp);
		}
		return str;
	}

}
