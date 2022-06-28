package com.brunoreato.buscador.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowWords {
	private Map<String, List<Integer>> words = new HashMap<String, List<Integer>>();
	
	public void addWord(String word, int position) {
		if (words.containsKey(word)) {
			words.get(word).add(position);
		} else {
			List<Integer> positions = new ArrayList<Integer>();
			positions.add(position);
			words.put(word, positions);
		}
	}

	public Map<String, List<Integer>> getWords() {
		return words;
	}

}
