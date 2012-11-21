package org.yourorghere;


import com.sun.opengl.util.Animator;
import com.sun.opengl.util.GLUT;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
 

public class EsferaRotate implements GLEventListener {
    private float velRotacion = 0.0f; //controla velocidad de rotacion de la esfera
   
    public static void main(String[] args) {
        Frame frame = new Frame("OpenGl Graficos");
        GLCanvas canvas = new GLCanvas();
 
        canvas.addGLEventListener(new EsferaRotate());
        frame.add(canvas);
        frame.setSize(640, 550);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {
 
            @Override
            public void windowClosing(WindowEvent e) {
//                Ejecutar este en otro hilo de la cola de eventos AWT para
//                asegúrese de que la llamada a Animator.stop () finaliza antes de
//                salir
                new Thread(new Runnable() {
 
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        //Centrar Frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }
 
    public void init(GLAutoDrawable drawable) {
        display(drawable);
       
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
 
        //Enable VSync
        gl.setSwapInterval(1);
 
        //Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
        
    
        
        
        
        
    }
 
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
 
        if (height <= 0) { // avoid a divide by zero error!
            height = 1;
        }
       
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
 
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLUT glut = new GLUT();
        GLU glu = new GLU();
 
        velRotacion += 2.0f; //Velocidad a la que rota la esfera
       
        //Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        //Reset the current matrix to the "identity"
        gl.glLoadIdentity();
 
        //creamos la ESFERA
        gl.glTranslatef(0.0f, 0.0f, -1.0f); //Permite enfocar la esfera
        gl.glRotatef(velRotacion, 0.0f, 0.0f, -2.0f); //Permite la rotacion de la esfera
        gl.glColor3f( 0.6f , 0.5f , 0.7f );
        glu.gluLookAt(1, 1, 5, 0, 0, 0, 1, 1, 0);
        glut.glutWireSphere(2d, 40, 50);                        // Se coloca con el centro en <0, 0, 0>
        //glut.glutSolidSphere(1d, 5, 5);
 
        //Flush all drawing operations to the graphics card
        gl.glFlush();
    }
 
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}
