/**
 * This is a vector with unit length
 * @author Jon
 *
 */
public class UnitVector extends Vector {
	private double x,y,z; // cartesian components
	
	/**
	 * Converta vector to an equivalent unitvector
	 * @param v
	 * @return
	 */
	public static UnitVector unitize(Vector v){
		return new UnitVector(v.getX(), v.getY(), v.getZ());
	}
	
	public UnitVector(double x, double y, double z){
		super(x,y,z);
		double l = Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
		this.x = x/l;
		this.y = y/l;
		this.z = z/l;
	}
	
	public UnitVector(Point3d p1, Point3d p2){
		super(p1,p2);
		double x1 = p2.getX() - p1.getX();
		double y1 = p2.getY() - p1.getY();
		double z1 = p2.getZ() - p1.getZ();
		
		double l = Math.sqrt(Math.pow(x1,2) + Math.pow(y1,2) + Math.pow(z1,2));
		this.x = x1/l;
		this.y = y1/l;
		this.z = z1/l;
	}
	
	public double getX(){return x;}
	public double getY(){return y;}
	public double getZ(){return z;}
}
