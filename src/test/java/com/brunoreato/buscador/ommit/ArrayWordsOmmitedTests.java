package com.brunoreato.buscador.ommit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.brunoreato.buscador.ommit.ArrayWordsOmmited;
import com.brunoreato.buscador.ommit.WordsOmmited;

public class ArrayWordsOmmitedTests {
	
	@Test
	public void isOmmitedTrueIfWordExists() {
		List<String> wordsOmmited = List.of("WORD1", "WORD2");
		WordsOmmited wo = new ArrayWordsOmmited(wordsOmmited);
		
		for (String word : wordsOmmited) {
			assertTrue(wo.isOmmited(word));
		}
	}
	
	@Test
	public void isOmmitedFalseIfWordNotExists() {
		List<String> wordsOmmited = List.of("WORD1", "WORD2");
		List<String> wordsNotOmmited = List.of("WORD3", "WORD4");
		WordsOmmited wo = new ArrayWordsOmmited(wordsOmmited);
		
		for (String word : wordsNotOmmited) {
			assertFalse(wo.isOmmited(word));
		}
	}

}
