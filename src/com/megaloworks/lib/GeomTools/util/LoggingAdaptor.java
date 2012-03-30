package com.megaloworks.lib.GeomTools.util;

public interface LoggingAdaptor {

	public void setTag(String tag);

	public void info(String tag, String... messages);

	public void i(String... messages);

}
