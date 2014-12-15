import processing.core.*;
import processing.event.*;

public class PlotSurface extends Widget{

	Curve curve = new Curve();
	boolean hide_curve = false;

	//Multiplies the curve values for a better visualisation
	float scale_x = 50;
	float scale_y = 50;

	float curve_stroke_weight = 0.5f/scale_x;
	Color curve_stroke = new Color(0,0,0,255);

	boolean adjust_y_scale = true;
	boolean adjust_x_movement = true;

	int first_index_to_plot = 0;
	int max_number_of_values = -1;

	boolean draw_grid = true;
	public DrawableGrid grid;

	//Holds the number of intervals on the grid.
	final int number_of_intervals = 4;

	protected float biggest_y_value = 0;

	boolean is_digital = false;


	PlotSurface(PApplet parent, int x, int y, int w, int h){
		super(parent,x,y,w,h);
		scale_x = h/(2*number_of_intervals);
		scale_y = scale_x;

		grid = new DrawableGrid(parent,x,y,w,h);
		grid.setGridColor(0,0,0,50);
		grid.setScale(1,1);
		grid.setMouseBypass(true);
		setGridStep();
	}

	@Override
	public void display(){
		super.display();

		if(!hide_curve){
			drawCurve();
		}		
	}

	@Override
	public void hide(){
		super.hide();
		grid.hide();
		hide_curve = true;
	}

	@Override
	public void show(){
		super.show();
		grid.show();
		hide_curve = false;
	}

	public void setDigital(boolean state){
		is_digital = state;
	}

	public boolean isDigital(){
		return is_digital;
	}

	protected void drawPoints(){
		parent.beginShape(parent.LINES);
		for(int i = first_index_to_plot + 1; i < curve.size(); i++){//last_index_to_plot
			float x0 = curve.get(i-1).x;
			float y0 = curve.get(i-1).y;
			float x1 = curve.get(i).x;
			float y1 = curve.get(i).y;
			
			//This will work if and only if the zero is centered on the graph

			//Crops the y if it's bigger than the height/2
			if(parent.abs(y0)*scale_y > h/2){
				if(y0 > 0){
					y0 = h/2/scale_y;	
				} 
				else{
					y0 = -h/2/scale_y;
				}
			}
			if(parent.abs(y1)*scale_y > h/2){
				if(y1 > 0){
					y1 = h/2/scale_y;	
				} 
				else{
					y1 = -h/2/scale_y;
				}
			}
			if((parent.abs(x1) - parent.abs(curve.get(first_index_to_plot).x))*scale_x > w){
				break;
			}
			parent.vertex(x0, y0);
			parent.vertex(x1,y1);
		}
		parent.endShape();
	}

	private void drawDigitalPoints(){
		parent.beginShape(parent.LINES);
		for(int i = first_index_to_plot + 1; i < curve.size(); i++){
			if(parent.abs(curve.get(i-1).y*scale_y) > h/2){
				continue;
			}
			if((parent.abs(curve.get(i).x) - parent.abs(curve.get(first_index_to_plot).x))*scale_x > w){
				break;
			}
			parent.vertex(curve.get(i-1).x, curve.get(i-1).y);
			parent.vertex(curve.get(i-1).x, curve.get(i).y);
			parent.vertex(curve.get(i-1).x, curve.get(i).y);
			parent.vertex(curve.get(i).x, curve.get(i).y);
		}
		parent.endShape();
	}

	public void drawCurve(){
		if(!curve.isEmpty()){

			//Curve Stroke
			parent.strokeWeight(curve_stroke_weight);
			parent.stroke(curve_stroke.r,curve_stroke.g,curve_stroke.b,curve_stroke.a);
	
			//Change the coordinate system
			parent.pushMatrix();
			parent.resetMatrix();
			parent.translate(this.x - curve.get(first_index_to_plot).x*scale_x, this.y + this.h/2);
			parent.scale(1,-1);	//Change the default y orientation
			parent.scale(scale_x, scale_y);
	
			// Draw curve
			if(is_digital){
				drawDigitalPoints();
			} else {
				drawPoints();
			}
	
			parent.popMatrix();
		}
	}

	private void setGridStep(){
		grid.setStep(h/(2*number_of_intervals),h/(2*number_of_intervals));
	}

	public void add(float x, float y, float z){
		curve.add(new Vertex(x,y,z));

		//Checks if the graph must be scaled vertically
		if(adjust_y_scale){
			float positive_y = parent.abs(y);

			if(positive_y*scale_y > this.h/2){
				scale_y = (this.h/2)/positive_y;
			}
		}		

		//Checks if the graph must be moved horizontally
		if(adjust_x_movement){
			while((x - curve.get(first_index_to_plot).x)*scale_x > this.w){
				first_index_to_plot ++;
			}
		}
		

		//Checks maximum number of values
		if(max_number_of_values > 0 && curve.size() > max_number_of_values){
			curve.remove(0);
			//Updates the first index to plot
			if(first_index_to_plot > 0){
				first_index_to_plot --;
			}
		}
	}

	public void add(float x, float y){
		add(x,y,0);
	}

	public void add(float value){
		add(parent.millis()/100.0f,value);
	}

	protected void adjustYScale(){
		biggest_y_value = 0;
		float current_y = 0;

		for(int i = first_index_to_plot; i < curve.size(); i++){
			current_y = parent.abs(curve.get(i).y);
			
			if(current_y > biggest_y_value){
				biggest_y_value = current_y;
			}
		}

		if(biggest_y_value > 0){
			scale_y = (this.h/2)/biggest_y_value;
		}
	}	

	public void setCurveStroke(float weight, int r, int g, int b, int a){
		curve_stroke_weight = weight / scale_x;
		curve_stroke.setColor(r,g,b,a);
	}

	public void setMaxNumberOfValues(int max){
		max_number_of_values = max;
	}

	public void enableGrids(boolean state){
		draw_grid = state;
	}

	public void setScale(float scale_x, float scale_y){
		//This scale multiplies the input values in order to
		//show them on the screen. So, if the scale is 100 and
		//the input value is 1 and them it's 1 again, it will be
		//a line with 100 pixels
		this.scale_x = scale_x;
		this.scale_y = scale_y;
	}

	@Override 
	public void setPosition(int x, int y){
		super.setPosition(x,y);
		grid.setPosition(x,y);
	}

	@Override
	public void setSize(int w, int h){
		if(w > 0 && h > 0){
			super.setSize(w,h);
			grid.setSize(w,h);
			setGridStep();
			adjustYScale();
		}
	}

	@Override
	public void onWheel(int mouse_x, int mouse_y, int count){
		if(scale_x - count > 0){
			scale_x -= count;
		}
		if(scale_y - count > 0){
			scale_y -= count;
		}
	}

	@Override
	public void onDrag(int mouse_x, int mouse_y, int button){
		if(button == parent.LEFT){
			int delta_x = mouse_x - window.getLastMouseX();
			adjust_x_movement = false;

			//The mouse moved to the right
			if(delta_x > 0){
				
				if(first_index_to_plot - delta_x < 0){
					first_index_to_plot = 0;
				} else {
					first_index_to_plot -= delta_x;
				}
			} else {
				//The mouse moved to the left
				if(first_index_to_plot - delta_x > curve.size() - 1 && curve.size() > 0){
					first_index_to_plot = curve.size() - 1;
				} else {
					first_index_to_plot -= delta_x;
				}	
			}

			//If the curve is being drawn, set the automatic x adjustment
			if((parent.abs(curve.get(curve.size()-1).x) - parent.abs(curve.get(first_index_to_plot).x))*scale_x < w){
					adjust_x_movement = true;
				}
		}
	}

	public float getYScale(){
		return scale_y;
	}

	public float getXScale(){
		return scale_x;
	}

	public void setCurve(Curve curve){
		if(curve != null)
			this.curve = curve;
	}
}