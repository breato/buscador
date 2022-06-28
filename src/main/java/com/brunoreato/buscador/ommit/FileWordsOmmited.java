package com.brunoreato.buscador.ommit;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWordsOmmited extends BaseWordsOmmited { 
	private List<String> words;	
	private BufferedReader br;

	public FileWordsOmmited(BufferedReader br) {
		this.br = br;
	}
	
	public List<String> getWords() {
		if (words == null) {
			words = new ArrayList<>();
			String word;
			try {
				while((word = br.readLine()) != null)
					words.add(word.trim());
				
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		return words;
	}
}
