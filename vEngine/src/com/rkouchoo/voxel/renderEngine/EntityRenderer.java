package com.rkouchoo.voxel.renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.rkouchoo.voxel.renderEngine.models.TexturedModel;

public class EntityRenderer {

	public static void render(TexturedModel model) {
		GL30.glBindVertexArray(model.getModel().getVAOId());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureId());
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getModel().getVertextCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
}
