package com.rkouchoo.voxel.main;

import org.lwjgl.opengl.Display;

import com.rkouchoo.voxel.renderEngine.DisplayManager;
import com.rkouchoo.voxel.renderEngine.Loader;
import com.rkouchoo.voxel.renderEngine.MasterRenderer;
import com.rkouchoo.voxel.renderEngine.entities.SimpleRectangle;
import com.rkouchoo.voxel.renderEngine.models.RawModel;
import com.rkouchoo.voxel.renderEngine.models.TexturedModel;
import com.rkouchoo.voxel.renderEngine.shaders.StaticShader;
import com.rkouchoo.voxel.renderEngine.textures.ModelTexture;

public class Main {
	
    static MasterRenderer renderer;
	public static Loader loader;
	public static StaticShader staticShader;
	static ModelTexture texture;
	static TexturedModel texModel;
	
	/**
	 * Main setup thread.
	 * @param args
	 */
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();	
		renderer = new MasterRenderer();
		loader = new Loader();
		staticShader = new StaticShader();
		
		RawModel cubeModel = loader.loadToVAO(SimpleRectangle.VERTICIES, SimpleRectangle.INDICIES, SimpleRectangle.UV);
		
		texture = new ModelTexture(loader.loadTexture("dirtTex"));
		texModel = new TexturedModel(cubeModel, texture);
		
		while (!Display.isCloseRequested()) {
			
			renderer.prepare();
			staticShader.start();
			renderer.render(texModel);
			staticShader.stop();
			
			DisplayManager.updateDisplay();
		}
		
		DisplayManager.closeDisplay();
	}

}
	