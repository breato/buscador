package com.brunoreato.buscador.scan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class FileRowIterator implements Iterator<String> {
	private BufferedReader br;
	private String nextRow = "";


	
	public FileRowIterator(BufferedReader br) throws FileNotFoundException {
		this.br = br;
	}


	@Override
	public boolean hasNext() {
		try {
			nextRow = br.readLine();
			return nextRow != null;
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}

	@Override
	public String next() {
		String row = "";
		
		if (nextRow != "" || hasNext())
			row = nextRow;
		
		nextRow = "";
		
		return row;
	}
	
	public static FileRowIterator createInstance(String file) throws FileNotFoundException {
		return new FileRowIterator(new BufferedReader(new FileReader(new java.io.File(file))));
	}
}
