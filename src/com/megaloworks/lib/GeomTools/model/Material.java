package com.megaloworks.lib.GeomTools.model;

import java.util.Arrays;

public class Material {

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

		public Builder() {
		}

		public Material build() {
			return new Material(this);
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

	private Material(Builder builder) {
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

	@Override
	public String toString() {
		return "Material [amb_=" + amb_ + ", aplane_=" + aplane_ + ", bump_="
				+ bump_ + ", col_=" + Arrays.toString(col_) + ", dif_=" + dif_
				+ ", emi_=" + emi_ + ", name_=" + name_ + ", power_=" + power_
				+ ", shader_=" + shader_ + ", spc_=" + spc_ + ", tex_=" + tex_
				+ ", vcol_=" + vcol_ + "]";
	}

}
