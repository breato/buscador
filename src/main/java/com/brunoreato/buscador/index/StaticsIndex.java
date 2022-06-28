package com.brunoreato.buscador.index;

import java.io.Serializable;

public class StaticsIndex implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 448302689724559321L;
	private int filesCount;
	private int rowsCount;
	private int totalWordsCount;
	private int validWordsCount;
	
	public final int getFilesCount() {
		return filesCount;
	}
	
	public final int getRowsCount() {
		return rowsCount;
	}
	
	public final int getTotalWordsCount() {
		return totalWordsCount;
	}
	
	public final int getValidWordsCount() {
		return validWordsCount;
	}
	
	public void incrementFilesCount() {
		this.filesCount++;
	}
	
	public void incrementRowsCount() {
		this.rowsCount++;
	}
	
	public void incrementTotalWordsCount() {
		this.totalWordsCount++;
	}
	
	public void incrementValidWordsCount() {
		this.validWordsCount++;
	}
	
	public void resetStats() {
		this.filesCount = 0;
		this.rowsCount = 0;
		this.totalWordsCount = 0;
		this.validWordsCount = 0;
	}
	
	
}
