package com.brunoreato.buscador.ui;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.brunoreato.buscador.model.WordOccurrences;

public class FindWordResultModel extends DefaultTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5157408813378786689L;
	private List<WordOccurrences> wordOccurrences;

	public FindWordResultModel(List<WordOccurrences> wordOccurrences) {
		super();
		this.wordOccurrences = wordOccurrences;
	}

	@Override
	public int getRowCount() {
		return wordOccurrences == null ? 0 : wordOccurrences.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Archivo";

		case 1:
			return "Cant. ocurrencias";
			
		default:
			return "";
		}
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return wordOccurrences.get(row).getFile();
		case 1:
			return wordOccurrences.get(row).getOcurrences();

		default:
			return "";
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	
	

}
