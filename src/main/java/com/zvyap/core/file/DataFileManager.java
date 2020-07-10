package com.zvyap.core.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataFileManager {

	FolderManager fm;
	
	File folder;
	
	public DataFileManager(File dir) {
		FolderManager fm = new FolderManager(dir);
		this.fm = fm;
		this.folder = fm.getFolder();
	}
	
	public void createDataFile(String filename) {
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File file = new File(folder, filename + ".dat");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void saveObject(String filename, Object object) {
		File file = new File(folder, filename + ".dat");
		if(!file.exists()) {
			createDataFile(filename);
		}
		try {
			ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
			stream.writeObject(object);
			stream.flush();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object loadFile(String filename) {
		File file = new File(folder, filename + ".dat");
		if(!file.exists()) {
			createDataFile(filename);
		}
		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
			Object obj = stream.readObject();
			stream.close();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
