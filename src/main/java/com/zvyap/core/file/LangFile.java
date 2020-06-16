package com.zvyap.core.file;

import java.util.HashMap;

public interface LangFile {
	
	HashMap<String, String> getLangs();
	
	void setLang(String langid, String string);
}
