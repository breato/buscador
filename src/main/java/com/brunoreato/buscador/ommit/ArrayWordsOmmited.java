package com.brunoreato.buscador.ommit;

import java.util.List;

public class ArrayWordsOmmited extends BaseWordsOmmited {
	private List<String> words;

	public ArrayWordsOmmited(List<String> words) {
		this.words = words;
	}
	
	public List<String> getWords() {
		return words;
	}
	
	public static ArrayWordsOmmited newInstance( ) {
		return new ArrayWordsOmmited(List.of("a", "ante", "bajo", "cabe", "con", "contra", "de", "desde"));	
		
	}
	
}
