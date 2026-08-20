package com.mjsamaha.lwjgltutorial;

import org.lwjgl.Version;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main {
	
	private long window;

	
	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion());
		
		init();
		loop();
		
		// free the window callbacks and destroy window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		
		// terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	private void init() {

	    // Set error callback
	    GLFWErrorCallback.createPrint(System.err).set();

	    // Initialize GLFW
	    if (!glfwInit()) {
	        throw new IllegalStateException("Unable to initialize GLFW");
	    }

	    // Configure GLFW
	    glfwDefaultWindowHints();
	    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
	    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

	    // Create the window
	    window = glfwCreateWindow(
	            600,
	            800,
	            "LWJGL Demo",
	            NULL,
	            NULL
	    );

	    if (window == NULL) {
	        throw new RuntimeException("Failed to create GLFW window");
	    }

	    // Setup key callback
	    glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {

	        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
	            glfwSetWindowShouldClose(window, true);
	        }

	    });

	    try (MemoryStack stack = stackPush()) {

	        IntBuffer pWidth = stack.mallocInt(1);
	        IntBuffer pHeight = stack.mallocInt(1);

	        // Get window size
	        glfwGetWindowSize(window, pWidth, pHeight);

	        // Get primary monitor resolution
	        GLFWVidMode vidmode =
	                glfwGetVideoMode(glfwGetPrimaryMonitor());

	        // Center window
	        glfwSetWindowPos(
	                window,
	                (vidmode.width() - pWidth.get(0)) / 2,
	                (vidmode.height() - pHeight.get(0)) / 2
	        );
	    }

	    // Make OpenGL context current
	    glfwMakeContextCurrent(window);

	    // Enable V-Sync
	    glfwSwapInterval(1);

	    // Show window
	    glfwShowWindow(window);
	}
		

	private void loop() {
		// critical for LWJGL interoperation with GLFW's OpenGL context
		GL.createCapabilities(); 
		
		// set clear color
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		
		// run rendering loop until user closes
		while (!glfwWindowShouldClose(window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear framebuffer
			
			glfwSwapBuffers(window); // swap color buffers
			
			// poll for events, key callback
			glfwPollEvents();
		}
	
	}
	
	public static void main(String[] args) {
		new Main().run();
	}
}
