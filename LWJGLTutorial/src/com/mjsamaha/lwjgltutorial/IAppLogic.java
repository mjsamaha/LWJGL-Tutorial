package com.mjsamaha.lwjgltutorial;


public interface IAppLogic {
	
	void cleanup();
	
	void init(Window window, Scene scene, Render render);
	
	void input(Window window, Scene scene, long diffTimeMillis);
	
	void update(Window window, Scene scene, long diffTimeMillis);

}
