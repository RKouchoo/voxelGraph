 package com.rkouchoo.voxel.renderEngine;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import com.rkouchoo.voxel.constants.RenderConstants;
import com.rkouchoo.voxel.renderEngine.entities.Entity;
import com.rkouchoo.voxel.renderEngine.shaders.StaticShader;

public class MasterRenderer {
	
	Matrix4f projectionMatrix;

	public MasterRenderer(StaticShader shader) {
		createProjectionMatrix();
		System.out.println("hi");
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(RenderConstants.GL_SKY_COLOR[0], RenderConstants.GL_SKY_COLOR[1], RenderConstants.GL_SKY_COLOR[2], RenderConstants.GL_SKY_COLOR[3]);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	public void render(Entity entity, StaticShader shader) {
		EntityRenderer.render(entity, shader);
	}
	
	public void createProjectionMatrix() {
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float yScale = (float) (1f / Math.tan(Math.toRadians(RenderConstants.VIEW_FOV / 2f)));
		float xScale = yScale / aspectRatio;
		float zp = RenderConstants.FAR_PLANE_DISTANCE + RenderConstants.NEAR_PLANE_DISTANCE;
		float zm = RenderConstants.FAR_PLANE_DISTANCE - RenderConstants.NEAR_PLANE_DISTANCE;
		
		this.projectionMatrix = new Matrix4f();
		
		this.projectionMatrix.m00 = xScale;
		this.projectionMatrix.m11 = yScale;
		this.projectionMatrix.m22 = -zp/zm;
		this.projectionMatrix.m23 = -1;
		this.projectionMatrix.m32 = -(2 * RenderConstants.FAR_PLANE_DISTANCE * RenderConstants.NEAR_PLANE_DISTANCE) / zm;
		this.projectionMatrix.m33 = 0;
	}
}
