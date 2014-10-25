import java.awt.Color;

public class Plane extends Traceable {
	private double A,B,C,D;
	
	public Plane (double A, double B, double C, double D){
		this.A = A;
		this.B = B;
		this.C = C;
		this.D = D;
	}

	/**
	 * Given a ray, determines the point of intersection between the ray and the plane
	 */
	public Point3d intersect(Ray r){
		Point3d p1 = r.getStart();
		Point3d p2 = new Point3d(p1.getX() + r.getDir().getX(), p1.getY() + r.getDir().getY(), p1.getZ() + r.getDir().getZ());
		
		double x1 = p1.getX();
		double y1 = p1.getY();
		double z1 = p1.getZ();
		double x2 = p2.getX();
		double y2 = p2.getY();
		double z2 = p2.getZ();
		
		double u = (A*x1 + B*y1 + C*z1 + D)/(A*(x1-x2) + B*(y1-y2) + C*(z1-z2));
		
		return p1.add(p2.add(p1.multiply(-1)).multiply(u));
	}

	/**
	 * Given a point, determines normal vector at that point
	 */
	public Vector getNormal(Point3d p) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return base color of object
	 */
	public Color getColor() {
		return color;
	}
}
