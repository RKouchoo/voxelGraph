package com.rkouchoo.voxel.main;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import com.rkouchoo.voxel.renderEngine.DisplayManager;
import com.rkouchoo.voxel.renderEngine.Loader;
import com.rkouchoo.voxel.renderEngine.MasterRenderer;
import com.rkouchoo.voxel.renderEngine.entities.Camera;
import com.rkouchoo.voxel.renderEngine.entities.Entity;
import com.rkouchoo.voxel.renderEngine.entities.SimpleCube;
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
	static Entity entity;
	static Camera camera;
	
	/**
	 * Main setup thread.
	 * @param args
	 */
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();	
		loader = new Loader();
		staticShader = new StaticShader();
		renderer = new MasterRenderer(staticShader);
		camera = new Camera(new Vector3f(0, 0, 0), 0, 0, 0);

		RawModel cubeModel = loader.loadToVAO(SimpleCube.VERTICIES, SimpleCube.INDICIES, SimpleCube.UV);
		
		texture = new ModelTexture(loader.loadTexture("dirtTex"));
		texModel = new TexturedModel(cubeModel, texture);
		entity = new Entity(texModel, new Vector3f(0, 0, -1), 0, 0, 0, 1);
		
		while (!Display.isCloseRequested()) {
			
			camera.move();
			
			renderer.prepare();
			staticShader.start();
			staticShader.loadViewMatrix(camera);
			
			//entity.increasePosition(0.001f, 0f, 0f);
			entity.increaseRotation(1f, 1f, 1f);
			//entity.increaseScale(-.001f);
			
			renderer.render(entity, staticShader);
			staticShader.stop();
			
			DisplayManager.updateDisplay();
		}
		
		DisplayManager.closeDisplay();
	}

}
	