package com.rkouchoo.voxel.renderEngine.shaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public abstract class ShaderProgram {
	
	int programId;
	int vertexShaderId;
	int fragmentShaderId;
	
	FloatBuffer matrixBuffer;
	
	public ShaderProgram(String vertexFile, String fragmentFile) {
		this.programId = GL20.glCreateProgram();
		this.vertexShaderId = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		this.fragmentShaderId = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		
		matrixBuffer = BufferUtils.createFloatBuffer(16);
		
		GL20.glAttachShader(this.programId, this.vertexShaderId);
		GL20.glAttachShader(this.programId, this.fragmentShaderId);
		bindAttributes();
		GL20.glLinkProgram(programId);
		GL20.glValidateProgram(this.programId);
		
		getAllUniformLocations();
	}
	
	protected abstract void getAllUniformLocations();
	
	protected int getUniformLocation(String variableName) {
		return GL20.glGetUniformLocation(this.programId, variableName);
	}
	
	protected abstract void bindAttributes(); 
	
	protected void loadFloat(int location, Float value) {
		GL20.glUniform1f(location, value);
	}
	
	protected void load2DVector(int location, Vector2f vec) {
		GL20.glUniform2f(location, vec.x, vec.y);
	}
	
	protected void load3DVector(int location, Vector3f vec) {
		GL20.glUniform3f(location, vec.x, vec.y, vec.z);
	}
	
	protected void loadMatrix(int location, Matrix4f mat) {
		mat.store(matrixBuffer);
		matrixBuffer.flip();
		
		GL20.glUniformMatrix4(location, false, matrixBuffer);
	}
	
	protected void loadBoolean(int location, boolean bool) {
		float value = bool ? 1.0f : 0.0f;
		GL20.glUniform1f(location, value);
	}
	
	protected void bindAttribute(String variableName, int attribute) {
		GL20.glBindAttribLocation(this.programId, attribute, variableName);
	} 
	
	public void start() {
		GL20.glUseProgram(this.programId);
	}
	
	public void stop() {
		GL20.glUseProgram(0);
	}
	
	public void cleanUp() {
		stop();
		GL20.glDetachShader(this.programId, this.vertexShaderId);
		GL20.glDetachShader(this.programId, this.fragmentShaderId);
		GL20.glDeleteShader(this.vertexShaderId);
		GL20.glDeleteShader(this.fragmentShaderId);
		GL20.glDeleteProgram(this.programId);
	}
	
	private int loadShader(String file, int type) {
		StringBuilder shaderSource = new StringBuilder();
		InputStream in = Class.class.getResourceAsStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				shaderSource.append(line).append("//\n");
			}
			reader.close();
		} catch (IOException e) {
			System.exit(-1);
		}
		
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		
		if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Failed to compile shader program!");
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 1000));
			System.exit(-1);
		}
		
		return shaderID;
	}

}
