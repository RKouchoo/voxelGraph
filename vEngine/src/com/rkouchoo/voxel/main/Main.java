package com.rkouchoo.voxel.main;

import org.lwjgl.opengl.Display;

import com.rkouchoo.renderEngine.entities.SimpleRectangle;
import com.rkouchoo.renderEngine.models.RawModel;
import com.rkouchoo.voxel.renderEngine.DisplayManager;
import com.rkouchoo.voxel.renderEngine.Loader;
import com.rkouchoo.voxel.renderEngine.MasterRenderer;

public class Main {
	
    static MasterRenderer renderer;
	public static Loader loader;
	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();	
		renderer = new MasterRenderer();
		loader = new Loader();
		
		RawModel cubeModel = loader.loadToVAO(SimpleRectangle.VERTICIES, SimpleRectangle.INDICIES);
		
		while (!Display.isCloseRequested()) {
			DisplayManager.updateDisplay();
			renderer.prepare();
			renderer.render(cubeModel);
		}
		
		DisplayManager.closeDisplay();
	}

}
	