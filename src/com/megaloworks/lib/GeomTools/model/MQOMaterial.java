package com.megaloworks.lib.GeomTools.model;

import java.util.Arrays;

/**
 * MQOMaterial class. Use builder pattern.
 * 
 * @author trude
 * 
 */
public final class MQOMaterial {

	public static class Builder {

		private String name_;

		private int shader_;

		private int vcol_;

		private float[] col_;

		private float dif_;

		private float amb_;

		private float emi_;

		private float spc_;

		private float power_;

		private String tex_;

		private String aplane_;

		private String bump_;

		private int proj_type_;

		private float[] proj_pos_;

		private float[] proj_scale_;

		private float[] proj_angle_;

		public Builder() {
		}

		public MQOMaterial build() {
			return new MQOMaterial(this);
		}

		public Builder setName_(String name) {
			name_ = name;
			return this;
		}

		public Builder setShader_(int shader) {
			shader_ = shader;
			return this;
		}

		public Builder setVcol_(int vcol) {
			vcol_ = vcol;
			return this;
		}

		public Builder setCol_(float[] col) {
			col_ = col;
			return this;
		}

		public Builder setDif_(float dif) {
			dif_ = dif;
			return this;
		}

		public Builder setAmb_(float amb) {
			amb_ = amb;
			return this;
		}

		public Builder setEmi_(float emi) {
			emi_ = emi;
			return this;
		}

		public Builder setSpc_(float spc) {
			spc_ = spc;
			return this;
		}

		public Builder setPower_(float power) {
			power_ = power;
			return this;
		}

		public Builder setTex_(String tex) {
			tex_ = tex;
			return this;
		}

		public Builder setAplane_(String aplane) {
			aplane_ = aplane;
			return this;
		}

		public Builder setBump_(String bump) {
			bump_ = bump;
			return this;
		}

		public Builder setProj_type_(int projType) {
			proj_type_ = projType;
			return this;
		}

		public Builder setProj_pos_(float[] projPos) {
			proj_pos_ = projPos;
			return this;
		}

		public Builder setProj_scale_(float[] projScale) {
			proj_scale_ = projScale;
			return this;
		}

		public Builder setProj_angle_(float[] projAngle) {
			proj_angle_ = projAngle;
			return this;
		}

	}

	private final String name_;

	private final int shader_;

	private final int vcol_;

	private final float[] col_;

	private final float dif_;

	private final float amb_;

	private final float emi_;

	private final float spc_;

	private final float power_;

	private final String tex_;

	private final String aplane_;

	private final String bump_;

	private final int proj_type_;

	private final float[] proj_pos_;

	private final float[] proj_scale_;

	private final float[] proj_angle_;

	private MQOMaterial(Builder builder) {
		name_ = builder.name_;
		shader_ = builder.shader_;
		vcol_ = builder.vcol_;
		col_ = builder.col_;
		dif_ = builder.dif_;
		amb_ = builder.amb_;
		emi_ = builder.emi_;
		spc_ = builder.spc_;
		power_ = builder.power_;
		tex_ = builder.tex_;
		aplane_ = builder.aplane_;
		bump_ = builder.bump_;
		proj_type_ = builder.proj_type_;
		proj_pos_ = builder.proj_pos_;
		proj_scale_ = builder.proj_scale_;
		proj_angle_ = builder.proj_angle_;
	}

	public String getName_() {
		return name_;
	}

	public int getShader_() {
		return shader_;
	}

	public int getVcol_() {
		return vcol_;
	}

	public float[] getCol_() {
		return col_;
	}

	public float getDif_() {
		return dif_;
	}

	public float getAmb_() {
		return amb_;
	}

	public float getEmi_() {
		return emi_;
	}

	public float getSpc_() {
		return spc_;
	}

	public float getPower_() {
		return power_;
	}

	public String getTex_() {
		return tex_;
	}

	public String getAplane_() {
		return aplane_;
	}

	public String getBump_() {
		return bump_;
	}

	public int getProj_type_() {
		return proj_type_;
	}

	public float[] getProj_pos_() {
		return proj_pos_;
	}

	public float[] getProj_scale_() {
		return proj_scale_;
	}

	public float[] getProj_angle_() {
		return proj_angle_;
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("MQOMaterial [amb_=");
		builder2.append(amb_);
		builder2.append(", aplane_=");
		builder2.append(aplane_);
		builder2.append(", bump_=");
		builder2.append(bump_);
		builder2.append(", col_=");
		builder2.append(Arrays.toString(col_));
		builder2.append(", dif_=");
		builder2.append(dif_);
		builder2.append(", emi_=");
		builder2.append(emi_);
		builder2.append(", name_=");
		builder2.append(name_);
		builder2.append(", power_=");
		builder2.append(power_);
		builder2.append(", proj_angle_=");
		builder2.append(Arrays.toString(proj_angle_));
		builder2.append(", proj_pos_=");
		builder2.append(Arrays.toString(proj_pos_));
		builder2.append(", proj_scale_=");
		builder2.append(Arrays.toString(proj_scale_));
		builder2.append(", proj_type_=");
		builder2.append(proj_type_);
		builder2.append(", shader_=");
		builder2.append(shader_);
		builder2.append(", spc_=");
		builder2.append(spc_);
		builder2.append(", tex_=");
		builder2.append(tex_);
		builder2.append(", vcol_=");
		builder2.append(vcol_);
		builder2.append("]");
		return builder2.toString();
	}

}
