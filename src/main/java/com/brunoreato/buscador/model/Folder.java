package com.brunoreato.buscador.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.brunoreato.buscador.FinderConfiguration;

public class Folder {
	private List<File> files = new ArrayList<File>();
	private java.io.File folder;
	private FinderConfiguration config;
	private boolean filesLoaded;
		
	
	public Folder(java.io.File folder, FinderConfiguration config) {
		super();
		this.folder = folder;
		this.config = config;
	}

	public List<File> getFiles() {
		return files;
	}
	
	public void searchFiles() throws FileNotFoundException {
		
		filesLoaded = false;
		if (!folder.isDirectory())
			throw new IllegalArgumentException("La carpeta indicada no existe");

		java.io.File[] textFiles = this.folder.listFiles((f, n) -> n.endsWith(".txt") || n.endsWith(".php") || n.endsWith(".java"));
		
		for (java.io.File file : textFiles) {
			File f = new File(this, file.toString(), config);

			f.searchRows();
			
			files.add(f);
		}
		
		filesLoaded = true;
	}

	public String getFolder() {
		return folder.getAbsolutePath();
	}

	public final boolean isFilesLoaded() {
		return filesLoaded;
	}
	
}
