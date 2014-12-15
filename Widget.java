import processing.core.*;
import processing.event.*;

public class Widget {

	//Holds the widget's layer. It's used to
	//know witch widget is over when drawing
	//them and on mouse events
	private static int layer_counter = 0;

	protected final PApplet parent;

	protected int x = 0;
	protected int y = 0;
	protected int w = 100;
	protected int h = 100;
	protected int min_h = 5;
	protected int min_w = 5;

	private boolean is_resizable = true;
	private boolean is_hidden = false;

	//Background colors
	protected Color background = new Color(255,255,255,255);

	//Foreground colors
	protected Color foreground = new Color(0,0,0,255);

	//Stroke
	protected float stroke_weight = 1.0f;
	protected Color stroke_color = new Color(0,0,0,255);

	//Static variable that manages all the widgets. It controls
	//the drawing, the events and the focus
	protected static final Window window = new Window();

	//If bypass is true, the widget will not be verified for the events
	private boolean bypass_mouse = false;
	private boolean bypass_keyboard = false;

	Widget(PApplet parent, int x, int y, int w, int h){

		this.parent = parent;

		if(layer_counter == 0){
			//Setup the window
			window.setParent(parent);
			parent.registerMethod("mouseEvent", window);
			parent.registerMethod("draw", window);
			parent.registerMethod("keyEvent", window);
		}
		//Add the widget to the window
		window.register(this);
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		//Increases layer counter so as the next widget
		//will be on top
		layer_counter ++;
	}


	public void onClick(int mouse_x, int mouse_y, int button){}//parent.println("Clicked at " + this.getClass().getName());
	
	public void onHover(int mouse_x, int mouse_y){}//parent.println("OVER " + this.getClass().getName());

	public void onMouseEnter(int mouse_x, int mouse_y){}//parent.println("Entered at " + this.getClass().getName());

	public void onMouseLeave(int mouse_x, int mouse_y){}//parent.println("Left " + this.getClass().getName());

	public void onPress(int mouse_x, int mouse_y, int button){}//parent.println("Pressed at x = " + mouse_x + " y = " + mouse_y);

	public void onRelease(int mouse_x, int mouse_y, int button){}

	public void onDrag(int mouse_x, int mouse_y, int button){}//parent.println("Dragged at x = " + mouse_x + " y = " + mouse_y);

	public void onWheel(int mouse_x, int mouse_y, int count){}//parent.println("Count = " + count);

	public void onKeyPress(char key, int keyCode){}//parent.println("key pressed");
	
	public void onKeyType(char key, int keyCode){}//parent.println("key released");
	
	public void onKeyRelease(char key, int keyCode){}//parent.println("key type");

	public void show(){

		is_hidden = false;
	}
	
	public void hide(){

		is_hidden = true;
	}

	protected void display(){
		//Configures the screen parameters and draw the widget
		parent.colorMode(parent.RGB,255);
		parent.rectMode(parent.CORNER);
		parent.stroke(stroke_color.r, stroke_color.g, stroke_color.b, stroke_color.a);
		parent.strokeWeight(stroke_weight);
		parent.fill(background.r,background.g,background.b,background.a);
		parent.rect(x,y,w,h);
		parent.fill(foreground.r,foreground.g,foreground.b,foreground.a);
	}

	public boolean isOver(int x, int y){
		//Returns if the point (x,y) is over the widget
		return !(x < this.x || x > this.x + this.w ||
				 y < this.y || y > this.y + this.h);
	}

	public void setBackgroundColor(int r, int g, int b, int a){
		background.setColor(r,g,b,a);
	}

	public void setBackgroundColor(Color color){
		background = color;
	}

	public void setForegroundColor(int r, int g, int b, int a){
		foreground.setColor(r,g,b,a);
	}

	public void setForegroundColor(Color color){
		foreground = color;
	}

	public Color getBackgroundColor(){
		return background;
	}

	public Color getForegroundColor(){
		return foreground;
	}

	public Color getStrokeColor(){
		return stroke_color;
	}

	public void setStroke(float weight, int r, int g, int b, int a){
		stroke_weight = weight;
		stroke_color.setColor(r,g,b,a);
	}

	public void setSize(int w, int h){
		if(w > min_w){
			this.w = w;
		}
		if(h > min_h){
			this.h = h;
		}
	}

	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void setMouseBypass(boolean state){
		bypass_mouse = state;
	}

	public void setKeyboardBypass(boolean state){
		bypass_keyboard = state;
	}

	public boolean isBypassingMouse(){
		return bypass_mouse;
	}

	public boolean isBypassingKeyboard(){
		return bypass_keyboard;
	}

	public int getLayerNumber(){
		return layer_counter;
	}

	public boolean isHidden(){
		return is_hidden;
	}

	public void goTop(){
		//Puts the widget on the top of all the other widgets
		window.goTop(this);
	}

	public void goBottom(){
		//Puts the widget on the bottom of all the other widgets
		window.goBottom(this);
	}

	public void goUp(){
		//Trades the widget's layer with the next one
		window.goUp(this);
	}

	public void goDown(){
		//Trades the widget's layer with the previous one
		window.goDown(this);
	}

	public boolean delete(){
		//This function will remove the widget from the 
		//widget list, so it will not be drawn automatically
		//and it will loose it's events properties
		return window.remove(this);
	}

	public int getH(){
		return h;
	}

	public int getW(){
		return w;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public boolean setH(int h){
		if(h > min_h){
			this.h = h;
			return true;
		}
		return false;
	}

	public boolean setW(int w){
		if(w > min_w){
			this.w = w;
			return true;
		}
		return false;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public void setResizable(boolean state){
		is_resizable = state;
	}

	public boolean isResizable(){
		return is_resizable;
	}
}