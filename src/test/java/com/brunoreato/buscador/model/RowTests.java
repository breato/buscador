package com.brunoreato.buscador.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.brunoreato.buscador.FinderConfiguration;
import com.brunoreato.buscador.scan.RIWordIterator;

public class RowTests {
	
	private class RowTestInfo {
		
		private String row;
		private String searchWord;
		private int occurrences;
		
		public RowTestInfo(String row, String searchWord, int occurrences) {
			super();
			this.row = row;
			this.occurrences = occurrences;
			this.searchWord = searchWord;
		}
		
		public String getRow() {
			return row;
		}
		
		public int getOccurrences() {
			return occurrences;
		}

		public String getSearchWord() {
			return searchWord;
		}
		
	}

	private File file;
	private RowWords rowWords;
	private Map<String, List<Integer>> words;
	private FinderConfiguration fc;
	
	private List<RowTestInfo> rowTestList = List.of(
			new RowTestInfo("En realidad la viejecita era una bruja que lo que quería era hacerlos trabajar. La niña tenía que cocinar y hacer la limpieza, pero a Hansel lo querían para comérselo, por lo que lo metió en una jaula y comenzó a darle de comer.", "que", 4),
			new RowTestInfo("Los niños se quedaron durmiendo, pero al despertar fueron a buscar el camino de migas de pan para regresar a su casa. Por más que buscaban no lo encontraban, ya que los pájaros se lo habían comido. Estuvieron andando mucho tiempo hasta que encontraron una casa hecha de galletas y caramelos. Era tal el hambre que tenían que se acercaron a ella, pero de repente apareció una anciana que los invitó a pasar y les ofreció comida. Seguidamente les preparó la cama para que durmiesen.", "que", 7),
			new RowTestInfo("En un lugar muy lejano vivió un leñador con su esposa y sus dos hijos que se llamaban Hansel y Gretel. Se trataba de una familia muy pobre que apenas ganaba dinero para comer. Llegó un día en el que no tenían nada, ni tan siquiera para comprar algo de comida ni harina para hacer pan, así que el matrimonio se empezó a preocupar porque pensaba que sus hijos iban a morir de hambre.", "que", 5)
			
			);
	
	
	
	@Before
	public void setUp() throws FileNotFoundException {
		words = new HashMap<>();
		file = mock(File.class);
		rowWords = mock(RowWords.class);
		when(rowWords.getWords()).thenReturn(words);
		fc = mock(FinderConfiguration.class);		
	}
	
	@Test
	public void getFileOk() {
		Row rw = new Row(file, "", fc);
		
		assertEquals(file, rw.getFile());
	}
	
	@Test
	public void getRowWordsOk() {
		Row rw = new Row(file, "", fc);
		
		assertEquals(rowWords.getWords(), rw.getWords());
	}
	
	@Test
	public void getWordsCountOk() {
		when(fc.getWordIterator("HOLA MUNDO")).thenReturn(new RIWordIterator("HOLA MUNDO"));
		Row r = new Row(file, "HOLA MUNDO", fc);
		r.searchWords();
		
		assertEquals(2, r.getWords().size());
		assertNotNull(r.getWords().get("HOLA"));
		assertNotNull(r.getWords().get("MUNDO"));
		assertEquals(1, r.getWords().get("HOLA").size());
		assertEquals(1, r.getWords().get("MUNDO").size());
	}
	
	@Test
	public void getWordsCountOkSpecialChars() {
		when(fc.getWordIterator("HOLA MUNDO!!!!!")).thenReturn(new RIWordIterator("HOLA MUNDO!!!!!"));
		Row r = new Row(file, "HOLA MUNDO!!!!!", fc);
		r.searchWords();
		
		assertEquals(2, r.getWords().size());
		assertNotNull(r.getWords().get("HOLA"));
		assertNotNull(r.getWords().get("MUNDO"));
		assertEquals(1, r.getWords().get("HOLA").size());
		assertEquals(1, r.getWords().get("MUNDO").size());
	}
	
	@Test
	public void getWordsCountOkAcentos() {
		when(fc.getWordIterator("Atención ríe")).thenReturn(new RIWordIterator("Atención ríe"));
		Row r = new Row(file, "Atención ríe", fc);
		r.searchWords();
		
		assertEquals(2, r.getWords().size());
		assertNotNull(r.getWords().get("Atención"));
		assertNotNull(r.getWords().get("ríe"));
		assertEquals(1, r.getWords().get("Atención").size());
		assertEquals(1, r.getWords().get("ríe").size());
	}
	
	@Test
	public void testSearchRowExamples() {
		for (RowTestInfo rowTestInfo : rowTestList) {
			String rowText = rowTestInfo.getRow();
			String word = rowTestInfo.getSearchWord();
			int occurrences = rowTestInfo.getOccurrences();
			
			when(fc.getWordIterator(rowText)).thenReturn(new RIWordIterator(rowText));
			Row r = new Row(file, rowText, fc);
			r.searchWords();
			
			assertNotNull(r.getWords().get(word));
			assertEquals(occurrences, r.getWords().get(word).size());
		}
	}
}
