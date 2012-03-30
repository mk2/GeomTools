package com.megaloworks.lib.GeomTools.math;

public final class Vec3 {

	public static Vec3 kZeroVec3;
	static {
		kZeroVec3 = new Vec3();
		kZeroVec3.zero();
	}

	public static float vecMag(Vec3 v) {
		double x = v.x;
		double y = v.y;
		double z = v.z;
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public static Vec3 crossP(Vec3 v1, Vec3 v2) {
		return new Vec3(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z,
				v1.x * v2.y - v1.y * v2.x);
	}

	public static float[] normalizeArray(float[] xyz) {
		double x = xyz[0];
		double y = xyz[1];
		double z = xyz[2];
		double sqrt = Math.sqrt(x * x + y * y + z * z);
		return new float[] { (float) (x / sqrt), (float) (y / sqrt),
				(float) (z / sqrt) };
	}

	public float x;

	public float y;

	public float z;

	public Vec3() {
	}

	public Vec3(Vec3 v) {
		x = v.x;
		y = v.y;
		z = v.z;
	}

	public Vec3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3(float[] xyz) {
		try {
			x = xyz[0];
			y = xyz[1];
			z = xyz[2];
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			x = Float.MIN_VALUE;
			y = Float.MIN_VALUE;
			z = Float.MIN_VALUE;
		}
	}

	public Vec3 xyz() {
		return new Vec3(x, y, z);
	}

	public Vec3 xzy() {
		return new Vec3(x, z, y);
	}

	public Vec3 yxz() {
		return new Vec3(y, x, z);
	}

	public Vec3 yzx() {
		return new Vec3(y, z, x);
	}

	public Vec3 zxy() {
		return new Vec3(z, x, y);
	}

	public Vec3 zyx() {
		return new Vec3(z, y, x);
	}

	public float[] toArray() {
		return new float[] { x, y, z };
	}

	public void zero() {
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
	}

	public Vec3 get3Cev() {
		return new Vec3(-x, -y, -z);
	}

	public Vec3 add(Vec3 v) {
		return new Vec3(x + v.x, y + v.y, z + v.z);
	}

	public Vec3 minus(Vec3 v) {
		return new Vec3(x - v.x, y - v.y, z - v.z);
	}

	public Vec3 multiS(float c) {
		return new Vec3(x * c, y * c, z * c);
	}

	public Vec3 divS(float c) {
		float oneOverC = 1.0f / c;
		return new Vec3(x * oneOverC, y * oneOverC, z * oneOverC);
	}

	public void normalize() {
		float magSq = x * x + y * y + z * z;
		if (magSq > 0.0f) {
			float oneOverMag = 1.0f / (float) Math.sqrt((double) magSq);
			x *= oneOverMag;
			y *= oneOverMag;
			z *= oneOverMag;
		}
	}

	public float dotP(Vec3 v) {
		return x * v.x + y * v.y + z * v.z;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		result = prime * result + Float.floatToIntBits(z);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vec3 other = (Vec3) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
			return false;
		return true;
	}
}
