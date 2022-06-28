package com.brunoreato.buscador.model;

import java.io.Serializable;

public class WordOccurrences implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2397065370104621263L;
	private String file;
	private int ocurrences;
	
	public WordOccurrences(String file, int occurrences) {
		super();
		this.file = file;
		this.ocurrences = occurrences;
	}

	public String getFile() {
		return file;
	}

	public int getOcurrences() {
		return ocurrences;
	}
	
	public void increment(int occurrences) {
		this.ocurrences += occurrences;
	}
	
}
