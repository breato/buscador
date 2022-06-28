package com.brunoreato.buscador.index;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;

import org.apache.commons.codec.digest.DigestUtils;

import com.brunoreato.buscador.FinderConfiguration;
import com.brunoreato.buscador.model.Folder;
import com.brunoreato.buscador.model.WordOccurrenceList;

public class FileIndex extends MemoryIndex {
	private static final int OT_DATA = 1;
	private static final int OT_STATS = 2;

	public FileIndex(Folder folder, FinderConfiguration fc, WordOccurrenceList wordOccurrenceList) {
		super(folder, fc, wordOccurrenceList);
		
	}
	
	private void saveObject(int objType, Object obj) throws URISyntaxException, FileNotFoundException, IOException {
		String hashFolderName = DigestUtils.md5Hex(this.getFolder().getFolder()).toUpperCase();
		String jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		
		File indexFile;
		if (objType == OT_DATA)
			indexFile = new File(new File(jarPath).getParent(), hashFolderName + ".data");
		else
			indexFile = new File(new File(jarPath).getParent(), hashFolderName + "_stats.data");
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(indexFile));
		try {
			oos.writeObject(obj);
		} finally {
			oos.close();
		}
	}
	
	private Object loadObject(int objType) throws URISyntaxException, FileNotFoundException, IOException, ClassNotFoundException {
		String hashFolderName = DigestUtils.md5Hex(this.getFolder().getFolder()).toUpperCase();
		String jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		
		File indexFile;
		if (objType == OT_DATA)
			indexFile = new File(new File(jarPath).getParent(), hashFolderName + ".data");
		else
			indexFile = new File(new File(jarPath).getParent(), hashFolderName + "_stats.data");
		
		if (indexFile.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(indexFile));
			try {
				return ois.readObject();
			} finally {
				ois.close();
			}
			
		} else {
			return null;
		}
	}
	
	public boolean load() throws URISyntaxException, FileNotFoundException, IOException, ClassNotFoundException {
		Object objWOL = loadObject(OT_DATA);
		Object objStats = loadObject(OT_STATS);
		
		if (objWOL != null)
			words = (WordOccurrenceList)objWOL;
		
		if (objStats != null)
			stats = (StaticsIndex)objStats;
		
		return (words != null) && (objStats != null);
	}
	
	public void save() throws URISyntaxException, FileNotFoundException, IOException {
		saveObject(OT_DATA, words);
		saveObject(OT_STATS, stats);
	}

}
