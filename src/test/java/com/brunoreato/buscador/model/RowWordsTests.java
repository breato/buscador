package com.brunoreato.buscador.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RowWordsTests {
	
	@Test
	public void addOneWordGet1WordItem() {
		RowWords rw = new RowWords();
		
		rw.addWord("hola", 11);
		
		assertEquals(rw.getWords().size(), 1);
	}
	
	@Test
	public void addSameWordGet2ItemsPosition() {
		RowWords rw = new RowWords();
		
		rw.addWord("hola", 11);
		rw.addWord("hola", 15);
		
		assertEquals(1, rw.getWords().size());
		assertEquals(2, rw.getWords().get("hola").size());
	}
	
	@Test
	public void addDifferentWordsGet2WordItem() {
		RowWords rw = new RowWords();
		
		rw.addWord("hola", 11);
		rw.addWord("hola", 15);
		
		assertEquals(1, rw.getWords().size());
		assertEquals(2, rw.getWords().get("hola").size());
	}
}
