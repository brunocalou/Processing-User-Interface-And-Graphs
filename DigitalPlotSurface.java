import processing.core.*;
import processing.event.*;

public class DigitalPlotSurface extends PlotSurface{
	
	DigitalPlotSurface(PApplet parent, int x, int y, int w, int h){
		super(parent,x,y,w,h);
	}

	@Override
	protected void drawPoints(){
		parent.beginShape(parent.LINES);
		for(int i = first_index_to_plot + 1; i < curve.size(); i++){
			parent.vertex(curve.get(i-1).x, curve.get(i-1).y);
			parent.vertex(curve.get(i-1).x, curve.get(i).y);
			parent.vertex(curve.get(i-1).x, curve.get(i).y);
			parent.vertex(curve.get(i).x, curve.get(i).y);
		}
		parent.endShape();
	}
}