package com.laptrinhjavasql.view;

import java.util.List;
import java.util.Scanner;

import com.laptrinhjavasql.controller.BuildingController;
import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.model.BuildingModel;

public class BuildingView {

	private static BuildingController controller = new BuildingController();
	
	public static void add(Scanner sc) {
		BuildingEntity buildingEntity = new BuildingEntity();
		
		System.out.println("Enter name: ");
		String name = sc.nextLine();
		System.out.println("Enter floorArea: ");
		Integer floorArea = Integer.parseInt(sc.nextLine());
		System.out.println("Enter number of basement: ");
		Integer numberOfBasement = Integer.parseInt(sc.nextLine());
		System.out.println("Enter type code: ");
		String type = sc.nextLine();
		
		buildingEntity.setName(name);
		buildingEntity.setFloorArea(floorArea);
		buildingEntity.setNumberOfBasement(numberOfBasement);
		buildingEntity.setTypes(type);
		
		Long id = controller.add(buildingEntity);
		
		if(id != null) {
			System.out.println("Added building with id = " + id);
		}
		
	}

	public static void findById(Scanner sc) {
		
		System.out.println("Enter the building ID: ");
		Long id = Long.parseLong(sc.nextLine());
		BuildingController controller = new BuildingController();

		BuildingModel building = controller.findById(id);

		System.out.println(building);

	}

	public static void findAll() {
		
		List<BuildingModel> buildings = controller.findAll();
		
		buildings.forEach(building -> {
			System.out.println(building);
		});

	}

	public static void deleteById(Scanner sc) {
		System.out.println("Enter the building ID: ");
		Long id = Long.parseLong(sc.nextLine());
		controller.delete(id);
	}
}
