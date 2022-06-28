package com.brunoreato.buscador.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brunoreato.buscador.ommit.FileWordsOmmited;
import com.brunoreato.buscador.ommit.WordsOmmited;

public class FileWordsOmmitedTests {
	
	private BufferedReader br;
	private int index;
	private List<String> wordsOmmited = List.of("WORD1", "WORD2");
	private List<String> wordsNotOmmited = List.of("WORD3", "WORD4");
	
	@Before
	public void setUp() {
		br = mock(BufferedReader.class);
		index = -1;
	}

	@Test
	public void isOmmitedTrueIfWordExists() throws IOException {
		
		
		when(br.readLine()).then((args) -> {
			index++;
			return index < wordsOmmited.size() ? wordsOmmited.get(index) : null;
		});
		
		WordsOmmited wo = new FileWordsOmmited(br);
		
		for (String word : wordsOmmited) {
			assertTrue(wo.isOmmited(word));
		}
	}
	
	@Test
	public void isOmmitedFalseIfWordNotExists() throws IOException {
		when(br.readLine()).then((args) -> {
			index++;
			return index < wordsOmmited.size() ? wordsOmmited.get(index) : null;
		});
		
		WordsOmmited wo = new FileWordsOmmited(br);
		
		for (String word : wordsNotOmmited) {
			assertFalse(wo.isOmmited(word));
		}
	}
}
