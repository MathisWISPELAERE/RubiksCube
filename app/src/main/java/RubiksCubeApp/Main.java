import main.Java.RubiksCube;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class Main {
    static RubiksCube cube;

    public static void main(String[] args) throws InterruptedException{

        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("GLFW init failed");
        }

        long window = GLFW.glfwCreateWindow(800, 600, "Rubik's Cube", 0, 0);
        if (window == 0) throw new RuntimeException("Erreur création fenêtre");

        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSetKeyCallback(window, new GLFWKeyCallback() {
            @Override
            public void invoke(long window,
                               int key,
                               int scancode,
                               int action,
                               int mods) {
                boolean shift = (mods & GLFW.GLFW_MOD_SHIFT) != 0;
                if (key == GLFW.GLFW_KEY_W && action == GLFW.GLFW_PRESS){
                    if(shift){
                        Main.cube.rotationFace(RubiksCube.H, false);
                    }else{
                        Main.cube.rotationFace(RubiksCube.H, true);
                    }
                }else if (key == GLFW.GLFW_KEY_A && action == GLFW.GLFW_PRESS){
                    if(shift){
                        Main.cube.rotationFace(RubiksCube.G, false);
                    }else{
                        Main.cube.rotationFace(RubiksCube.G, true);
                    }
                }else if (key == GLFW.GLFW_KEY_S && action == GLFW.GLFW_PRESS){
                    if(shift){
                        Main.cube.rotationFace(RubiksCube.A, false);
                    }else{
                        Main.cube.rotationFace(RubiksCube.A, true);
                    }
                }else if (key == GLFW.GLFW_KEY_D && action == GLFW.GLFW_PRESS){
                    if(shift){
                        Main.cube.rotationFace(RubiksCube.D, false);
                    }else{
                        Main.cube.rotationFace(RubiksCube.D, true);
                    }
                }else if (key == GLFW.GLFW_KEY_F && action == GLFW.GLFW_PRESS){
                    if(shift){
                        Main.cube.rotationFace(RubiksCube.P, false);
                    }else{
                        Main.cube.rotationFace(RubiksCube.P, true);
                    }
                }else if (key == GLFW.GLFW_KEY_C && action == GLFW.GLFW_PRESS){
                    if(shift){
                        Main.cube.rotationFace(RubiksCube.B, false);
                    }else{
                        Main.cube.rotationFace(RubiksCube.B, true);
                    }
                }         
            };}                         
        );
        GL.createCapabilities();

        // Projection 2D orthographique (pixels)
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 600, 0, -1, 1); // origine en haut à gauche
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        Main.cube = new RubiksCube();
        Renderer renderer = new Renderer();

        // Test : applique quelques rotations          
        
                    
        GLFW.glfwShowWindow(window);

        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClearColor(0.15f, 0.15f, 0.15f, 1f);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            renderer.drawUnfolded(cube);
            
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }

        GLFW.glfwTerminate();
    }
}