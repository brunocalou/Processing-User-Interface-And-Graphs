/*
  
  All the code on this file was created by Bruno Calou Alves and it's free
  to use under the MIT License

  Note: Add the MIT License here

*/


void logExample(){
  //Shows a graph loaded from a text file
  my_graph = new Graph(this,0,0,width,height);
  my_graph.setBackgroundColor(40,40,40,255);
  my_graph.setCurveStroke(0.5,255,0,0,255);
  my_graph.setTitle("Graph from Log");

  Log my_log = new Log(this);
  my_log.load("./log.txt");
  my_graph.setCurve(my_log.getCurve());

  increment_graph = false;
}

void graphExample(){
  //Shows a graph beeing updated over time
  my_graph = new Graph(this,0,0,width,height);
  my_graph.setBackgroundColor(40,40,40,255);
  my_graph.setCurveStroke(0.5,255,0,0,255);
  my_graph.setMaxNumberOfValues(1000);
}

void digitalGraphExample(){
  //Shows a graph beeing updated over time
  my_graph = new Graph(this,0,0,width,height);
  my_graph.setBackgroundColor(40,40,40,255);
  my_graph.setCurveStroke(0.5,255,0,0,255);
  my_graph.setMaxNumberOfValues(1000);
  my_graph.setDigital(true);
}

void layerExample(){
  //Shows the layer property
  Label my_label = new Label(this,60,80,100,60);
  Button my_button = new Button(this,40,40,50,50);
  Button my_button2 = new Button(this,60,60,50,50);
  Widget my_widget = new Widget(this,80,80,100,100);
  TextField my_txt_field = new TextField(this,100,100,100,100);
  LabelList my_lbl_list = new LabelList(this,120,120,120,120);
  my_lbl_list.add("A");
  my_lbl_list.add("B");
  my_lbl_list.add("C");
  my_lbl_list.add("D");
  my_lbl_list.setPosition(500,120);
  PlotSurface my_plot_surface = new PlotSurface(this,150,150,300,300);
  Button my_button3 = new Button(this,380,380,50,50);
  //Add a curve on the graph
  for(float i = 0; i < 10; i+=0.1){
    my_plot_surface.add(i,sin(i));
  }
}

void customizationExample(){
  //Shows how to customize the widgets
  Label my_label = new Label(this,60,80,150,60);
  my_label.setText("My awesome label");
  my_label.setTextColor(255,0,0,255);
  my_label.setTextSize(15);
  my_label.setBackgroundColor(255,255,0,255);
  my_label.setStroke(5,100,100,10,255);
  my_label.setSize(100,60);

  Button my_button = new Button(this,170,80,150,100);
  my_button.setText("My awesome button");
  my_button.setNormalTextColor(255,255,255,255);
  my_button.setOnPressTextColor(255,0,0,255);
  my_button.setOnHoverTextColor(255,255,255,255);
  my_button.setNormalColor(255,0,0,255);
  my_button.setOnPressColor(255,255,255,255);
  my_button.setOnHoverColor(255,0,0,255);
  my_button.setTextSize(20);
  my_button.setBackgroundColor(255,0,0,255);
  my_button.setStroke(3,100,100,200,255);

  TextField my_txt_field = new TextField(this,330,80,200,100);
  my_txt_field.setText("Click me and write something");

  Button my_free_button = new Button(this,60,150,100,60){
    @Override
    public void onDrag(int mouse_x, int mouse_y, int button) {
      this.x -= window.last_mouse_x - mouse_x;
      this.y -= window.last_mouse_y - mouse_y;
      setText("I believe I can fly...");
    }

    @Override
    public void onMouseLeave(int mouse_x, int mouse_y){
      super.onMouseLeave(mouse_x,mouse_x);
      setText("Move me !");
    }
  };

  my_free_button.setText("Move me !");
  my_free_button.setOnPressTextColor(255,0,0,255);
  my_free_button.setStroke(0,255,255,255,255);

  Button my_resizable_button = new Button(this,60,220,100,60){
    @Override
    public void onDrag(int mouse_x, int mouse_y, int button) {
      int new_w = this.w - (window.last_mouse_x - mouse_x);
      if(new_w < 400){
        setW(new_w);
      }
      int new_h = this.h - (window.last_mouse_y - mouse_y);
      if(new_h < 400){
        setH(new_h);
      }
      setText("You got it!");
      parent.println("w = " + getW() + " h = " + this.getH());
    }

    @Override
    public void onMouseLeave(int mouse_x, int mouse_y){
      super.onMouseLeave(mouse_x,mouse_x);
      setText("Resize me!");
    }
  };

  my_resizable_button.setText("Resize me!");
  my_resizable_button.setStroke(0,255,255,255,255);
}

/*
 Window variables
*/

int current_w = 0;
int current_h = 0;
int min_w = 100;
int min_h = 100;

/*
  This variable controls if the x axis will be
  measured by the program time. If not, it the counter
  variable will be used
*/

boolean use_time_as_x_axis = false;
boolean increment_graph = true;

float counter = 0;
float step = 0.01;

Graph my_graph = null;

void setup() {
  size(900,700);
  noSmooth();
  registerMethod("pre", this);
  frame.setResizable(true);
  current_h = height;
  current_w = width;

  /*
    Examples
    Uncomment the example you would like to see
  */

  logExample();
  // digitalGraphExample();
  // graphExample();
  // layerExample();
  // customizationExample();

}

void incrementGraph(){
  if(my_graph != null){
    if(use_time_as_x_axis){
      if(my_graph.isDigital()){
        int y = round(random(1));
        my_graph.add(millis()/500.0,y);
      } else {
        my_graph.add(millis()/1000.0,20*sin(counter));
        // my_graph.add(millis()/1000.0,20*cos(counter));
        // my_graph.add(millis()/1000.0,20*tan(counter));
        }
      
    } else {
      if(my_graph.isDigital()){
        int y = round(random(1));
        my_graph.add(counter*10,y);
      } else {
        my_graph.add(counter,20*sin(counter));
      // my_graph.add(counter,20*cos(counter));
      // my_graph.add(counter,20*tan(counter));
        }
    }
    counter+= step;  
  }
}
void pre(){
  //Configures the graph size based on the window size
  if(width < min_w) size(min_w,height);
  if(height < min_h) size(width,min_h);

  if(current_w != width || current_h != height){
    if(my_graph != null) my_graph.setSize(width,height);
    current_w = width;
    current_h = height;
  }
}

void draw() {
  background(200);

  if(increment_graph){
    incrementGraph();
  }
}