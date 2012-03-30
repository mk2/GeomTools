package com.megaloworks.lib.GeomTools.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public final class TrimBufferedReader extends BufferedReader {

	private int currentReadingLineNumber_;

	public TrimBufferedReader(Reader in) {
		super(in);
		currentReadingLineNumber_ = 0;
	}

	@Override
	public String readLine() throws IOException {
		String ret = super.readLine();
		if (ret != null) {
			currentReadingLineNumber_++;
			return ret.trim();
		} else {
			return ret;
		}
	}

	public int getCurrentReadingLineNumber_() {
		return currentReadingLineNumber_;
	}

}