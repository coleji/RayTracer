/*
 * HW 4 
 * "Ray Tracer"
 * Mick Martin
 * Chris Rusyniak
 * Jonathan Cole
 */


/*
 * Coordinate System:
 * 
 * Tableau: (0,0,0) -> (1000,1000,1000)
 * Screen: (250,250,-433) -> (750,750,-433)
 * Focus: (500,500,-866)
 * Max Ray Angle: 30deg
 * 
 */


import java.awt.*;

import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.*;
import javax.media.opengl.glu.*;


public class RayTracer implements GLEventListener
{
    private JLabel statusLine = new JLabel(); // for misc messages at bottom of window
    private GLU glu = new GLU();
    

    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.setSwapInterval(1); // for animation synchronized to refresh rate
        gl.glClearColor(1f,1f,1f,1f); // white background

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        System.out.println("reshaping to " + width + "x" + height);

        GL2 gl = drawable.getGL().getGL2();  
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        //TODO: what is this crap
    //    gl.glOrtho(0f, 500f, 0f, 500f, -500f, 0f);
        gl.glOrtho(0,500,0,500,0,500);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    

    public void display(GLAutoDrawable drawable) {
        GL2 gl  = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glPointSize(2f);        
        gl.glBegin(GL2.GL_POINTS);           

        
        Point3d focus = new Point3d(500.00d, 500.00d, -866.00d);
        Traceable[] objects = {
        		new Plane(0d,0d,-1d,1000d,Color.RED), // back
        		new Plane(1d,0d,0d,0d,Color.RED), // left
        		new Plane(-1d,0d,0d,1000d,Color.RED), // right
        		new Plane(0d,1d,0d,0d,Color.RED), // bottom
        		new Plane(0d,-1d,0d,1000d,Color.RED) // top
        };
        Point3d light = new Point3d(100d,900d,700d);
        
        for (double i=0; i<500; i++){
        	for (double j=0; j<500; j++){
        		Ray ray = new Ray(focus, new Point3d(250d+i,250d+j,-433.00d));
        		Point3d intersect = null;
        		Traceable intersectObj = null;
        		gl.glColor3f(0f,0f,0f);
        		for (int k=0; k<objects.length; k++) {
        			Point3d intersectTemp = objects[k].intersect(ray);
        			if (intersectTemp.getZ() > -866 && (intersect == null || focus.distance(intersectTemp) < focus.distance(intersect))) {
        				intersect = intersectTemp;
        				intersectObj = objects[k];
        			}
        			/*
        			System.out.println((float)objects[k].getColor().getRed());
        			System.out.println((float)objects[k].getColor().getGreen());
        			System.out.println((float)objects[k].getColor().getBlue());
        			System.out.println(intersect.getX());
        			System.out.println(intersect.getY());
        			System.out.println(intersect.getZ() + "\n\n");
        			*/
        			
        		}

        		Vector lightVector = new Vector(intersect,light);
        		double lightDistance = intersect.distance(light);
        		Vector normal = intersectObj.getNormal(intersect);
        		
        		
        		UnitVector lightVectorUV = UnitVector.unitize(lightVector);
        		UnitVector normalUV = UnitVector.unitize(normal);


        		double dim = lightVectorUV.dot(normalUV); 
        		
        		dim = dim / (.7d + (.2d * lightDistance * lightDistance));
        		
        		dim = (dim < 0.001) ? 0.001 : dim;
        		gl.glColor3f((float)dim*intersectObj.getColor().getRed(),(float)dim*intersectObj.getColor().getGreen(),(float)dim*intersectObj.getColor().getBlue());
        		gl.glVertex3d(i,j,0);
        	}
        }
        System.out.println("done");
        
        
        gl.glEnd();                         

        // check for errors, at least once per frame
        int error = gl.glGetError();
        if (error != GL2.GL_NO_ERROR) {
            System.out.println("OpenGL Error: " + glu.gluErrorString(error));
   
            System.exit(1);
        }
    }

    public void dispose(GLAutoDrawable drawable) {
    }

    public static void main(String[] args) {
        GLProfile.initSingleton();
        System.setProperty("sun.awt.noerasebackground", "true"); // sometimes necessary to avoid erasing over the finished window

        JFrame frame = new JFrame("Ray Tracer");
        GLCanvas canvas = new GLCanvas();
        canvas.setPreferredSize(new Dimension(500,500));  // desired size, not guaranteed

        RayTracer renderer = new RayTracer();
        canvas.addGLEventListener(renderer);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(canvas, BorderLayout.CENTER);
        frame.getContentPane().add(renderer.statusLine, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        /*
         FPSAnimator animator = new FPSAnimator(canvas,60);
         animator.start();
		*/
    }
}