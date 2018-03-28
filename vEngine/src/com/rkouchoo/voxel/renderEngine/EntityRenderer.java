package com.rkouchoo.voxel.renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import com.rkouchoo.voxel.renderEngine.entities.Entity;
import com.rkouchoo.voxel.renderEngine.models.TexturedModel;
import com.rkouchoo.voxel.renderEngine.shaders.StaticShader;
import com.rkouchoo.voxel.toolbox.Maths;

public class EntityRenderer {

	public static void render(Entity entity, StaticShader shader) {
		GL30.glBindVertexArray(entity.getModel().getModel().getVAOId());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, entity.getModel().getTexture().getTextureId());
		GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getModel().getModel().getVertextCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
}
