package com.megaloworks.lib.GeomTools.util;

import java.util.ArrayList;
import java.util.logging.Logger;

public final class Commons {

	public static int FLOAT_BUFFER_SIZE = 4;

	public static LoggingAdaptor getNormalLoggingAdaptoerUsingLogger(
			final Object obj) {
		LoggingAdaptor log = new LoggingAdaptor() {

			private String tag = getTAG(obj);

			private Logger logger;

			@Override
			public void setTag(String tag) {
				this.tag = tag;
			}

			@Override
			public void info(String tag, String... messages) {
				this.tag = tag;
				i(messages);
			}

			@Override
			public void i(String... messages) {
				StringBuilder sb = new StringBuilder();
				for (String message : messages) {
					sb.append(message);
				}
				logger.info(this.tag + " " + sb.toString());
			}
		};
		return log;
	}

	public static String getTAG(Object obj) {
		return obj.getClass().getName();
	}

	public static float[] getfloats(ArrayList<Float> alfloats) {
		float[] floats = new float[alfloats.size()];
		for (int i = 0; i < alfloats.size(); i++) {
			floats[i] = alfloats.get(i);
		}
		return floats;
	}

	public static int[] getints(ArrayList<Integer> alints) {
		int[] ints = new int[alints.size()];
		for (int i = 0; i < alints.size(); i++) {
			ints[i] = alints.get(i);
		}
		return ints;
	}

	public static short[] getshorts(ArrayList<Short> alshorts) {
		short[] shorts = new short[alshorts.size()];
		for (int i = 0; i < alshorts.size(); i++) {
			shorts[i] = alshorts.get(i);
		}
		return shorts;
	}

}
