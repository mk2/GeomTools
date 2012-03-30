package com.megaloworks.lib.GeomTools.model;

import java.util.Arrays;

public final class Vertices {

    private int nVertices_;

    private float[] coords_;

    public int getnVertices_() {
	return nVertices_;
    }

    public void setnVertices_(int nVertices) {
	nVertices_ = nVertices;
    }

    public float[] getCoords_() {
	return coords_;
    }

    public void setCoords_(float[] coords) {
	coords_ = coords;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Vertices [coords_=");
	builder.append(Arrays.toString(coords_));
	builder.append(", nVertices_=");
	builder.append(nVertices_);
	builder.append("]");
	return builder.toString();
    }

}
