
public class Vector {
	private double x,y,z;
	
	public Vector(){}
	
	/**
	 * Constructs a vector given x,y,z components
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Constructs a vector given two (ordered) points
	 * @param p1
	 * @param p2
	 */
	public Vector (Point3d p1, Point3d p2){
		x = p2.getX() - p1.getX();
		y = p2.getY() - p1.getY();
		z = p2.getZ() - p1.getZ();
	}
	
	/**
	 * Subtracts one vector form another
	 * @param v
	 * @return
	 */
	public Vector subtract(Vector v){
		return new Vector(x-v.getX(),y-v.getY(),z-v.getZ());
		// TODO: test me
	}
	
	/**
	 * returns the dot product of two vectors
	 * @param v
	 * @return
	 */
	public double dot(Vector v){
		return x*v.getX() + y*v.getY() + z*v.getZ();
	}
	
	/**
	 * returns the reflection vector given an incident vector and reflection surface normal vector
	 * @param normal
	 * @return
	 */
	public Vector getReflection(Vector normal){
		return this.add(normal.multiply(-2*this.dot(normal)));
	}
	
	/**
	 * adds two vectors together
	 * @param v
	 * @return
	 */
	public Vector add(Vector v){
		return new Vector(x+v.getX(),y+v.getY(),z+v.getZ());
	}
	
	/**
	 * Multiplies a vector by a scalar
	 * @param d
	 * @return
	 */
	public Vector multiply(double d){
		return new Vector(x*d,y*d,z*d);
	}
	
	
	public double getX(){return x;}
	public double getY(){return y;}
	public double getZ(){return z;}
	
	public String toString(){
		return "x: "+ x + " y: " + y + " z: " + z;
	}
}
