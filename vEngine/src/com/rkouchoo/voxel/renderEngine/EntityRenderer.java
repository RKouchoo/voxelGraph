package com.rkouchoo.voxel.renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.rkouchoo.renderEngine.models.RawModel;

public class EntityRenderer {

	public static void render(RawModel model) {
		GL30.glBindVertexArray(model.getVAOId());
		GL20.glEnableVertexAttribArray(0);
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertextCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
}
