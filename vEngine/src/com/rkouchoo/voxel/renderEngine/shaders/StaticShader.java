package com.rkouchoo.voxel.renderEngine.shaders;

import org.lwjgl.util.vector.Matrix4f;

import com.rkouchoo.voxel.constants.StaticShaderConstants;

public class StaticShader extends ShaderProgram {
	
	int location_transformationMatrix;
	
	public StaticShader() {
		super(StaticShaderConstants.VERTEX_FILE, StaticShaderConstants.FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute("position", 0);
		super.bindAttribute("textureCoords", 1);
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
	}

	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
}
