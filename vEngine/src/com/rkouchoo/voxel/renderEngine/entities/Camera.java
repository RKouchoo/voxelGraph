package com.rkouchoo.voxel.renderEngine.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import com.rkouchoo.voxel.constants.CameraConstants;

public class Camera {
	
	private Vector3f position;
	private float rotX;
	private float rotY;
	private float rotZ;
	
	private float moveAt;
	
	public Camera(Vector3f position, float rotX, float rotY, float rotZ) {
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
	}
	
	public void move() {
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			 moveAt += -CameraConstants.MOVEMENT_SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			 moveAt += CameraConstants.MOVEMENT_SPEED;
		} else {
			moveAt = 0;
		}
		
		rotX += -Mouse.getDY() * CameraConstants.TURN_SPEED;
		rotY += Mouse.getDX() * CameraConstants.TURN_SPEED;
		
		float dx = (float) -(moveAt * Math.sin(Math.toRadians(rotY)));
		float dy = (float) (moveAt * Math.sin(Math.toRadians(rotX)));
		float dz = (float) (moveAt * Math.cos(Math.toRadians(rotZ)));
		
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;

	}

	public Vector3f getPosition() {
		return position;
	}

	public float getRotX() {
		return rotX;
	}
 
	public float getRotY() {
		return rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

}
