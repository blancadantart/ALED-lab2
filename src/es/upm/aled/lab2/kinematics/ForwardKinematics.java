package es.upm.aled.lab2.kinematics;

import es.upm.aled.lab2.gui.Node;

/**
 * This class implements a forward kinematics algorithm using recursion. It
 * expects a tree of Segments (defined by its length and angle with respect to
 * the previous Segment in the tree) and returns a tree of Nodes (defined by
 * their absolute coordinates in a 2-dimensional space).
 * 
 * @author rgarciacarmona
 */
public class ForwardKinematics {

	/**
	 * Returns a tree of Nodes to be used by SkeletonPanel to draw the position of
	 * an exoskeleton. This method is the public facade to a recursive method that
	 * builds the result from a tree of Segments defined by their angle and length,
	 * and the relationship between them (which Segment is children of which).
	 * 
	 * @param root    The root of the tree of Segments.
	 * @param originX The X coordinate for the origin point of the tree.
	 * @param originY The Y coordinate for the origin point of the tree.
	 * @return The tree of Nodes that represent the exoskeleton position in absolute
	 *         coordinates.
	 */
	// Public method: returns the root of the position tree
	public static Node computePositions(Segment root, double originX, double originY) {
		// Me dicen que el ángulo acumulado empieza siendo 0
		return computePositions(root,originX,originY,0.0);
	}

	// Private helper method that implements the recursive algorithm
	private static Node computePositions(Segment link, double baseX, double baseY, double accumulatedAngle) {
		long startTime= System.nanoTime();
		
		// Devuelve un NODO! Código común. Genero el nodo
		accumulatedAngle+=link.getAngle();
		double x= baseX + link.getLength()*Math.cos(accumulatedAngle);
		double y= baseY + link.getLength()*Math.sin(accumulatedAngle);
		Node node= new Node(x,y);
		
		// Si el segmento del que estamos hablando no tiene hijos, devuelvo el nodo y acaba el método
		if(link.getChildren().isEmpty()) {
			// Medida de tiempos
			long runningTime = System.nanoTime() - startTime;
			System.out.println("Tiempo de computePositions para un segmento con " + link.getChildren().size() + " hijos: " + runningTime + " nanosegundos");
			return node;
		}
		
		// Recorro la lista de Segmentos hijos del segmento del que estamos hablando.
		// Si tiene hijos, los añado a la lista children del nodo creado. 
		for(Segment c:link.getChildren())
			node.addChild(computePositions(c,x,y,accumulatedAngle));
		
		// Medida de tiempos
		long runningTime = System.nanoTime() - startTime;
		System.out.println("Tiempo de computePositions para un segmento con " + link.getChildren().size() + " hijos: " + runningTime + " nanosegundos");
		// Devuelvo el nodo y acaba el método
		return node;		
	}
}