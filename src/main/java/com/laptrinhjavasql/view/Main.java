package com.laptrinhjavasql.view;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int functionID = 0;
		boolean flag = true;
		do {
			showMenu();
			try {
				functionID = Integer.parseInt(sc.nextLine());
				switch (functionID) {
				case 1:
					BuildingView.add(sc);
					break;
				case 2:
					BuildingView.findAll();
					break;
				case 3:
					BuildingView.findById(sc);
					break;
				case 4:
					break;

				default:
					flag = false;
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: " + e.getMessage());
			}
		} while (flag);
		sc.close();
	}

	public static void showMenu() {
		System.out.println("================ BOOK MANAGER ================");
		System.out.println("1. Add building.");
		System.out.println("2. List buildings.");
		System.out.println("3. Find building by ID.");
		System.out.println("4. Delete building. ");
		System.out.println("5. Update building by ID.");
		System.out.println("6. Search building.");
		System.out.println("7. Search building.");
		System.out.println("Your choice [1-7]: ");
	}
}
