package com.rkouchoo.voxel.renderEngine.shaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL20;

public abstract class ShaderProgram {
	
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
		
		
		
	}

}