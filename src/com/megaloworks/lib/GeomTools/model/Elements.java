package com.megaloworks.lib.GeomTools.model;

import java.util.Arrays;

public final class Elements {

	public static final int TRIANGLE = 3;

	public static final int QUADANGLE = 4;

	private int patchType_ = TRIANGLE;

	private int nElements_;

	private short[] connectivities_;

	private int[] connectivitiesSeparationIndices_;

	private int[] materialIndices_;

	private float[] texCoords_;

	private int[] vcolors_;

	private float[] normals_;

	public int getPatchType_() {
		return patchType_;
	}

	public void setPatchType_(int patchType) {
		patchType_ = patchType;
	}

	public int getnElements_() {
		return nElements_;
	}

	public void setnElements_(int nElements) {
		nElements_ = nElements;
	}

	public short[] getConnectivities_() {
		return connectivities_;
	}

	public void setConnectivities_(short[] connectivities) {
		connectivities_ = connectivities;
	}

	public int[] getConnectivitiesSeparationIndices_() {
		return connectivitiesSeparationIndices_;
	}

	public void setConnectivitiesSeparationIndices_(
			int[] connectivitiesSeparationIndices) {
		connectivitiesSeparationIndices_ = connectivitiesSeparationIndices;
	}

	public int[] getMaterialIndices_() {
		return materialIndices_;
	}

	public void setMaterialIndices_(int[] materialIndices) {
		materialIndices_ = materialIndices;
	}

	public float[] getTexCoords_() {
		return texCoords_;
	}

	public void setTexCoords_(float[] texCoords) {
		texCoords_ = texCoords;
	}

	public int[] getVcolors_() {
		return vcolors_;
	}

	public void setVcolors_(int[] vcolors) {
		vcolors_ = vcolors;
	}

	public float[] getNormals_() {
		return normals_;
	}

	public void setNormals_(float[] normals) {
		normals_ = normals;
	}

	public static int getTriangle() {
		return TRIANGLE;
	}

	public static int getQuadangle() {
		return QUADANGLE;
	}

	@Override
	public String toString() {
		return "Elements [connectivitiesSeparationIndices_="
				+ Arrays.toString(connectivitiesSeparationIndices_)
				+ ", connectivities_=" + Arrays.toString(connectivities_)
				+ ", materialIndices_=" + Arrays.toString(materialIndices_)
				+ ", nElements_=" + nElements_ + ", normals_="
				+ Arrays.toString(normals_) + ", patchType_=" + patchType_
				+ ", texCoords_=" + Arrays.toString(texCoords_) + ", vcolors_="
				+ Arrays.toString(vcolors_) + "]";
	}

}
