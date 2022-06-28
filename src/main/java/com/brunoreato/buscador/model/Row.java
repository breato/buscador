package com.brunoreato.buscador.model;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.brunoreato.buscador.FinderConfiguration;
import com.brunoreato.buscador.scan.WordInfo;

public class Row {
	private RowWords words = new RowWords();
	private File file;
	private String rawRow;
	private FinderConfiguration config;
	
	

	public Row(File file, String rawRow, FinderConfiguration config) {
		super();
		this.file = file;
		this.rawRow = rawRow;
		this.config = config;
	//this.words = rowWords;
		
	}
	
	public void searchWords() {
		
		Iterator<WordInfo> wordIterator = config.getWordIterator(rawRow);
		
		while (wordIterator.hasNext()) {
			WordInfo wi = wordIterator.next();
			words.addWord(wi.getWord(), wi.getPosition());
		}
		
			
	}
	
	public Map<String, List<Integer>> getWords() {
		return words.getWords();
	}

	public File getFile() {
		return file;
	}
}
