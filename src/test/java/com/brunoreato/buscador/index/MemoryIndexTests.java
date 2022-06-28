package com.brunoreato.buscador.index;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.brunoreato.buscador.FinderConfiguration;
import com.brunoreato.buscador.model.File;
import com.brunoreato.buscador.model.Folder;
import com.brunoreato.buscador.model.Row;
import com.brunoreato.buscador.model.WordOccurrenceList;
import com.brunoreato.buscador.ommit.WordsOmmited;

public class MemoryIndexTests {
	
	private Folder folder;
	private WordsOmmited wordsOmmited;
	private List<File> files;
	private File file1;
	private List<Row> rows;
	private Row row1;
	private Map<String, List<Integer>> words;
	private WordOccurrenceList wol;
	private FinderConfiguration fc;
	
	@Before
	public void setUp() {
		folder = mock(Folder.class);
		fc = mock(FinderConfiguration.class);
		wordsOmmited = mock(WordsOmmited.class);
		files = new ArrayList<File>();
		words = new HashMap<String, List<Integer>>();
		
		file1 = mock(File.class);
		files.add(file1);
		
		rows = new ArrayList<Row>();
		row1 = mock(Row.class);
		rows.add(row1);
		when(folder.getFolder()).thenReturn("C:\\carpeta");
		when(folder.getFiles()).thenReturn(files);
		when(file1.getRows()).thenReturn(rows);
		when(file1.getFileName()).thenReturn("C:\\carpeta\\aa.txt");
		when(row1.getWords()).thenReturn(words);
		when(fc.getWordsOmmited()).thenReturn(wordsOmmited);
		
		words.put("HOLA", List.of(1, 5));
		
		wol = mock(WordOccurrenceList.class);
		
	}

	@Test
	public void buildScanTextFiles() throws FileNotFoundException {
		MemoryIndex mi = new MemoryIndex(folder, fc, wol);
		
		mi.build();
		
		verify(folder, times(1)).getFiles();
		verify(file1, times(1)).getRows();
		verify(row1, times(1)).getWords();
		verify(wordsOmmited, times(1)).isOmmited("HOLA");
		verify(wol, times(1)).add(eq("HOLA"), eq("C:\\carpeta\\aa.txt"), anyInt());
		
	}
	
	@Test
	public void buildCheckIfWordIsOmmited() throws FileNotFoundException {
		MemoryIndex mi = new MemoryIndex(folder, fc, wol);
		
		mi.build();
		
		verify(folder, times(1)).getFiles();
		verify(file1, times(1)).getRows();
		verify(row1, times(1)).getWords();
		
	}
}
