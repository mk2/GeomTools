package com.megaloworks.lib.GeomTools.model;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public interface GeomData {

	public int getTextureID();

	public FloatBuffer getCoords();

	public FloatBuffer getTexCoords();

	public FloatBuffer getNormalCoords();

	public ShortBuffer getConnectivities();

}
