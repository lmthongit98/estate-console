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
						BuildingView.search();
						break;
					case 3:
						BuildingView.assignBuildingToStaffs(sc);
						break;
					case 0:
					default:
						flag = false;
						break;
					}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: " + e.getMessage());
			}
			if(functionID != 0) {
				System.out.print("Nhập phím bất kỳ để tiếp tục:");
				sc.nextLine();
			}
		} while (flag);
		sc.close();
	}

	public static void showMenu() {
		System.out.println("\n\n");
		System.out.println("================ ỨNG DỤNG QUẢN LÝ VĂN PHÒNG CHO THUÊ ================");
		System.out.println("1. Hiển thị danh sách tòa nhà.");
		System.out.println("2. Tìm kiếm tòa nhà.");
		System.out.println("3. Giao tòa nhà cho nhân viên quản lý.");
		System.out.println("0. Thoát.");
		System.out.println("Nhập lựa chọn của bạn [1-4]: ");
	}
}
