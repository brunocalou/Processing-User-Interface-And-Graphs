import processing.core.*;
import processing.event.*;

/*

	This class is a plot surface with x and y scales, legends and a title. Due to
	complexity and time problems, the scales doesn't show the correct values, but
	they behave correctly when the graph is resized

*/
public class Graph extends PlotSurface{
	int text_size;
	int title_size;
	LabelList x_axis;
	LabelList y_axis;
	TextField title;
	TextField x_legend;
	TextField y_legend;

	//Refers to the y_axis
	int number_of_digits = 4;

	Graph (PApplet parent, int x, int y, int w, int h){
		super(parent,x,y,w,h);
		text_size = 16;
		title_size = 20;

		x_axis = new LabelList(parent,x,y,200,200);
		x_axis.setVertical();
		x_axis.setStep(grid.getXStep());
		x_axis.setTextSize(text_size);

		y_axis = new LabelList(parent,x,y,100,100);
		y_axis.setHorizontal();
		y_axis.setStep(grid.getYStep());
		y_axis.setTextSize(text_size);
		y_axis.setReverse(true);

		//Fill the axis
		for(int i = 0; i <= 2*number_of_intervals; i++){
			x_axis.add(""+i);
			y_axis.add("" + (double)parent.round((i-number_of_intervals)*100)/parent.pow(10,number_of_digits));
		}

		title = new TextField(parent,x,y,100,100);
		title.setTextSize(title_size);
		title.getBackgroundColor().setAlpha(0);
		title.getStrokeColor().setAlpha(0);
		title.setText("Wow! Much curves! Such graph!");
		title.setTextAlign(parent.CENTER,parent.CENTER);

		x_legend = new TextField(parent,x,y,100,100);
		x_legend.setTextSize(text_size);
		x_legend.getBackgroundColor().setAlpha(0);
		x_legend.getStrokeColor().setAlpha(0);
		x_legend.setText("x axis");
		x_legend.setTextAlign(parent.CENTER,parent.CENTER);

		y_legend = new TextField(parent,x,y,100,100);
		y_legend.setTextSize(text_size);
		y_legend.getBackgroundColor().setAlpha(0);
		y_legend.getStrokeColor().setAlpha(0);
		y_legend.setText("y\na\nx\ni\ns");
		y_legend.setTextAlign(parent.CENTER,parent.CENTER);

		setPosition(x,y);
		setSize(w,h);
	}

	@Override
	public void setPosition(int x, int y){
		super.setPosition(x+(int)y_legend.getRealTextWidth()*2+(int)y_axis.getBiggestTextHeight()*number_of_digits,
			y+(int)title.getRealTextHeight()*2);
		adjustLabels();
	}

	@Override
	public void setSize(int w, int h){
		if(w > 0 && h > 0){
			super.setSize(w-(int)y_legend.getRealTextWidth()*4-(int)y_axis.getBiggestTextWidth(),
			h-2*(int)x_legend.getRealTextHeight()-(int)x_axis.getBiggestTextHeight()-(int)title.getRealTextHeight()*2);
		
			adjustLabels();	
		}
	}

	@Override
	public void hide(){
		super.hide();
		x_axis.hide();
		y_axis.hide();
		x_legend.hide();
		y_legend.hide();
		title.hide();
	}

	@Override
	public void show(){
		super.show();
		x_axis.show();
		y_axis.show();
		x_legend.show();
		y_legend.show();
		title.show();
	}

	public void setTitle(String text){
		title.setText(text);
	}

	public void setXLegend(String text){
		x_legend.setText(text);
	}

	public void setYLegend(String text){
		y_legend.setText(text);
	}

	private void adjustLabels(){
		int cells_difference = grid.getNumberOfXCells() - x_axis.size() + 1;
		if(cells_difference > 0){
			for(int i = 0; i < cells_difference; i++){
				x_axis.add("" + i);
			}
		} else if(cells_difference < 0) {
			for(int i = 0; i < -cells_difference; i++){
				x_axis.remove(x_axis.size()-1);
			}
		}

		//Axis
		x_axis.setPosition(x,y+h);
		x_axis.setStep(grid.getXStep());
		y_axis.setPosition(x-(int)y_axis.getBiggestTextWidth(),y);
		y_axis.setStep(grid.getYStep());

		//Title
		title.setPosition(x,y-2*(int)title.getRealTextHeight());
		title.setSize(w,2*(int)title.getRealTextHeight());

		//Legends

		x_legend.setPosition(x,y+h+(int)x_axis.getBiggestTextHeight());
		x_legend.setSize(w,(int)x_legend.getRealTextHeight()*2);

		y_legend.setPosition(x-(int)y_axis.getBiggestTextWidth()-(int)y_legend.getRealTextWidth()*2,y);
		y_legend.setSize((int)y_legend.getRealTextWidth()*2,h);

			
	}
}