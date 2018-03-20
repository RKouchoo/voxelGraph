package com.rkouchoo.voxel.renderEngine.entities;

/*
 * Numbers to generate a simple rectangle on the screen.
 * will be DEPRECIATED eventually!	
45 */
public class SimpleRectangle {

	public static final int[] INDICIES = {
			0, 1, 2,
			2, 3, 0
	};
	
	public static final float[] VERTICIES = {
			-0.5f, 0.5f, 0,
			-0.5f, -0.5f, 0,
			0.5f, -0.5f, 0, 
			0.5f, 0.5f, 0, 	
	};
	
	public static final float[] UV = {
			0, 0,
			0, 1,
			1, 1,
			1, 0
	};
}
