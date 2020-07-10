package com.zvyap.core.file;

import java.io.File;
import java.util.ArrayList;

import com.zvyap.core.PluginMain;

public class FolderManager {

	private File folder;
	
	public FolderManager(File folder) {
		if(!folder.exists()) {
			folder.mkdirs();
		}
		if(folder.isDirectory()) {
			this.setFolder(folder);
		}else {
			this.folder = new File(PluginMain.getInstance().getDataFolder(), folder.getName());
			if (!folder.exists()) {
				folder.mkdirs();
			}
		}
	}

	public FolderManager(String foldername) {
		this.folder = new File(PluginMain.getInstance().getDataFolder(), foldername);
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}
	
	public File getFolder() {
		return folder;
	}

	public void setFolder(File folder) {
		this.folder = folder;
	}
	
	public boolean isHaveFileInFolder() {
		if(folder.listFiles() == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public ArrayList<File> getAllFileInsideFolder() {
		ArrayList<File> filelist = new ArrayList<File>();
	    if (isHaveFileInFolder()) {
		      for(File f : folder.listFiles()) {
			      filelist.add(f);
		      }
		      return filelist;
		}else {
			return null;
		}
	}
	
	public ArrayList<File> getAllFileInFolder() {
		ArrayList<File> filelist = new ArrayList<File>();
	    if (isHaveFileInFolder()) {
		      for(File f : folder.listFiles()) {
		    	  if(f.isDirectory()) {
		    		  filelist.addAll(getAllFileInFolder(f));
		    	  }
		      }
		      return filelist;
		}else {
			return null;
		}
	}
	
	private ArrayList<File> getAllFileInFolder(File folder) {
		ArrayList<File> filelist = new ArrayList<File>();
	    if (isHaveFileInFolder()) {
		      for(File f : folder.listFiles()) {
		    	  if(f.isDirectory()) {
		    		  filelist.addAll(getAllFileInFolder(f));
		    	  }
		      }
		      return filelist;
		}else {
			return null;
		}
	}
	
	public boolean isFileInFolder(String name) {
		if(folder.listFiles() == null) {
			for(File f : getAllFileInFolder()) {
				if(f.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isFileInFolder(File file) {
		if(folder.listFiles() == null) {
			for(File f : getAllFileInFolder()) {
				if(f.equals(file)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public ArrayList<YmlFileManager> registerYmlAllFileInsideFolder() {
		ArrayList<YmlFileManager> fm = new ArrayList<YmlFileManager>();
		if(isHaveFileInFolder()) {
			for(File f : getAllFileInsideFolder()) {
				if(!f.getName().contains(".yml")) {
					fm.add(new YmlFileManager(f));
				}
			}
		}
		return fm;
	}
	
	public ArrayList<YmlFileManager> registerYmlAllFileInFolder() {
		ArrayList<YmlFileManager> fm = new ArrayList<YmlFileManager>();
		if(isHaveFileInFolder()) {
			for(File f : getAllFileInFolder()) {
				if(!f.getName().contains(".yml")) {
					fm.add(new YmlFileManager(f));
				}
			}
		}
		return fm;
	}
}
