package com.rkouchoo.voxel.renderEngine.shaders;

import org.lwjgl.util.vector.Matrix4f;

import com.rkouchoo.voxel.constants.StaticShaderConstants;
import com.rkouchoo.voxel.renderEngine.entities.Camera;
import com.rkouchoo.voxel.toolbox.Maths;

public class StaticShader extends ShaderProgram {
	
	int location_transformationMatrix;
	int location_projectionMatrix;
	int location_viewMatrix;
	
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
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
	}

	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(location_projectionMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera) {
		super.loadMatrix(location_viewMatrix, Maths.createViewMatrix(camera));
	}
}
