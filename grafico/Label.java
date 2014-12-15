import processing.core.*;
import processing.event.*;

public class Label extends Widget{

	String text = "Label";
	int size = 12;
	int text_align_x;
	int text_align_y;

	//In processing, if the rectangle that bound the text is smaller than the
	//text, the text will not be shown. This variable ignores the bounding box
	//and draws the text
	boolean ignore_dimensions = false;

	Label(PApplet parent, int x, int y, int w, int h){
		super(parent,x,y,w,h);
		stroke_color.setAlpha(0);
		background.setAlpha(0);
		text_align_x = parent.CENTER;
		text_align_y = parent.CENTER;
	}

	public void setText(String text){
		this.text = text;
	}

	public void setTextSize(int size){
		this.size = size;
	}

	public void setTextColor(int r, int g, int b, int a){
		setForegroundColor(r,g,b,a);
	}

	public void setTextColor(Color color){
		setForegroundColor(color);
	}

	@Override
	public void display(){
		super.display();
		parent.textSize(size);
		parent.textAlign(text_align_x,text_align_y);

		if(ignore_dimensions){
			parent.text(text,x,y);
		} else {
			parent.text(text,x,y,w,h);
		}

	}

	public void setTextAlign(int align_x, int align_y){
		text_align_x = align_x;
		text_align_y = align_y;
	}

	public float getRealTextHeight(){
		//Returns the height of the text in pixels. Note that
		//it can be different from the widget's height
		parent.textSize(size);
		return parent.textAscent() - parent.textDescent();
	}

	public float getRealTextWidth(){
		parent.textSize(size);
		return parent.textWidth(text);
	}

	public void ignoreDimensions(boolean state){
		//Ignore the widget's bounding box
		ignore_dimensions = state;
	}

	public String getText(){
		return text;
	}
}