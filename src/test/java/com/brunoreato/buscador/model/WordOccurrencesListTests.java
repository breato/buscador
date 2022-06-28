package com.brunoreato.buscador.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class WordOccurrencesListTests {

	private Map<String, List<WordOccurrences>> words;
	private WordOccurrenceList wol;
	
	@Before
	public void setUp() {
		words = spy(new HashMap<String, List<WordOccurrences>>());		
		wol = new WordOccurrenceList(words);
	}
	
	@Test
	public void addOneWhenSameWord() {
		wol.add("HOLA", "c:\\a.txt", 1);
		wol.add("HOLA", "c:\\a.txt", 2);
		
		// Verificar que se haya guardado solo 1 vez la palabra HOLA en el Map
		verify(words, times(1)).put(eq("HOLA"), anyList());
		
		List<WordOccurrences> occ = words.get("HOLA");
		
		// Verificar que tenga 1 elemento la lista de ocurrencias de la palabra HOLA y 2 ocurrencias
		assertEquals(1, occ.size());
		assertEquals(3, occ.get(0).getOcurrences());
	}
	
	@Test
	public void addCaseInsensitive() {
		wol.add("HOLA", "c:\\a.txt", 2);
		wol.add("hola", "c:\\a.txt", 5);
		
		// Verificar que se haya guardado solo 1 vez la palabra HOLA en el Map
		verify(words, times(1)).put(eq("HOLA"), anyList());
		
		List<WordOccurrences> occ = words.get("HOLA");
		
		// Verificar que tenga 1 elemento la lista de ocurrencias de la palabra HOLA y 2 ocurrencias
		assertEquals(1, occ.size());
		assertEquals(7, occ.get(0).getOcurrences());
	}
	
	@Test
	public void searchCaseInsensitive() {
		wol.add("Hola", "c:\\a.txt", 2);
		wol.add("hola", "c:\\a.txt", 2);
		
		// Verificar que se haya guardado solo 1 vez la palabra HOLA en el Map
		verify(words, times(1)).put(eq("HOLA"), anyList());
		
		List<WordOccurrences> wo = wol.search("hola");
		
		// Verificar que tenga 1 elemento la lista de ocurrencias de la palabra HOLA y 2 ocurrencias
		assertEquals(1, wo.size());
		assertEquals(4, wo.get(0).getOcurrences());
	}
}
