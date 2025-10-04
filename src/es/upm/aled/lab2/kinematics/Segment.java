package es.upm.aled.lab2.kinematics;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a segment with its length and angle.
 * It also contains a List of Segments called children.
 * @author blancadantart
 */

public class Segment {
	private double length;
	private double angle;
	private List <Segment> children = new ArrayList <Segment>();
	
/** Builds a new Segment.
 * @param length Represents the length of the segment in cm
 * @param angle Represents the angle the segment forms with the father segment, in radians
 */
	public Segment (double length,double angle) {
		this.length=length;
		this.angle=angle;
	}
/** 
 * Returns the length
 *
 * @return The length
 */
	public double getLength() {
		return length;
	}
/**
 * Returns the angle
 * 
 * @return The angle
 */
	public double getAngle() {
		return angle;
	}
	
	public void setAngle(double angle) {
		this.angle=angle;
	}
	
	/** 
	 * Returns the Segments this one is father of. 
	 * 
	 * @return A List of all de children Segments.
	 */
	public List <Segment> getChildren(){
		return children;
	}
/** Adds a new Segment to the children List if this one is not already in it.
 * 
 * @param child the new child we want to add to the List.
 * 
 */
	public void addChild(Segment child) {
		if(!children.contains(child))
			children.add(child);
	
	}
}
