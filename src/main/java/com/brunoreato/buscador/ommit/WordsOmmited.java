package com.brunoreato.buscador.ommit;

import java.util.List;

public interface WordsOmmited {
	boolean isOmmited(String word);
	List<String> getWords();
}
