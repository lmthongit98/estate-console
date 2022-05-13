package com.laptrinhjavasql.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.laptrinhjavasql.controller.BuildingController;
import com.laptrinhjavasql.model.BuildingModel;

public class BuildingListView {
	
	static BuildingController controller = new BuildingController();

	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter building name: ");
//		String name = sc.nextLine();
//		System.out.println("Enter number of basement: ");
//		Integer numberOfBasement = Integer.parseInt(sc.nextLine());
//		System.out.println("Enter floor area: ");
//		Integer floorArea = Integer.parseInt(sc.nextLine());
//		System.out.println("Enter your building type. e.g: tang-tret, nguyen-can, noi-that: ");
//		String typeStr = sc.nextLine();
//		String[] types =  typeStr.split(",");
//		List<String> typeList = new ArrayList<>();
//		Collections.addAll(typeList, types);
		
		List<BuildingModel> buildings = controller.findAll();
		
//		buildings = controller.searchBuilding(name, numberOfBasement, floorArea, typeList);
		Long id = (long) 1;
	    BuildingModel building = controller.findById(id);
	    System.out.println(building);
		
//		buildings.forEach(building -> {
//			System.out.println(building);
//			System.out.println("-----------------------------------------------------");
//		});
		
	}
}
