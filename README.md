This project was created by Bruno Calou Alves

How to Run

-To run the project, open the grafico.pde in the processing IDE and press run

-You can also edit the file run.sh and modify the line

	/home/bruno/processing-2.2.1/processing-java --output=/tmp/processing/ --force --run --sketch=$1

 specifying your processing-java location. After that, run the run_graph.sh on the terminal

Notes

-In the grafico.pde file, there are some examples. To run them, uncomment the desired
example on the setup function and make sure the other examples as commented

-There is a python script to generate a log file. If you want generate another function,
edit the "function(x)" and run the script

-You can resize the window and watch the graph beeing adjusted

-The graph supports dragging with the left mouse button and you can change it's scale
using the mouse wheel

-The graph's axis are not beeing updated to the real values as it should due to
complexity / time issues to finish the project at time