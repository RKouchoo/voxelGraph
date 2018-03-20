package com.rkouchoo.voxel.renderEngine;

import java.util.List;

import javax.sound.midi.SysexMessage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.rkouchoo.voxel.renderEngine.models.RawModel;

public class Loader {

	static List<Integer> vaos = new ArrayList<Integer>();
	static List<Integer> vbos = new ArrayList<Integer>();
	static List<Integer> textures = new ArrayList<Integer>();
	
	public RawModel loadToVAO(float[] verticies, int[] indicies, float[] uv) {
		int vaoID = createVAO();
		vaos.add(vaoID);
		storeDataInAttributeList(verticies, 0, 3);
		storeDataInAttributeList(uv, 1, 2);
		bindIndiciesBuffer(indicies);
		GL30.glBindVertexArray(0);
		return new RawModel(vaoID, indicies.length);
	}
	
	private int createVAO() {
		int VaoID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(VaoID);
		
		return VaoID;
	}
	
	public int loadTexture(String fileName) {
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", Class.class.getResourceAsStream("/res/" + fileName + ".PNG"));
		} catch (IOException e) {
			System.err.println("Failed to load texture!");
			e.printStackTrace();
		}
		
		int textureId = texture.getTextureID();
		textures.add(textureId);
		
		return textureId;
	}
	
	private void storeDataInAttributeList(float[] data, int attributeNumber, int dimensions) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer dataBuffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, dataBuffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber, dimensions, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private void bindIndiciesBuffer(int[] indicies) { 
		int vboId = GL15.glGenBuffers();
		vbos.add(vboId);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboId);
		IntBuffer buffer = storeDataInIntBuffer(indicies);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	IntBuffer storeDataInIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		
		return buffer;
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();

		return buffer;
	}
	
	/*
	 * Cleans up all of the VAOS and VBOS
	 */
	public void cleanUp() {
		for (int vao : vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for (int vbo : vbos) {
			GL15.glDeleteBuffers(vbo);
		}
		for (int tex : textures) {
			GL11.glDeleteTextures(tex);
		}
	}

}
