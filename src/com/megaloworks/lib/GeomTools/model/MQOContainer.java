package com.megaloworks.lib.GeomTools.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

/**
 * MQOContainer class. Use builder pattern.
 * 
 * @author trude
 * 
 */
public class MQOContainer {

	public static class Builder {

		private float version_;

		private MQOObject[] mqoObjects_;

		private MQOMaterial[] mqoMaterials_;

		public Builder() {

		}

		public MQOContainer build() {
			return new MQOContainer(this);
		}

		public Builder setVersion_(float version) {
			version_ = version;
			return this;
		}

		public Builder setMqoObjects_(MQOObject[] mqoObjects) {
			mqoObjects_ = mqoObjects;
			return this;
		}

		public Builder setMqoMaterials_(MQOMaterial[] mqoMaterials) {
			mqoMaterials_ = mqoMaterials;
			return this;
		}

	}

	private final float version_;

	private final MQOObject[] mqoObjects_;

	private final MQOMaterial[] mqoMaterials_;

	private int[] textureIDs_;

	private FloatBuffer[] verticesCoordsBuffers_;

	private FloatBuffer[] texCoordsBuffers_;

	private ShortBuffer[] connectivitiesBuffers_;

	private FloatBuffer[] normalsBuffers_;

	private int[] nVerticesCoords_;

	private MQOContainer(Builder builder) {
		version_ = builder.version_;
		mqoObjects_ = builder.mqoObjects_;
		mqoMaterials_ = builder.mqoMaterials_;
		textureIDs_ = null;
		verticesCoordsBuffers_ = null;
		texCoordsBuffers_ = null;
		connectivitiesBuffers_ = null;
		normalsBuffers_ = null;
		nVerticesCoords_ = null;
	}

	public float getVersion_() {
		return version_;
	}

	public MQOObject[] getMqoObjects_() {
		return mqoObjects_;
	}

	public MQOMaterial[] getMqoMaterials_() {
		return mqoMaterials_;
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("MQOContainer [mqoMaterials_=");
		builder2.append(Arrays.toString(mqoMaterials_));
		builder2.append(", mqoObjects_=");
		builder2.append(Arrays.toString(mqoObjects_));
		builder2.append(", version_=");
		builder2.append(version_);
		builder2.append("]");
		return builder2.toString();
	}
//
//	@Override
//	public int getTextureIDs() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public FloatBuffer getCoords() {
//		if (verticesCoordsBuffers_ == null) {
//			verticesCoordsBuffers_ = new FloatBuffer[mqoObjects_.length];
//			for (int i = 0; i < mqoObjects_.length; i++) {
//				ByteBuffer bbuf = ByteBuffer.allocateDirect(mqoObjects_[i]
//						.getVertices_().getCoords_().length * 4);
//				bbuf.order(ByteOrder.nativeOrder());
//				verticesCoordsBuffers_[i] = bbuf.asFloatBuffer();
//				verticesCoordsBuffers_[i].put(mqoObjects_[i].getVertices_()
//						.getCoords_());
//				verticesCoordsBuffers_[i].rewind();
//			}
//		}
//		return verticesCoordsBuffers_;
//	}
//
//	@Override
//	public FloatBuffer getTexCoords() {
//		if (texCoordsBuffers_ == null) {
//			texCoordsBuffers_ = new FloatBuffer[mqoObjects_.length];
//			for (int i = 0; i < mqoObjects_.length; i++) {
//				texCoordsBuffers_[i] = FloatBuffer.wrap(mqoObjects_[i]
//						.getVertices_().getCoords_());
//				texCoordsBuffers_[i].rewind();
//			}
//		}
//		return texCoordsBuffers_;
//	}
//
//	@Override
//	public ShortBuffer getConnectivities() {
//		if (connectivitiesBuffers_ == null) {
//			connectivitiesBuffers_ = new ShortBuffer[mqoObjects_.length];
//			for (int i = 0; i < mqoObjects_.length; i++) {
//				connectivitiesBuffers_[i] = ShortBuffer.wrap(mqoObjects_[i]
//						.getElements_().getConnectivities_());
//				connectivitiesBuffers_[i].rewind();
//			}
//		}
//		return connectivitiesBuffers_;
//	}
//
//	@Override
//	public FloatBuffer getNormalsCoords() {
//		if (normalsBuffers_ == null) {
//			normalsBuffers_ = new FloatBuffer[mqoObjects_.length];
//			for (int i = 0; i < mqoObjects_.length; i++) {
//				normalsBuffers_[i] = FloatBuffer.wrap(mqoObjects_[i]
//						.getElements_().getNormals_());
//				normalsBuffers_[i].rewind();
//			}
//		}
//		return normalsBuffers_;
//	}

}
