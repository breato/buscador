package com.brunoreato.buscador.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WordOccurrencesTests {

	public final String FILENAME = "c:\\a.txt";
	
	@Test
	public void incrementNoCallOccurrences1() {
		WordOccurrences wo = new WordOccurrences(FILENAME, 5);
		
		
		assertEquals(5, wo.getOcurrences());
	}
	
	@Test
	public void callIncrementOnceGet2Occurrences() {
		WordOccurrences wo = new WordOccurrences(FILENAME, 3);
		wo.increment(2);
		
		assertEquals(5, wo.getOcurrences());
	}
	
	@Test
	public void getFileName() {
		WordOccurrences wo = new WordOccurrences(FILENAME, 7);

		
		assertEquals(FILENAME, wo.getFile());
	}
}
