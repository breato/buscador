package com.brunoreato.buscador.scan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.brunoreato.buscador.scan.FileRowIterator;

public class FileRowIteratorTests {

	private BufferedReader br;
	
	@Before
	public void setUp() {
		br = mock(BufferedReader.class);
	}	
	
	@Test
	public void hasNextFalse() throws IOException {
		when(br.readLine()).thenReturn(null);
		
		FileRowIterator fri = new FileRowIterator(br);
		assertFalse(fri.hasNext());
	}
	
	@Test
	public void hasNextTrue() throws IOException {
		when(br.readLine()).thenReturn("ESTA ES UNA LINEA");
		
		FileRowIterator fri = new FileRowIterator(br);
		assertTrue(fri.hasNext());
	}
	
	@Test
	public void nextReturnsLine( ) throws IOException {
		when(br.readLine()).thenReturn("ESTA ES UNA LINEA");
		
		FileRowIterator fri = new FileRowIterator(br);
		assertEquals("ESTA ES UNA LINEA", fri.next());
	}
	
	@Test
	public void nextNoReturnsLineIfNotHasNext( ) throws IOException {
		when(br.readLine()).thenReturn("ESTA ES UNA LINEA");
		
		FileRowIterator fri = new FileRowIterator(br);
		assertEquals("ESTA ES UNA LINEA", fri.next());
		when(br.readLine()).thenReturn(null);
		assertEquals("", fri.next());
	}
}
