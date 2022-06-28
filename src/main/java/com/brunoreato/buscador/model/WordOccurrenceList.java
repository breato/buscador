package com.brunoreato.buscador.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WordOccurrenceList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2526295030729409151L;
	private Map<String, List<WordOccurrences>> words;
	
	public WordOccurrenceList(Map<String, List<WordOccurrences>> words) {
		this.words = words;
	}
	
	
	public void add(String word, String file, int occurrences) {
		List<WordOccurrences> wordOccurrences = words.get(word.toUpperCase());
		
		if (wordOccurrences == null) {
			wordOccurrences = new ArrayList<WordOccurrences>();
			words.put(word.toUpperCase(), wordOccurrences);
		} 
		
		Optional<WordOccurrences> wo = wordOccurrences.stream().filter(w -> w.getFile() == file).findFirst();
			
		if (wo.isPresent()) {
			wo.get().increment(occurrences);
		} else {
			WordOccurrences newWO = new WordOccurrences(file, occurrences);
			
			wordOccurrences.add(newWO);
		}		
	}
	
	public List<WordOccurrences> search(String word) {
		List<WordOccurrences> occ = words.get(word.toUpperCase());
		if (occ != null)
			return occ.stream().sorted((wo1, wo2) -> wo2.getOcurrences() - wo1.getOcurrences()).toList();
		else
			return new ArrayList<>();
	}
	
	public void resetWords() {
		words.clear();
	}
}
