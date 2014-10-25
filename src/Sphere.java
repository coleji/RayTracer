import java.awt.Color;

public class Sphere extends Traceable {
	private Point3d c; // center
	private double r;  // radius
	
	public Sphere(Point3d c, double r){
		this.c = c;
		this.r = Math.abs(r); // no negative values for radius
	}
	
	public Point3d intersect(Ray r){
		Point3d p1 = r.getStart();
		Point3d p2 = new Point3d(p1.getX() + r.getDir().getX(), p1.getY() + r.getDir().getY(), p1.getZ() + r.getDir().getZ());
		
		double x1 = p1.getX();
		double y1 = p1.getY();
		double z1 = p1.getZ();
		double x2 = p2.getX();
		double y2 = p2.getY();
		double z2 = p2.getZ();
		double x3 = c.getX();
		double y3 = c.getY();
		double z3 = c.getZ();
		
		double a = Math.pow(x2-x1,2) + Math.pow(y2-y1,2) + Math.pow(z2-z1,2);
		double b = 2*((x2-x1)*(x1-x3) + (y2-y1)*(y1-y3) + (z2-z1)*(z1-z3));
		double c = Math.pow(x3,2) + Math.pow(y3,2) + Math.pow(z3,2) + Math.pow(x1,2) + Math.pow(y1,2) + Math.pow(z1,2) - 2*(x3*x1 + y3*y1 + z3*z1) - Math.pow(this.r,2);
		
		double u1 = (-1*b + Math.sqrt(Math.pow(b,2)-4*a*c))/(2*a);
		double u2 = (-1*b - Math.sqrt(Math.pow(b,2)-4*a*c))/(2*a);
		
		Point3d int1 = p1.add(p2.add(p1.multiply(-1)).multiply(u1));
		Point3d result;
		if (u2 != u1){
			Point3d int2 = p1.add(p2.add(p1.multiply(-1)).multiply(u2));
			result = (p1.distance(int1) < p1.distance(p2)) ? int1 : int2;
		} else
			result = int1;
		return result;
	}


	public Vector getNormal(Point3d p) {
		// TODO Auto-generated method stub
		return null;
	}


	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
}
