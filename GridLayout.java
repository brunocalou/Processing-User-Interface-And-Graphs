/*

	THIS CLASS IS NOT BEEING USED. JUST IGNORE IT

*/


// import processing.core.*;
// import processing.event.*;
// import java.util.ArrayList;

// public class GridLayout extends Widget{
// 	private ArrayList<ArrayList<Widget> > grid = new ArrayList<ArrayList<Widget>>();

// 	GridLayout(PApplet parent, int x, int y, int w, int h){
// 		super(parent,x,y,w,h);
// 		// setBackgroundColor(0,0,0,0);
// 		// setForegroundColor(0,0,0,0);
// 	}

// 	boolean add(Widget widget, int row, int column){
// 		if(row < 0 || column < 0) return false;
// 		if(grid.size() == 0){
// 			grid.add(new ArrayList<Widget>());
// 			grid.get(0).add(new Widget(parent,0,0,0,0));
// 		}

// 		widget.setPosition(0,0);
				
// 		//Fill current rows
// 		for(int i = 0; i < grid.size(); i++){//Rows
// 			for(int j = grid.get(i).size(); j < column + 1; j++){//Columns
// 				grid.get(i).add(new Widget(parent,0,0,0,0));
// 			}
// 		}

// 		//Add new rows
// 		for(int i = grid.size(); i < row + 1; i++){//Rows
// 			grid.add(new ArrayList<Widget>());
// 			for(int j = 0; j < column + 1; j++){
// 				grid.get(i).add(new Widget(parent,0,0,0,0));
// 			}
// 		}

// 		//Add widget to the right position
// 		grid.get(row).get(column).delete();	//Delete widget on this position
// 		grid.get(row).set(column, widget);	//Add required widget

// 		// parent.println("number of rows = " + grid.size() + " number of cols = " + grid.get(0).size());
// 		adjustGrid();
// 		return true;
// 	}

// 	boolean adjustGrid(){
// 		if(grid.isEmpty() || grid.size() < 2) return false;
// 		if(grid.get(0).size() < 2) return false;

		
// 		//================================================================
// 		//PEGAR A MAIOR ALTURA PRA LINHA E APLICAR SOBRE OS ELEMENTOS DELA
// 		//FAZER O MESMO COM A LARGURA PARA AS COLUNAS
// 		//================================================================

// 		int biggest_height = 0;
// 		int biggest_width = 0;

// 		//Adjust column by column

// 		for(int i = 0; i < grid.size(); i++){//Rows

// 			//Get biggest height
// 			for(int j = 0; j < grid.get(i).size(); j++){//Columns
// 				Widget widget = grid.get(i).get(j);
// 				if(widget.getH() > biggest_height){
// 					biggest_height = widget.getH();
// 				}
// 			}

// 			//Apply the height and adjust the position
// 			for(int j = 1; j < grid.get(i).size(); j++){//Columns
// 				Widget widget = grid.get(i).get(j-1);
// 				Widget next_widget = grid.get(i).get(j);
// 				next_widget.setPosition(widget.getX() + widget.getW()*j, next_widget.getY());// + widget.getH()*i);
// 				next_widget.setSize(next_widget.getW(), biggest_height);
// 			}

// 			biggest_height = 0;
// 		}

// 		parent.println("grid.get(0).size() = " + grid.get(0).size());
// 		parent.println("grid.size() = " + grid.size());
// 		int counter = 0;
// 		// //Adjust row by row
// 		// for(int j = 0; j < grid.get(counter).size(); j++){//Columns

// 		// 	for(int i = 0; i < grid.size(); i++){//Rows
// 		// 		parent.println("j = " + j + " i = " + i);
// 		// 		Widget widget = grid.get(i).get(j);
// 		// 		if(widget.getW() > biggest_width){
// 		// 			biggest_width = widget.getW();
// 		// 		}
// 		// 	}
			
// 		// 	counter ++;
			
// 		// 	if(counter >= grid.size()){
// 		// 		break;
// 		// 	}			

// 		// 	//Apply the width and adjust the position
// 		// 	for(int i = 1; i < grid.size(); i++){
// 		// 		Widget widget = grid.get(i-1).get(j);
// 		// 		Widget next_widget = grid.get(i).get(j);
// 		// 		next_widget.setPosition(next_widget.getX(), widget.getY() + widget.getH()*i);// + widget.getH()*i);
				
// 		// 		if(widget.getW() > biggest_width){
// 		// 			next_widget.setSize(biggest_width, next_widget.getH());
// 		// 			//Adjust position of the next widgets on the columns
// 		// 			for(int k = j + 1; k < grid.get(i).size(); k++){
// 		// 				Widget next = grid.get(i).get(k);
// 		// 				next.setPosition(next_widget.getX() + next_widget.getW(),next.getY());
// 		// 			}
// 		// 		}
// 		// 	}
// 		// 	biggest_width = 0;
// 		// }

// 		// for(int i = 1; i < grid.size(); i++){//Rows
// 		// 	for(int j = 0; j < grid.get(i).size(); j++){//Columns
// 		// 		Widget widget = grid.get(i-1).get(j);
// 		// 		Widget next_widget = grid.get(i).get(j);
// 		// 		next_widget.setPosition(next_widget.getX(), widget.getY() + widget.getH()*i);// + widget.getH()*i);
// 		// 	}
// 		// }
// 		return true;
// 	}

// 	boolean getWidget(Widget widget, int row, int column){
// 		if(grid.isEmpty()) return false;
// 		if(row < 0 || row > grid.size()) return false;
// 		if(column < 0 || column > grid.get(0).size()) return false;
// 		widget.delete();
// 		widget = grid.get(row).get(column);
// 		return true;
// 	}
// }