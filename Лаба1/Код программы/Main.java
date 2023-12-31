package laba1n11;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {
	public int N = 0;
    public ArrayList<Double> xcoord = new ArrayList<Double>(N);
    public ArrayList<Double> ycoord = new ArrayList<Double>(N);
    public static void main(String[] args) {
    Main main = new Main();
    Scanner sc1 = new Scanner(System.in);
    while(true) {
    	System.out.println("Введите количство точек");
    	try {
    			main.N = sc1.nextInt();
    			break;
    		} catch(InputMismatchException e) {
    	System.out.println("Error");
    		}
    }
    System.out.println("Введите координаты точек");
    Scanner sc2 = new Scanner(System.in);
    for (int i = 0; i < main.N; i++){
        main.xcoord.add(sc2.nextDouble());
        main.ycoord.add(sc2.nextDouble());
    }
    Perimetr perim = new Perimetr();
    System.out.println(perim.SearchPer(main.xcoord, main.ycoord, main.N));
    sc1.close();
    sc2.close();
    }
}

