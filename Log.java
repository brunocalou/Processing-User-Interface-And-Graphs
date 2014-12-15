import processing.core.*;
import processing.event.*;

public class Log {

	Curve curve = new Curve();
	String token = ",";

	protected final PApplet parent;

	Log(PApplet parent){
		this.parent = parent;
	}

	public boolean load(String path){
		//Load the log file as a list of strings
		String lines[] = parent.loadStrings(path);
		if(lines == null){
			return false;
		}
		convertToCurve(lines);
		return true;
	}

	private void convertToCurve(String lines[]){
		//Converts a list of strings into a Curve
		for(int i = 0; i < lines.length; i++){
			String vertexes[] = parent.splitTokens(lines[i],token);
			
			float x = 0, y = 0, z = 0;
			
			if(vertexes.length > 0){
				x = Float.parseFloat(vertexes[0]);
			}
			
			if(vertexes.length > 1){
				y = Float.parseFloat(vertexes[1]);
			}
			
			if(vertexes.length > 2){
				z = Float.parseFloat(vertexes[2]);
			}

			Vertex vertex = new Vertex(x,y,z);
			curve.add(vertex);
		}
	}

	public Curve getCurve(){
		return curve;
	}
}