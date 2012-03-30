package com.megaloworks.lib.GeomTools.model;

import java.util.Arrays;

/**
 * MQOObject class. Use builder pattern.
 * 
 * @author trude
 * 
 */
public class MQOObject {

    public static class Builder {

	private String name_;

	private Vertices vertices_;

	private Elements elements_;

	private int depth_;

	private int folding_;

	private float[] scale_;

	private float[] rotation_;

	private float[] translation_;

	private int patch_;

	private int segment_;

	private int visible_;

	private int locking_;

	private int shading_;

	private float facet_;

	private float[] color_;

	private int color_type_;

	private int mirror_;

	private int mirror_axis_;

	private float mirror_dis_;

	private int lathe_;

	private int lathe_axis_;

	private int lathe_seg_;

	public Builder() {

	}

	public MQOObject build() {
	    return new MQOObject(this);
	}

	public Builder setName_(String name) {
	    name_ = name;
	    return this;
	}

	public Builder setVertices_(Vertices vertices) {
	    vertices_ = vertices;
	    return this;
	}

	public Builder setElements_(Elements elements) {
	    elements_ = elements;
	    return this;
	}

	public Builder setDepth_(int depth) {
	    depth_ = depth;
	    return this;
	}

	public Builder setFolding_(int folding) {
	    folding_ = folding;
	    return this;
	}

	public Builder setScale_(float[] scale) {
	    scale_ = scale;
	    return this;
	}

	public Builder setRotation_(float[] rotation) {
	    rotation_ = rotation;
	    return this;
	}

	public Builder setTranslation_(float[] translation) {
	    translation_ = translation;
	    return this;
	}

	public Builder setPatch_(int patch) {
	    patch_ = patch;
	    return this;
	}

	public Builder setSegment_(int segment) {
	    segment_ = segment;
	    return this;
	}

	public Builder setVisible_(int visible) {
	    visible_ = visible;
	    return this;
	}

	public Builder setLocking_(int locking) {
	    locking_ = locking;
	    return this;
	}

	public Builder setShading_(int shading) {
	    shading_ = shading;
	    return this;
	}

	public Builder setFacet_(float facet) {
	    facet_ = facet;
	    return this;
	}

	public Builder setColor_(float[] color) {
	    color_ = color;
	    return this;
	}

	public Builder setColor_type_(int colorType) {
	    color_type_ = colorType;
	    return this;
	}

	public Builder setMirror_(int mirror) {
	    mirror_ = mirror;
	    return this;
	}

	public Builder setMirror_axis_(int mirrorAxis) {
	    mirror_axis_ = mirrorAxis;
	    return this;
	}

	public Builder setMirror_dis_(float mirrorDis) {
	    mirror_dis_ = mirrorDis;
	    return this;
	}

	public Builder setLathe_(int lathe) {
	    lathe_ = lathe;
	    return this;
	}

	public Builder setLathe_axis_(int latheAxis) {
	    lathe_axis_ = latheAxis;
	    return this;
	}

	public Builder setLathe_seg_(int latheSeg) {
	    lathe_seg_ = latheSeg;
	    return this;
	}

    }

    private final String name_;

    private final Vertices vertices_;

    private final Elements elements_;

    private final int depth_;

    private final int folding_;

    private final float[] scale_;

    private final float[] rotation_;

    private final float[] translation_;

    private final int patch_;

    private final int segment_;

    private final int visible_;

    private final int locking_;

    private final int shading_;

    private final float facet_;

    private final float[] color_;

    private final int color_type_;

    private final int mirror_;

    private final int mirror_axis_;

    private final float mirror_dis_;

    private final int lathe_;

    private final int lathe_axis_;

    private final int lathe_seg_;

    private MQOObject(Builder builder) {
	name_ = builder.name_;
	vertices_ = builder.vertices_;
	elements_ = builder.elements_;
	depth_ = builder.depth_;
	folding_ = builder.folding_;
	scale_ = builder.scale_;
	rotation_ = builder.rotation_;
	translation_ = builder.translation_;
	patch_ = builder.patch_;
	segment_ = builder.segment_;
	visible_ = builder.visible_;
	locking_ = builder.locking_;
	shading_ = builder.shading_;
	facet_ = builder.facet_;
	color_ = builder.color_;
	color_type_ = builder.color_type_;
	mirror_ = builder.mirror_;
	mirror_axis_ = builder.mirror_axis_;
	mirror_dis_ = builder.mirror_dis_;
	lathe_ = builder.lathe_;
	lathe_axis_ = builder.lathe_axis_;
	lathe_seg_ = builder.lathe_seg_;
    }

    public String getName_() {
	return name_;
    }

    public Vertices getVertices_() {
	return vertices_;
    }

    public Elements getElements_() {
	return elements_;
    }

    public int getDepth_() {
	return depth_;
    }

    public int getFolding_() {
	return folding_;
    }

    public float[] getScale_() {
	return scale_;
    }

    public float[] getRotation_() {
	return rotation_;
    }

    public float[] getTranslation_() {
	return translation_;
    }

    public int getPatch_() {
	return patch_;
    }

    public int getSegment_() {
	return segment_;
    }

    public int getVisible_() {
	return visible_;
    }

    public int getLocking_() {
	return locking_;
    }

    public int getShading_() {
	return shading_;
    }

    public float getFacet_() {
	return facet_;
    }

    public float[] getColor_() {
	return color_;
    }

    public int getColor_type_() {
	return color_type_;
    }

    public int getMirror_() {
	return mirror_;
    }

    public int getMirror_axis_() {
	return mirror_axis_;
    }

    public float getMirror_dis_() {
	return mirror_dis_;
    }

    public int getLathe_() {
	return lathe_;
    }

    public int getLathe_axis_() {
	return lathe_axis_;
    }

    public int getLathe_seg_() {
	return lathe_seg_;
    }

    @Override
    public String toString() {
	StringBuilder builder2 = new StringBuilder();
	builder2.append("MQOObject [color_=");
	builder2.append(Arrays.toString(color_));
	builder2.append(", color_type_=");
	builder2.append(color_type_);
	builder2.append(", depth_=");
	builder2.append(depth_);
	builder2.append(", elements_=");
	builder2.append(elements_);
	builder2.append(", facet_=");
	builder2.append(facet_);
	builder2.append(", folding_=");
	builder2.append(folding_);
	builder2.append(", lathe_=");
	builder2.append(lathe_);
	builder2.append(", lathe_axis_=");
	builder2.append(lathe_axis_);
	builder2.append(", lathe_seg_=");
	builder2.append(lathe_seg_);
	builder2.append(", locking_=");
	builder2.append(locking_);
	builder2.append(", mirror_=");
	builder2.append(mirror_);
	builder2.append(", mirror_axis_=");
	builder2.append(mirror_axis_);
	builder2.append(", mirror_dis_=");
	builder2.append(mirror_dis_);
	builder2.append(", name_=");
	builder2.append(name_);
	builder2.append(", patch_=");
	builder2.append(patch_);
	builder2.append(", rotation_=");
	builder2.append(Arrays.toString(rotation_));
	builder2.append(", scale_=");
	builder2.append(Arrays.toString(scale_));
	builder2.append(", segment_=");
	builder2.append(segment_);
	builder2.append(", shading_=");
	builder2.append(shading_);
	builder2.append(", translation_=");
	builder2.append(Arrays.toString(translation_));
	builder2.append(", vertices_=");
	builder2.append(vertices_);
	builder2.append(", visible_=");
	builder2.append(visible_);
	builder2.append("]");
	return builder2.toString();
    }

}
