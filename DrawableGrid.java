import processing.core.*;
import processing.event.*;

/*
	
	This class is just a grid that can be drawn on the screen

*/
public class DrawableGrid extends Widget{

	int step_x = 1;
	int step_y = 1;
	//The scale multiplies the step.
	//E.g. 1 - If the step is 1 and the scale is 100,
	//there will be 100 pixels to 1 unity.
	//and the lines will be drawn on each 100 pixels
	//E.g. 2 - If the step is 2 and the scale is 100,
	//there will be 100 pixels to 2 units
	//and the lines will be drawn on each 200 pixels
	float scale_x = 100;
	float scale_y = 100;

	boolean show_horizontal_lines = true;
	boolean show_vertical_lines = true;

	DrawableGrid(PApplet parent, int x, int y, int w, int h){
		super(parent,x,y,w,h);
		background.setAlpha(0);
	}

	@Override
	public void display(){
		super.display();
		if(show_vertical_lines)
		{
			for(int i = this.x; i < this.x + this.w; i += step_x*scale_x){
				parent.line(i, this.y, i, this.y + this.h);
			}
		}
		if(show_horizontal_lines)
		{
			for(int j = this.y; j < this.y + this.h; j += step_y*scale_y){
				parent.line(this.x, j, this.x + this.w, j);
			}
		}
		
		
	}

	public void setStep(int step_x, int step_y){
		this.step_x = step_x;
		this.step_y = step_y;
	}

	public void setScale(float scale_x, float scale_y){
		this.scale_x = scale_x;
		this.scale_y = scale_y;
	}

	public void setGridColor(int r, int g, int b, int a){
		setStroke(this.stroke_weight,r,g,b,a);
	}

	public void setGridThickness(float thickness){
		setStroke(thickness,stroke_color.r,stroke_color.g,stroke_color.b,stroke_color.a);
	}

	public int getXStep(){
		return step_x;
	}

	public int getYStep(){
		return step_y;
	}

	public void setVisibility(boolean horizontal_lines, boolean vertical_lines){
		show_horizontal_lines = horizontal_lines;
		show_vertical_lines = vertical_lines;
	}

	public int getNumberOfXCells(){
		return (int) (w/(step_x*scale_x));
	}

	public int getNumberOfYCells(){
		return (int) (y/(step_y*scale_y));
	}
}
	