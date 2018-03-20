package com.rkouchoo.voxel.renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import com.rkouchoo.voxel.constants.DisplayConstants;
import com.rkouchoo.voxel.main.Main;

public class DisplayManager {
	
	static ContextAttribs contextAttribs;
	
	public static void createDisplay() {
		
		contextAttribs = new ContextAttribs(3, 2)
				.withForwardCompatible(true)
				.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(DisplayConstants.WINDOW_WIDTH, DisplayConstants.WINDOW_HEIGHT));
			Display.create(new PixelFormat(), contextAttribs);
			Display.setTitle(DisplayConstants.DISPLAY_TITLE);
			Display.setFullscreen(DisplayConstants.DISPLAY_FULLSCREEN_MODE);
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		} catch (LWJGLException e) {
			//e.printStackTrace();
			System.err.println("Encounted an error creating the main window!");
		}
		
		Mouse.setGrabbed(true);
	}
	
	public static void updateDisplay() {
		Display.sync(DisplayConstants.REFRESH_RATE);
		Display.update();
	
	
		while(Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				
				/*
				 * Close the display if the 'esc' key has been pressed
				 */
				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					closeDisplay();
				}
				
				/*
				 * Checks if the 'E' Key has been pressed and toggles between grabbed / not grabbed.
				 */
				if (Keyboard.isKeyDown(Keyboard.KEY_E) && Mouse.isGrabbed()) {
					Mouse.setGrabbed(false);
				} else if (Keyboard.isKeyDown(Keyboard.KEY_E) && !Mouse.isGrabbed()) {
					Mouse.setGrabbed(true);
				}
			}
		}
		
	}
	
	public static void closeDisplay() {
		
		Main.loader.cleanUp();
		Main.staticShader.cleanUp(); 
		
		Display.destroy();
		System.exit(1);
	}

}
