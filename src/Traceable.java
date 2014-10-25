import java.awt.Color;

public abstract class Traceable {
	protected Color color;
	
	public abstract Point3d intersect(Ray r);
	
	public abstract Vector getNormal(Point3d p);
	
	public abstract Color getColor();
	
}
