package laba2n11;

import java.util.regex.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String Input;
		String regex1 = "(\\d++).(\\d{2}) (USD|RUB|EU)";
		Pattern p1 = Pattern.compile(regex1);
		Scanner sc1 = new Scanner(System.in);
		while(true) {
			try {
				System.out.println("Введите текст");
				Input = sc1.nextLine();
				break;
			} catch(InputMismatchException e) {
				System.out.println("Error");
			}
		}
		Matcher m1 = p1.matcher(Input);
		while (m1.find()) {
			System.out.println("Price: " + m1.group());
		}
		sc1.close();
	}

}
