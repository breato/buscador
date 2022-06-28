package com.brunoreato.buscador.utils;

import java.util.Iterator;
import java.util.List;


public class ArrayRowIterator  implements Iterator<String> {
	private List<String> rows;
	private int pos = -1;
	
	public ArrayRowIterator(List<String> rows) {
		super();
		this.rows = rows;
	}

	@Override
	public boolean hasNext() {
		return pos + 1 < rows.size();
	}

	@Override
	public String next() {
		pos++; 
		return rows.get(pos);
	}

}
