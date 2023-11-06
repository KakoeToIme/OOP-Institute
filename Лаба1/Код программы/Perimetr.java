package laba1n11;

import java.util.ArrayList;

public class Perimetr {
	double Per = 0;
	 public double SearchPer(ArrayList<Double> xcoord, ArrayList<Double> ycoord,  int N){
	        for (int i = 0; i < N; i++){
	            for (int j = 0; j < N; j++){
	                for (int k = 0; k < N; k++){
	                    double d = Math.sqrt(Math.pow(xcoord.get(i) - xcoord.get(j), 2) + Math.pow(ycoord.get(i) - ycoord.get(j), 2)) + Math.sqrt(Math.pow(xcoord.get(i) - xcoord.get(k), 2) + Math.pow(ycoord.get(i) - ycoord.get(k), 2)) + Math.sqrt(Math.pow(xcoord.get(j) - xcoord.get(k), 2) + Math.pow(ycoord.get(j) - ycoord.get(k), 2));
	                    if (Per < d) Per = d;
	                }
	            }
	        }
	        return Per;
	    }
}
