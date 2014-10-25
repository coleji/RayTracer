
public class Ray {
	private Point3d start;
	private UnitVector dir;
	
	/**
	 * Constructs a ray given start point and direction vector
	 * @param start
	 * @param dir
	 */
	public Ray(Point3d start, Vector dir){
		this.start = start;
		this.dir = UnitVector.unitize(dir); // converts vector to unit length
	}

	/**
	 * Constructs a ray given a start point and a second point along the ray.
	 * @param start
	 * @param mid
	 */
	public Ray(Point3d start, Point3d mid){
		this.start = start;
		Vector v = new Vector(start.getX()+mid.getX(),start.getY()+mid.getY(),start.getZ()+mid.getZ());
		this.dir = UnitVector.unitize(v); // converts vector to unit length
	}	
	
	/**
	 * Returns a point along a ray given a distance value from the start point
	 * @param t
	 * @return
	 */
	public Point3d getPoint(double t){
		double x = start.getX() + dir.getX()*t;
		double y = start.getY() + dir.getY()*t;
		double z = start.getZ() + dir.getZ()*t;
		return new Point3d(x,y,z);
	}
	
	/**
	 * Given a ray and a point, returns true if point is on the ray
	 * @param point
	 * @return
	 */
	public boolean testPoint(Point3d point){
		double distanceX = (point.getX() - start.getX())/dir.getX();
		double distanceY = (point.getY() - start.getY())/dir.getY();
		double distanceZ = (point.getZ() - start.getZ())/dir.getZ();
		boolean correctDir = ((point.getX() - start.getX()) > 0 && dir.getX() > 0) || ((point.getX() - start.getX()) < 0 && dir.getX() < 0) || point.getX() - start.getX() == 0;
		if (distanceX == distanceY && distanceY == distanceZ && correctDir){
			return true;
		} else return false;
	}
	
	public Point3d getStart(){return start;}
	public Vector getDir(){return dir;}
}
