package com.brunoreato.buscador.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.brunoreato.buscador.FinderConfiguration;
import com.brunoreato.buscador.scan.RIWordIterator;
import com.brunoreato.buscador.utils.ArrayRowIterator;

public class FileTests {
	
	private File file;
	private Folder folder;
	private ArrayRowIterator iterator;
	
	@Before
	public void setUp() throws FileNotFoundException {
		FinderConfiguration fc = mock(FinderConfiguration.class);
		java.io.File f = mock(java.io.File.class);
		folder = new Folder(f, fc);
		List<String> arrStrList = new ArrayList<>();
		arrStrList.add("Hola mundo");
		arrStrList.add("Hola mundo 2");
		
		iterator = spy(new ArrayRowIterator(arrStrList));
		
		
		when(fc.getRowIterator(any())).thenReturn(iterator);
		when(fc.getWordIterator(any())).thenReturn(new RIWordIterator(""));
		
		file = new File(folder, "", fc);
	}

	@Test
	public void searchRowsGenerate1RowInArray() throws FileNotFoundException {
		file.searchRows();
		
		verify(iterator, times(2)).next();
		assertEquals(2, file.getRows().size());
	}
}
