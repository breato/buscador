package com.brunoreato.buscador.scan;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RIWordIterator implements Iterator<WordInfo>{
	
	private Matcher matcher;
	private boolean hasNext;

	public RIWordIterator(String row) {
		Pattern p = Pattern.compile("[a-zA-Z\\u00C0-\\u017F]+", Pattern.CASE_INSENSITIVE);
	    matcher = p.matcher(row);	
	}
	
	@Override
	public boolean hasNext() {
		if (!hasNext)
			hasNext = matcher.find();
		
		return hasNext;
	}

	@Override
	public WordInfo next() {
		if (!hasNext)
			return null;
		
		WordInfo wi = new WordInfo();
		wi.setWord(matcher.group());
		wi.setPosition(matcher.start());
		hasNext = false;
		
		return wi;
	}

}
