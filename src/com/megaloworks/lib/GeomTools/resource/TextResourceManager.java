package com.megaloworks.lib.GeomTools.resource;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.megaloworks.lib.GeomTools.util.Commons;
import com.megaloworks.lib.GeomTools.util.LoggingAdaptor;

public final class TextResourceManager {

	private LoggingAdaptor logger_;

	private static TextResourceManager resMgr_;

	private ResourceBundle resBdl_;

	private ResourceBundle buildDateResBdl_;

	private TextResourceManager() {
		resBdl_ = ResourceBundle
				.getBundle("com.megaloworks.lib.GeomTools.resource.GeomTools");
		buildDateResBdl_ = ResourceBundle
				.getBundle("com.megaloworks.lib.GeomTools.resource.buildDate");
		logger_ = Commons.getNormalLoggingAdaptoerUsingLogger(this);
	}

	synchronized public static TextResourceManager getInstance() {
		if (resMgr_ == null) {
			resMgr_ = new TextResourceManager();
		}
		return resMgr_;
	}

	public String getString(String key) {
		String result = key;
		try {
			result = resBdl_.getString(key);
		} catch (MissingResourceException mre) {
			if (key.equals("buildDate")) {
				result = buildDateResBdl_.getString(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
