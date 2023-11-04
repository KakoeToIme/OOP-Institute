package laba1n11;

import java.util.Scanner;
import java.util.InputMismatchException;
public class Main {
	public int N = 0;
    public double[] coord = new double[2*N];
    public static void main(String[] args) {
    Main main = new Main();
    Scanner sc1 = new Scanner(System.in);
    while(true) {
    System.out.println("Введите количство точек");
    try {
    main.N = sc1.nextInt();
	main.coord = new double[2*main.N];
    break;
    } catch(InputMismatchException e) {
    	System.out.println("Error");
    }
    }
    System.out.println("Введите координаты точек");
    Scanner sc2 = new Scanner(System.in);
    for (int i = 0; i < 2* main.N; i++){
        main.coord[i] = sc2.nextDouble();
    }
    Perimetr perim = new Perimetr();
    System.out.println(perim.SearchPer(main.coord, main.N));
    sc1.close();
    sc2.close();
    }
}
