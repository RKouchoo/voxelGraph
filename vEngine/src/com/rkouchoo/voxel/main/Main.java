package com.rkouchoo.voxel.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import com.rkouchoo.voxel.constants.TerrainGenerationConstants;
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
	
	// 7, 31m
	
    static MasterRenderer renderer;
	public static Loader loader;
	public static StaticShader staticShader;
	static ModelTexture texture;
	static TexturedModel texModel;
	static Entity entitySingle;
	static Camera camera;
	
	static List<Entity> entities;
	static List<Vector3f> blockPositions;
	static Vector3f camPos;
	
	/**
	 * Main setup thread.
	 * @param args
	 */
	public static void main(String[] args) {
		entities = new ArrayList<Entity>();
		blockPositions = new ArrayList<Vector3f>();
		camPos = new Vector3f(0, 0, 0);
		
		DisplayManager.createDisplay();	
		loader = new Loader();
		staticShader = new StaticShader();
		renderer = new MasterRenderer(staticShader);
		camera = new Camera(new Vector3f(0, 0, 0), 0, 0, 0);

		RawModel cubeModel = loader.loadToVAO(SimpleCube.VERTICIES, SimpleCube.INDICIES, SimpleCube.UV);
		
		texture = new ModelTexture(loader.loadTexture("dirtTex"));
		texModel = new TexturedModel(cubeModel, texture);

		/*
		 * create a new thread and loop for generating terrain.
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(!Display.isCloseRequested()) {
					genFlatTerrain();
				}
			}
		}).start();;
		
		/*
		 * Main game loop
		 */
		while (!Display.isCloseRequested()) {
			camera.move();
			camPos = camera.getPosition();
			renderer.prepare();
			staticShader.start();
			staticShader.loadViewMatrix(camera);
			
			for (int i = 0; i < entities.size(); i++) {
				renderer.render(entities.get(i), staticShader);
			}
			
			staticShader.stop();
			DisplayManager.updateDisplay();
		}
		
		DisplayManager.closeDisplay();
	}

	/*
	 * Simple tool for generating flat terrain around the camera
	 */
	public static void genFlatTerrain() {
		Random r = new Random();
		for (int x = (int) camPos.x - TerrainGenerationConstants.TERRAIN_X_WIDTH; x < (int) camPos.x + TerrainGenerationConstants.TERRAIN_X_WIDTH; x++) {
			for (int z = (int) camPos.z - TerrainGenerationConstants.TERRAIN_Z_WIDTH; z < (int) camPos.z + TerrainGenerationConstants.TERRAIN_Z_WIDTH; z++) {
				
				if (!blockPositions.contains(new Vector3f(x, 0, z))) {
					entities.add(new Entity(texModel, new Vector3f(x, -100f, z), 0, 0, 0, 1));
					blockPositions.add(new Vector3f(x, 0, z));
					} else {
						// do nothing a block already exists
					}
			}	
		}
	}
	
}
	