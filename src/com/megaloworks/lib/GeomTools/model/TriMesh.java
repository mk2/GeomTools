package com.megaloworks.lib.GeomTools.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class TriMesh implements GeomData {

	private Elements elements_;

	private Vertices vertices_;

	private Material material_;

	public Elements getElements_() {
		return elements_;
	}

	public void setElements_(Elements elements) {
		elements_ = elements;
	}

	public Vertices getVertices_() {
		return vertices_;
	}

	public void setVertices_(Vertices vertices) {
		vertices_ = vertices;
	}

	public Material getMaterial_() {
		return material_;
	}

	public void setMaterial_(Material material) {
		material_ = material;
	}

	/**
	 * GeomData methods
	 */
	private ShortBuffer connectivities_;

	private FloatBuffer coords_;

	private FloatBuffer normalCoords_;

	private FloatBuffer texCoords_;

	private int textureID_;

	@Override
	public ShortBuffer getConnectivities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FloatBuffer getCoords() {
		if (coords_ == null) {
			ByteBuffer bbuf = ByteBuffer
					.allocateDirect(vertices_.getCoords_().length * 4);
			bbuf.order(ByteOrder.nativeOrder());
			coords_ = bbuf.asFloatBuffer();
			coords_.put(vertices_.getCoords_());
			coords_.rewind();
		}
		return coords_;
	}

	@Override
	public FloatBuffer getNormalCoords() {
		if (normalCoords_ == null) {
		}
		return null;
	}

	@Override
	public FloatBuffer getTexCoords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTextureID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
