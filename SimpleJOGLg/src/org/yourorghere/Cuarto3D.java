package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.GLUT;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
 
public class Cuarto3D implements GLEventListener {
    public static final double Pi = 3.14159265358979323846;
   
    public static void main(String[] args) {
        Frame frame = new Frame("OpenGl Graficos");
        GLCanvas canvas = new GLCanvas();
         int i;
        canvas.addGLEventListener(new Cuarto3D());
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
     
       
        //Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        //Reset the current matrix to the "identity"
        gl.glLoadIdentity();
 
        //creamos la ESFERA
        gl.glTranslatef(0.0f, 0.0f, -4.0f); //Permite enfocar
       
        //Dibujo piso
        gl.glColor3f(0.36f, 0.278f, 0.196f);
        
        gl.glBegin(gl.GL_QUADS);
            gl.glVertex3f(-2.0f, -1.7f, 0.0f);
            gl.glVertex3f(2.0f, -1.7f, 0.0f);
            gl.glVertex3f(2.0f, -0.5f, 0.0f);
            gl.glVertex3f(-0.5f, -0.5f, 0.0f);
        gl.glEnd();
       
        //dibuja pared izquierda
        gl.glColor3f(0f, 255f, 0f);
       
        gl.glBegin(gl.GL_QUADS);
            gl.glVertex3f(-2.0f, -1.7f, 0.0f);
            gl.glVertex3f(-0.5f, -0.5f, 0.0f);
            gl.glVertex3f(-0.5f, 1.7f, 0.0f);
            gl.glVertex3f(-2.0f, 1.7f, 0.0f);
            
        gl.glEnd();
         
       
         gl.glColor3f(0.9f, 0.6f, 0.5f);
         gl.glBegin(gl.GL_QUADS);
            gl.glVertex3f(-0.0f, 0.0f, 0.0f);
            gl.glVertex3f(0.0f, 1.8f, 0.0f);
            gl.glVertex3f(2.0f, -0.8f, 0.0f);
            gl.glVertex3f(-0.5f, -0.8f, 0.0f);
            
         gl.glEnd();
        gl.glColor3f(0.3f, 0.3f, 0.1f);   
        gl.glBegin(gl.GL_QUAD_STRIP);
        gl.glVertex3f( -5.0f, 0.0f, 1.0f );
        gl.glVertex3f( 1.0f, 0.0f, 3.0f );
        gl.glVertex3f( 0.0f, 1.0f, 2.0f );
        gl.glEnd( );
        
        
        
          //dibuja pared derecha
         gl.glColor3f(0.176f, 0.675f, 0.433f);
         gl.glBegin(gl.GL_QUADS);
            gl.glVertex3f(-0.5f, 1.7f, 0.0f);
            gl.glVertex3f(2.0f, 1.7f, 0.0f);
            gl.glVertex3f(2.0f, -0.5f, 0.0f);
            gl.glVertex3f(-0.5f, -0.5f, 0.0f);
        gl.glEnd();
       
        //dibuja marco exterior ventana
        gl.glColor3f(0.0f, 0.0f, 0.0f);
         gl.glBegin(gl.GL_QUADS);
            gl.glVertex3f(0.0f, 0.2f, 0.0f);
            gl.glVertex3f(1.0f, 0.2f, 0.0f);
            gl.glVertex3f(1.0f, 1.2f, 0.0f);
            gl.glVertex3f(0.0f, 1.2f, 0.0f);
        gl.glEnd();
       
        //dibuja marco interno ventana
        gl.glColor3f(0.314f, 0.74f, 0.894f);
        gl.glBegin(gl.GL_QUADS);
            gl.glVertex3f(0.2f, 0.35f, 0.0f);
            gl.glVertex3f(1.0f, 0.35f, 0.0f);
            gl.glVertex3f(1.0f, 1.2f, 0.0f);
            gl.glVertex3f(0.2f, 1.2f, 0.0f);
        gl.glEnd();
       
        //Para inferior ventana
        gl.glColor3f(0.36f, 0.278f, 0.196f);
        gl.glBegin(gl.GL_QUADS);
            gl.glVertex3f(0.0f, 0.2f, 0.0f);
            gl.glVertex3f(1.0f, 0.2f, 0.0f);
            gl.glVertex3f(1.0f, 0.35f, 0.0f);
            gl.glVertex3f(0.2f, 0.35f, 0.0f);
        gl.glEnd();
        //gl.glTranslatef(0.0f, 0.0f, -4.0f); //Permite enfocar
       
          //Dibuja cuadro pared izquierda
        gl.glColor3f(0.57f, 0.376f, 0.0f);
        gl.glBegin(gl.GL_QUADS);
            gl.glVertex3f(-1.6f, -0.5f, 0.0f);
            gl.glVertex3f(-0.7f, 0.1f, 0.0f);
            gl.glVertex3f(-0.7f, 1.1f, 0.0f);
            gl.glVertex3f(-1.6f, 0.5f, 0.0f);
        gl.glEnd();
       
         //Dibuja cuarteto de la pared izquierda
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glBegin(gl.GL_POLYGON);
            gl.glVertex3f(-1.15f, -0.2f, 0.0f);
            gl.glVertex3f(-0.7f, 0.6f, 0.0f);
            gl.glVertex3f(-1.15f, 0.8f, 0.0f);
            gl.glVertex3f(-1.6f, 0.0f, 0.0f);
        gl.glEnd();
        
        gl.glTranslatef(2.8f, 3.5f, -12.0f); //Permite enfocar el sol
        gl.glColor3f(1.0f, 1.0f, 0.0f); //pinta de amarillo el sol
        //Dibuja el sol
        gl.glBegin(gl.GL_POLYGON);
            for(int i=0; i<100; i++){
               float x = (float) Math.cos(i*2*Pi/100);
               float y = (float) Math.sin(i*2*Pi/100);
               gl.glVertex2f(x, y);
            }
        gl.glEnd();
       
     
       
       
        gl.glFlush();
    }
 
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}