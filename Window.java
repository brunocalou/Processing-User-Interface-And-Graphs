import processing.core.*;
import processing.event.*;
import java.util.ArrayList;

public class Window{

	PApplet parent;

	//All widgets
	private ArrayList<Widget> widget_list = new ArrayList<Widget>();

	//Mouse
	protected int last_mouse_x = 0;
	protected int last_mouse_y = 0;


	//Prevents loosing the mouse when it's not over the widget
	//but the mouse is still pressed
	private boolean is_dragging = false;

	//Last widget that the mouse was over
	private Widget last_mouse_over = null;

	//Current dragged widget
	private Widget dragged_widget = null;

	//Focused widget. It's used to know witch widget will
	//receive keystrokes
	private Widget focused_widget = null;

	Window(){
	}

	public void setParent(PApplet parent){
		this.parent = parent;
	}

	public void register(Widget widget){
		widget_list.add(widget);
		if(focused_widget == null){
			focused_widget = widget;
		}
		// parent.println("Added object of class " + widget.getClass().getName());
	}

	public void mouseEvent(processing.event.MouseEvent event){
		int mouse_x = event.getX();
		int mouse_y = event.getY();
		int button = event.getButton();

		//Checks if the widget is beeing dragged. It must be
		//outside the colliding condition to avoid missing the
		//mouse while moving it

		if(dragged_widget != null){
			dragged_widget.onDrag(mouse_x, mouse_y, button);
		}

		//After dragging, check if the mouse is over the new position

		Widget top_widget = null;
		int top_index = -1;
		
		//Get list of widgets under the mouse
		for(int i = widget_list.size() - 1; i >= 0 ; i--){
			
			if(widget_list.get(i).isBypassingMouse()){
				continue;
			}

			//Get widget on top
			if(widget_list.get(i).isOver(mouse_x, mouse_y) && 
				widget_list.get(i).getLayerNumber() > top_index &&
				!widget_list.get(i).isHidden()){
				
				top_index = i;
				break;
				}
		}

		//If the user clicked outside a widget, he is not dragging anymore
		if(event.getAction() == processing.event.MouseEvent.CLICK || 
			event.getAction() == processing.event.MouseEvent.RELEASE){
			dragged_widget = null;
		}		

		//If the mouse is colliding with the widget and it's not hidden,
		//process the events

		if(top_index != -1)
		{
			top_widget = widget_list.get(top_index);
			
			switch (event.getAction()) {
			
			case processing.event.MouseEvent.PRESS:
				dragged_widget = top_widget;
				top_widget.onPress(mouse_x, mouse_y, button);
				break;
			case processing.event.MouseEvent.RELEASE:
				is_dragging = false;
				dragged_widget = null;
				top_widget.onRelease(mouse_x, mouse_y, button);
				break;
			case processing.event.MouseEvent.CLICK:
				focused_widget = top_widget;
				top_widget.onClick(mouse_x, mouse_y, button);
				break;
			case processing.event.MouseEvent.DRAG:
				is_dragging = true;
				break;
			case processing.event.MouseEvent.MOVE:
				top_widget.onHover(mouse_x, mouse_y);
				break;
			case processing.event.MouseEvent.WHEEL:
				top_widget.onWheel(mouse_x, mouse_y, event.getCount());
				break;
  			}

  			//If the last widget under the mouse is different from
  			//the current, the mouse has left the last widget and
  			//entered on another
  			if(top_widget != last_mouse_over)
  			{
  				if(last_mouse_over != null){
  					last_mouse_over.onMouseLeave(mouse_x, mouse_y);
  				}
  				top_widget.onMouseEnter(mouse_x, mouse_y);
  				last_mouse_over = top_widget;
  			}

  			//Update the mouse position
  			last_mouse_x = mouse_x;
  			last_mouse_y = mouse_y;

		} else if(last_mouse_over != null) {
			//If there was an widget under the mouse on the last event,
			//and now there isnt, the mouse has left the last widget
			last_mouse_over.onMouseLeave(mouse_x, mouse_y);
			last_mouse_over = null;
		}
	}

	public void keyEvent(processing.event.KeyEvent event){
		if(focused_widget != null)
		{
			// parent.println("Parent = " + focused_widget.getClass().getName() + 
				// "key = " + event.getKey() + " key code = " + event.getKeyCode());
			switch(event.getAction()){
			case (processing.event.KeyEvent.PRESS):
				focused_widget.onKeyPress(event.getKey(), event.getKeyCode());
				break;
			case (processing.event.KeyEvent.RELEASE):
				focused_widget.onKeyRelease(event.getKey(), event.getKeyCode());
				break;
			case (processing.event.KeyEvent.TYPE):
				focused_widget.onKeyType(event.getKey(), event.getKeyCode());
				break;
			}
		}
	}

	public void draw(){
		//Draw all widgets in order
		for(int i = 0; i < widget_list.size(); i++){
			if(!widget_list.get(i).isHidden())
			{
				widget_list.get(i).display();
			}
		}
	}

	public int getLastMouseX(){
		return last_mouse_x;
	}

	public int getLastMouseY(){
		return last_mouse_y;
	}

	public int goTop(Widget widget){
		//Puts the widget on the top of all the other widgets
		int index = widget_list.indexOf(widget);
		widget_list.set(index, widget_list.get(widget_list.size()-1));
		widget_list.set(widget_list.size()-1, widget);
		return widget_list.size() - 1;
	}

	public int goBottom(Widget widget){
		//Puts the widget on the bottom of all the other widgets
		int index = widget_list.indexOf(widget);
		widget_list.set(index, widget_list.get(0));
		widget_list.set(0, widget);
		return 0;
	}

	public int goUp(Widget widget){
		//Trades the widget's layer with the next one
		int index = widget_list.indexOf(widget);
		if(index == widget_list.size() - 1){
			return index;
		}
		widget_list.set(index, widget_list.get(index+1));
		widget_list.set(index+1, widget);
		return index+1;
	}

	public int goDown(Widget widget){
		//Trades the widget's layer with the previous one
		int index = widget_list.indexOf(widget);
		if(index == 0){
			return index;
		}
		widget_list.set(index, widget_list.get(index-1));
		widget_list.set(index-1, widget);
		return index-1;
	}

	public boolean remove(Widget widget){
		//Removes the widget from the list
		return widget_list.remove(widget);
	}

}