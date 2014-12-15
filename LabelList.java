import processing.core.*;
import processing.event.*;
import java.util.ArrayList;

/*

	This class manages lables in a row or column. It could be
	generalized to manage any widget, but there was no need
	for that in the program

*/
public class LabelList extends Widget{
	
	ArrayList<Label> list = new ArrayList<Label>();
	int text_size = 12;

	//Holds if the arrangement is vertical of horizontal
	boolean is_vertical = false;

	//Spacing between the labels
	int step = 20;

	// Draw the labels in the reverse order
	boolean reverse = false;

	//Limits the number of elements
	int max_number_of_elements = 100;

	LabelList(PApplet parent, int x, int y, int w, int h){
		super(parent,x,y,w,h);
		is_vertical = false;
		getBackgroundColor().setAlpha(255);
	}

	public boolean add(String text){
		//Adds a label on the list

		if(list.size() >= max_number_of_elements){
			return false;
		}

		Label label = new Label(parent,x,y,w,h);
		label.setText(text);
		label.setTextSize(text_size);
		label.setMouseBypass(true);
		label.ignoreDimensions(true);
		
		if(is_vertical){
			label.setPosition(list.size()*step,y);
			label.setTextAlign(parent.CENTER,parent.TOP);
		} else {
			label.setPosition(x, list.size()*step);
			label.setTextAlign(parent.LEFT,parent.CENTER);
		}
		parent.textSize(text_size);
		label.setSize((int)label.getRealTextWidth(), (int)label.getRealTextHeight());
		
		list.add(label);

		adjust();

		return true;
	}

	public void adjust(){
		//Iterates over all the lables and adjust their positions
		if(list.isEmpty()){
			return ;
		}

		int x = list.get(0).getX();
		int y = list.get(0).getY();
		int w = 0;
		int h = 0;

		if(!is_vertical){
			w = (int)getBiggestTextWidth();
			h = list.get(list.size()-1).getY() - list.get(0).getY();

			if(reverse){
				h*= -1;
				y = list.get(list.size()-1).getY();
			}
			
		} else {
			w = list.get(list.size()-1).getX() - list.get(0).getX();
			h = (int)getBiggestTextHeight();
			if(reverse){
				w*= -1;
				x = list.get(list.size()-1).getX();
			}
		}
		
		this.setSize(w,h);
		super.setPosition(x,y);
	}

	public void setVertical(){
		is_vertical = true;
		adjust();
	}

	public void setHorizontal(){
		is_vertical = false;
		adjust();
	}

	@Override
	public void display(){
		//Prevents from drawing the bounding box
	}

	@Override
	public void setPosition(int x, int y){
		super.setPosition(x,y);

		int counter = 0;
		if(reverse){
			counter = list.size()-1;
		}
		//Updates all the labels

		for(int i = 0; i < list.size(); i++){
			if(is_vertical){
				list.get(i).setPosition(parent.abs(counter - i)*step + x,y);
			} else {
				list.get(i).setPosition(x, parent.abs(counter - i)*step + y);
			}
		}

		adjust();
		
	}

	public void setStep(int step){
		this.step = step;
		setPosition(this.getX(),this.getY());
	}

	public void setReverse(boolean state){
		reverse = state;
	}

	public void setTextSize(int size){
		text_size = size;
		for(int i = 0; i < list.size(); i++){
			list.get(i).setTextSize(size);
		}
	}

	public float getBiggestTextHeight(){
		float biggest = 0;
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getRealTextHeight() > biggest){
				biggest = list.get(i).getRealTextHeight();
			}
		}
		return biggest;
	}

	public float getBiggestTextWidth(){
		float biggest = 0;
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getRealTextWidth() > biggest){
				biggest = list.get(i).getRealTextWidth();
			}
		}
		return biggest;
	}

	public void setLabelSize(int w, int h){
		for(int i = 0; i < list.size(); i++){
			list.get(i).setSize(w,h);
		}
	}

	@Override
	public void hide(){
		super.hide();
		for(int i = 0; i < list.size(); i++){
			list.get(i).hide();
		}
	}

	@Override
	public void show(){
		super.show();
		for(int i = 0; i < list.size(); i++){
			list.get(i).show();
		}
	}

	public int size(){
		return list.size();
	}

	public void setText(int index, String text){
		list.get(index).setText(text);
	}
	
	public Label remove(int index){
		Label label = list.remove(index);
		adjust();
		if(label != null){
			label.delete();
		}
		return label;
	}
}