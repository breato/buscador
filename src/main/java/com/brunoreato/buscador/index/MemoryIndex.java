package com.brunoreato.buscador.index;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.brunoreato.buscador.FinderConfiguration;
import com.brunoreato.buscador.model.File;
import com.brunoreato.buscador.model.Folder;
import com.brunoreato.buscador.model.Row;
import com.brunoreato.buscador.model.WordOccurrenceList;
import com.brunoreato.buscador.model.WordOccurrences;
import com.brunoreato.buscador.ommit.WordsOmmited;

public class MemoryIndex {
	
	protected WordOccurrenceList words;
	private Folder folder;
	private WordsOmmited wordsOmmited;
	protected StaticsIndex stats;
	
	
	public MemoryIndex(Folder folder, FinderConfiguration fc, WordOccurrenceList wordOccurrenceList) {
		this.folder = folder;
		this.wordsOmmited = fc.getWordsOmmited();
		this.words = wordOccurrenceList;
		stats = new StaticsIndex();
	}
	
	public void build() throws FileNotFoundException {		
		if (!folder.isFilesLoaded())
			folder.searchFiles();
		
		stats.resetStats();
		words.resetWords();
		
		for (File file : folder.getFiles()) {
			stats.incrementFilesCount();
			for (Row row : file.getRows()) {
				stats.incrementRowsCount();
				row.getWords().forEach((k, v) -> {
					stats.incrementTotalWordsCount();
					if (!wordsOmmited.isOmmited(k)) {
						stats.incrementValidWordsCount();
						words.add(k, file.getFileName(), v.size());
					}
				});
			}
		}
	}
	
	public List<WordOccurrences> findWords(String word) {
		List<WordOccurrences> res = words.search(word);
		return res == null ? new ArrayList<WordOccurrences>() : res;
	}

	public int getFilesCount() {
		return stats.getFilesCount();
	}

	public int getTotalWordsCount() {
		return stats.getTotalWordsCount();
	}

	public int getValidWordsCount() {
		return stats.getValidWordsCount();
	}

	public int getRowsCount() {
		return stats.getRowsCount();
	}

	public Folder getFolder() {
		return folder;
	}
	
	
}
