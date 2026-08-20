package com.mjsamaha.lwjgltutorial;

import java.util.*;

import static org.lwjgl.opengl.GL30.*;

public class SceneRender {
	
	private ShaderProgram shaderProgram;
	private UniformsMap uniformsMap;
	
	public SceneRender() {
	
        List<ShaderProgram.ShaderModuleData> shaderModuleDataList = new ArrayList<>();
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("res/shaders/scene.vert", GL_VERTEX_SHADER));
        shaderModuleDataList.add(new ShaderProgram.ShaderModuleData("res/shaders/scene.frag", GL_FRAGMENT_SHADER));
        shaderProgram = new ShaderProgram(shaderModuleDataList);
        
        createUniforms();
    }
	
	public void createUniforms() {
		uniformsMap = new UniformsMap(shaderProgram.getProgramId());
		uniformsMap.createUniform("projectionMatrix");
	}
	
	public void cleanup() {
		shaderProgram.cleanup();
	}
	
	public void render(Scene scene) {
        shaderProgram.bind();

        uniformsMap.setUniform("projectionMatrix", scene.getProjection().getProjMatrix());

        scene.getMeshMap().values().forEach(mesh -> {
        	glBindVertexArray(mesh.getVaoId());
        	glDrawElements(GL_TRIANGLES, mesh.getNumVertices(), GL_UNSIGNED_INT, 0);
        });	

        glBindVertexArray(0);

        shaderProgram.unbind();
    }

}
