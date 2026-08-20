package com.mjsamaha.lwjgltutorial;

import java.util.HashMap;
import java.util.Map;

public class Scene {
	
	private Projection projection;
	
	private Map<String, Mesh> meshMap;
	
	public Scene(int width, int height) {
		meshMap = new HashMap<>();
		projection = new Projection(width, height);
	}
	
	public Projection getProjection() {
		return projection;
	}
	
	public void resize(int width, int height) {
		projection.updateProjMatrix(width, height);
	}
	
	public void addMesh(String meshId, Mesh mesh) {
		meshMap.put(meshId, mesh);
	}
	
	public void cleanup() {
        meshMap.values().forEach(Mesh::cleanup);
    }

    public Map<String, Mesh> getMeshMap() {
        return meshMap;
    }

}
