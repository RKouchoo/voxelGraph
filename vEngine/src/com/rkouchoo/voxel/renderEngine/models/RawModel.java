package com.rkouchoo.voxel.renderEngine.models;

public class RawModel {

	private int vaoID;
	private int vertexCount;
	
	public RawModel(int id, int count) {
		this.vaoID = id;
		this.vertexCount = count;
	}
	
	
	public int getVAOId() {
		return vaoID;
	}
	
	public int getVertextCount() {
		return vertexCount;
	}
	
}
