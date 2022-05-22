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
						BuildingView.findAll();
						break;
					case 2:
						BuildingView.search(sc);
						break;
					case 3:
						BuildingView.add(sc);
						break;
					case 4:
						BuildingView.deleteById(sc);
						break;
					case 5:
						UserView.showStaffsAssignedToBuilding(sc);
					case 6:
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
		System.out.println("================ BUILDING MANAGER ================");
		System.out.println("1. Hiển thị danh sách tòa nhà.");
		System.out.println("2. Tìm kiếm tòa nhà.");
		System.out.println("3. Thêm tòa nhà. ");
		System.out.println("4. Xóa tòa nhà.");
		System.out.println("5. Hiển thị nhân viên quản lý của tòa nhà.");

		System.out.println("Nhập lựa chọn của bạn [1-4]: ");
	}
}
