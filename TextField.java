import processing.core.*;
import processing.event.*;

public class TextField extends Label{
	
	TextField(PApplet parent, int x, int y, int w, int h){
		super(parent,x,y,w,h);
		stroke_color.setAlpha(255);
		background.setAlpha(255);
		this.text = "TextField";
	}

	@Override
	public void onKeyPress(char key, int keyCode){
		//Backspace
		if(keyCode == 8){
			if(text.length() > 0){
				String result = text.substring(0, text.length()-1);
				text = result;
				parent.println(text.length());
			}
		}
		else if(keyCode != 0)//No key
		{
			text += key;
		}
	}
}