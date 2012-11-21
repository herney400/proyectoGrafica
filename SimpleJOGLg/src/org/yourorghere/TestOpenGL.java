package org.yourorghere;


import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
 
/**
 * TestOpenGL
 * author: Rafa Gallardo
 *
 */
public class TestOpenGL implements GLEventListener {
 
    public static void main(String[] args) {
        Frame frame = new Frame("OpenGl Graficos");
        GLCanvas canvas = new GLCanvas();
 
        canvas.addGLEventListener(new TestOpenGL());
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
 
        //Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        //Reset the current matrix to the "identity"
        gl.glLoadIdentity();
 
        //Move the "drawing cursor" around
        gl.glTranslatef(-2.0f, 1.4f, -6.0f);
 
        //Dibuja triangulo
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(1.0f, 0.5f, 0.0f);    
            gl.glVertex3f(0.0f, 1.0f, 0.0f);  
            gl.glColor3f(0.5f, 1.0f, 0.0f);    
            gl.glVertex3f(-1.0f, -1.0f, 0.0f);
            gl.glColor3f(0.0f, 0.5f, 1.0f);    
            gl.glVertex3f(1.0f, -1.0f, 0.0f);  
        //Finished Drawing The Triangle
        gl.glEnd();
        
//              gl.glBegin(GL.GL_TRIANGLES);
//              //  gl.glColor3ub((GLubyte)255,(GLubyte)0,(GLubyte)0);
//                gl.glVertex3f(0.0f,200.0f,0.0f);
//              //   Green on the right bottom corner
//             //   gl.glColor3ub((GLubyte)0,(GLubyte)255,(GLubyte)0);
//                gl.glVertex3f(200.0f,-70.0f,0.0f);
//              //   Blue on the left bottom corner
//               // gl.glColor3ub((GLubyte)0,(GLubyte)0,(GLubyte)255);
//                gl.glVertex3f(-200.0f, -70.0f, 0.0f);
//        gl.glEnd();

        
        
        //Move the "drawing cursor" to another position
        gl.glTranslatef(2.0f, 0.0f, -1.0f);
        //Dibuja cuadrado strip
        gl.glBegin(GL.GL_QUAD_STRIP);
            gl.glColor3f(0.5f, 0.5f, 1.0f);    
            gl.glVertex3f(-1.0f, 1.0f, 0.0f);
            gl.glColor3f(1.0f, 0.0f, 0.5f);  
            gl.glVertex3f(1.0f, 1.0f, 0.0f);
            gl.glColor3f(0.0f, 1.0f, 0.5f);  
            gl.glVertex3f(1.0f, -1.0f, 0.0f);  
            gl.glColor3f(1.0f, 0.0f, 1.0f);  //establece color
            gl.glVertex3f(-1.0f, -1.0f, 0.0f);
        //Done Drawing The Quad
        gl.glEnd();
       
         gl.glTranslatef(-1.5f, -2.6f, -0.2f);
        //Dibuja cuadrado
        gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(0.8f, 1.0f, 0.5f);    
            gl.glVertex3f(-1.0f, 1.0f, 0.0f);  
            gl.glColor3f(0.8f, 0.5f, 0.5f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
            gl.glColor3f(1.0f, 1.0f, 0.0f);
            gl.glVertex3f(-1.0f, -1.0f, 0.0f);
            gl.glColor3f(0.8f, 0.0f, 1.0f);
            gl.glVertex3f(-2.0f, 0.0f, 0.0f);
        //Done Drawing The Quad
        gl.glEnd();
       
        gl.glTranslatef(2.5f, -0.5f, -0.2f);
        //Dibuja Poligono
        gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(1.0f, 0.0f, 0.3f);    
            gl.glVertex3f(-1.0f, 1.0f, 0.0f);  
            gl.glColor3f(0.8f, 0.5f, 1.0f);
            gl.glVertex3f(-0.5f, 1.0f, 0.0f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
            gl.glColor3f(1.0f, 0.7f, 0.9f);
            gl.glVertex3f(0.0f, 0.5f, 0.0f);
            gl.glVertex3f(-1.0f, -1.0f, 0.0f);
            gl.glColor3f(1.0f, 0.7f, 0.1f);
            gl.glVertex3f(-2.0f, 0.0f, 0.0f);
        //Done Drawing The Quad
        gl.glEnd();
       
        gl.glTranslatef(0.5f, -0.5f, -3.0f);
        gl.glLineWidth(4.0f); //establece grosor lineas
        //Dibuja lineas
        gl.glBegin(GL.GL_LINES);
            gl.glColor3f(0.5f, 1.0f, 0.0f);
            gl.glVertex3f(1.0f, 0.0f, 0.0f);
            gl.glColor3f(0.2f, 0.7f, 0.0f);
            gl.glVertex3f(2.0f, 6.0f, 0.0f);
        gl.glEnd();
 
        //Flush all drawing operations to the graphics card
        gl.glFlush();
       
        gl.glTranslatef(0.5f, -0.5f, -3.0f);
        gl.glLineWidth(4.0f); //establece grosor lineas
        //Dibuja lineas
        gl.glBegin(GL.GL_LINES);
            gl.glColor3f(1.0f, 0.2f, 0.5f);
            gl.glVertex3f(4.0f, -2.0f, 0.0f);
            gl.glColor3f(0.2f, 1.0f, 0.5f);
            gl.glVertex3f(1.5f, 6.0f, 0.0f);
        gl.glEnd();
 
        //Flush all drawing operations to the graphics card
        gl.glFlush();
    }
 
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}