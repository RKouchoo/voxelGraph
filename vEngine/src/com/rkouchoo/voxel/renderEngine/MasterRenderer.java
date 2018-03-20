 package com.rkouchoo.voxel.renderEngine;

import org.lwjgl.opengl.GL11;

import com.rkouchoo.voxel.constants.RenderConstants;
import com.rkouchoo.voxel.renderEngine.models.TexturedModel;

public class MasterRenderer {

	public void prepare() {
		GL11.glClearColor(RenderConstants.GL_SKY_COLOR[0], RenderConstants.GL_SKY_COLOR[1], RenderConstants.GL_SKY_COLOR[2], RenderConstants.GL_SKY_COLOR[3]);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	
	public void render(TexturedModel model) {
		EntityRenderer.render(model);
	}
}
