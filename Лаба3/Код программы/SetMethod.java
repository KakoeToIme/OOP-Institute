package laba3n11;

import java.util.HashSet;
import java.util.Scanner;
import java.util.InputMismatchException;

public class SetMethod{

	public static void main(String[] args) {
		int n1 = 0;
		int n2 = 0;
		int n3 = 0;
		Scanner sc =new Scanner(System.in);
		try {
			System.out.println("Введите количество элементов первого множества: ");
			n1 = sc.nextInt();
			System.out.println("Введите количество элементов второго множества: ");
			n2 = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Ошибка ввода");
		}
		if (n1 > n2) n3 = n1;
		else n3 = n2;
		HashSet<Integer> set1 = new HashSet<>(n1);
		HashSet<Integer> set2 = new HashSet<>(n2);
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Введите элементы первого множества");
		for (int i = 0; i < n1; i++) {
			set1.add(sc1.nextInt());
		}
		System.out.println("Введите элементы второго множества");
		for (int i = 0; i < n2; i++) {
			set2.add(sc1.nextInt());
		}
		IntersectionAndUnionOfSets ints = new IntersectionAndUnionOfSets();
		System.out.println("Пересечение множеств: " + ints.Intersection(set1, set2, n3));
		System.out.println("Обьединение множеств: " + ints.Union(set1, set2, n3));
		sc.close();
		sc1.close();
	}

}
