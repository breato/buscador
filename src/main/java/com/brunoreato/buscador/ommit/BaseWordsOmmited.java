package com.brunoreato.buscador.ommit;

import java.util.List;

public abstract class BaseWordsOmmited implements WordsOmmited {

	public abstract List<String> getWords();
	
	public boolean isOmmited(String word) {
		return getWords().contains(word);
	}
}