package com.brunoreato.buscador.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.brunoreato.buscador.FinderConfiguration;

public class File {
	private List<Row> rows = new ArrayList<Row>();
	private Folder folder;
	private String fileName;
	private FinderConfiguration config;

	public File(Folder folder, String filename, FinderConfiguration config) {
		super();
		this.folder = folder;
		this.fileName = filename;
		this.config = config;
		
	}
	
	public void searchRows() throws FileNotFoundException {
		
		Iterator<String> rowIterator = config.getRowIterator(new java.io.File(fileName));
		
		String row;
		while (rowIterator.hasNext()) {
			row = rowIterator.next();
			Row r = new Row(this, row, config);
			r.searchWords();
			
			rows.add(r);
		}
	}

	public List<Row> getRows() {
		return rows;
	}

	public Folder getFolder() {
		return folder;
	}

	public String getFileName() {
		return fileName;
	}
}
