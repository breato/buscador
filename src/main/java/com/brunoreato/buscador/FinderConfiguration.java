package com.brunoreato.buscador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.Iterator;

import com.brunoreato.buscador.ommit.ArrayWordsOmmited;
import com.brunoreato.buscador.ommit.FileWordsOmmited;
import com.brunoreato.buscador.ommit.WordsOmmited;
import com.brunoreato.buscador.scan.FileRowIterator;
import com.brunoreato.buscador.scan.RIWordIterator;
import com.brunoreato.buscador.scan.WordInfo;

public class FinderConfiguration {
	private File fileWordsOmmited;
	
	public FinderConfiguration() throws URISyntaxException {
		String jarPath =  getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();	
		
		fileWordsOmmited = new File(new File(jarPath).getParent(), "words_ommited.txt");
	}
	
	public Iterator<WordInfo> getWordIterator(String row) {
		return new RIWordIterator(row);
	}
	
	public Iterator<String> getRowIterator(java.io.File file) throws FileNotFoundException {
		return new FileRowIterator(new BufferedReader(new FileReader(file)));
	}
	
	public WordsOmmited getWordsOmmited() {
		
		if (fileWordsOmmitedExists())
			try {
				return new FileWordsOmmited(new BufferedReader(new FileReader(fileWordsOmmited)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return ArrayWordsOmmited.newInstance();
			}
		else
			return ArrayWordsOmmited.newInstance();
	}

	public final String getFileWordsOmmited() {
		return fileWordsOmmited.getAbsolutePath();
	}
	
	public final boolean fileWordsOmmitedExists() {
		return fileWordsOmmited.exists();
	}
}
