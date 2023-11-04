package laba1n11;

public class Perimetr {
	double Per = 0;
	 public double SearchPer(double[] coord, int N){
	        for (int i = 0; i < 2*N; i+=2){
	            for (int j = 0; j < 2*N; j+=2){
	                for (int k = 0; k < 2*N; k+=2){
	                    double d = Math.sqrt(Math.pow(coord[i] - coord[j], 2) + Math.pow(coord[i+1] - coord[j+1], 2)) + Math.sqrt(Math.pow(coord[i] - coord[k], 2) + Math.pow(coord[i+1] - coord[k+1], 2)) + Math.sqrt(Math.pow(coord[j] - coord[k], 2) + Math.pow(coord[j+1] - coord[k+1], 2));
	                    if (Per < d) Per = d;
	                }
	            }
	        }
	        return Per;
	    }
}
