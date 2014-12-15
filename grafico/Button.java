import processing.core.*;
import processing.event.*;

public class Button extends Label{

	Color onPressColor = new Color(220,220,220,255);
	Color onHoverColor = new Color(240,240,240,255);
	Color normalColor = new Color(255,255,255,255);

	Color onPressTextColor = new Color(255,255,255,255);
	Color onHoverTextColor = new Color(200,200,200,255);
	Color normalTextColor = new Color(0,0,0,255);

	Button(PApplet parent, int x, int y, int w, int h){
		super(parent, x, y, w, h);
		stroke_color.setAlpha(255);	//The button has a border
		background.setAlpha(255);	//The button has a background
		text = "Button";
	}

	@Override
	public void onPress(int mouse_x, int mouse_y, int button){
		setBackgroundColor(onPressColor);
		setTextColor(onPressTextColor);
	}

	@Override
	public void onRelease(int mouse_x, int mouse_y, int button){
		setBackgroundColor(normalColor);
		setTextColor(normalTextColor);
	}

	@Override
	public void onMouseLeave(int mouse_x, int mouse_y){
		setBackgroundColor(normalColor);
		setTextColor(normalTextColor);
	}

	@Override
	public void onHover(int mouse_x, int mouse_y){
		setBackgroundColor(onHoverColor);
		setTextColor(onHoverTextColor);
	}

	//Background

	public void setOnPressColor(int r, int g, int b, int a){
		onPressColor.setColor(r,g,b,a);
	}

	public void setOnHoverColor(int r, int g, int b, int a){
		onHoverColor.setColor(r,g,b,a);
	}

	public void setNormalColor(int r, int g, int b, int a){
		normalColor.setColor(r,g,b,a);
	}

	//Text

	public void setOnPressTextColor(int r, int g, int b, int a){
		onPressTextColor.setColor(r,g,b,a);
	}

	public void setOnHoverTextColor(int r, int g, int b, int a){
		onHoverTextColor.setColor(r,g,b,a);
	}

	public void setNormalTextColor(int r, int g, int b, int a){
		normalTextColor.setColor(r,g,b,a);
	}
}