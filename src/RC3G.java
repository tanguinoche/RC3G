

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/* Random Connected Geometric Graph Generator */
public class RC3G {
	private int nbPoints = 1000;
	private int graphwidth = 1400;
	private int graphheight = 900;
	private int threshold = 80;
	
	public RC3G() {}
	
	public RC3G(int nbPoints, int graphwidth, int graphheight, int threshold){
		this.nbPoints = nbPoints;
		this.graphwidth = graphwidth;
		this.graphheight = graphheight;
		this.threshold = threshold;
	}
	
	public ArrayList<Point> generate() {
		Random generator = new Random();
		ArrayList<Point> points = new ArrayList<>();
		int minwidth=graphwidth/10*4, maxwidth=graphwidth/10*6;
		int minheight=graphheight/10*4, maxheight=graphheight/10*6;
		boolean init = true;
		for (int i = 1; i <= nbPoints; i++) {
			int x;
			int y;
			Point p;
			do {
				if(i%(nbPoints/5)==0) {
					minwidth=graphwidth/10*4; maxwidth=graphwidth/10*6;
					minheight=graphheight/10*4; maxheight=graphheight/10*6;
				}
				x = generator.nextInt(maxwidth-minwidth)+minwidth;
				y = generator.nextInt(maxheight-minheight)+minheight;
				p = new Point(x,y);
			} while (Tools.nbNeighbors(p, points, threshold) == 0 && !init);
			
			if(x-threshold < minwidth || init) minwidth = Math.max(0, x - threshold);
			
			if(x+threshold > maxwidth || init) maxwidth = Math.min(graphwidth, x + threshold);
			
			if(y-threshold < minheight || init) minheight = Math.max(0, y - threshold);
			
			if(y+threshold > maxheight || init) maxheight = Math.min(graphheight, y + threshold);
			
			init = false; 
			
			points.add(p);
		}
		return points;
	}
	
}
