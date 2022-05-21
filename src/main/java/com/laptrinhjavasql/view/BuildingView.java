package com.laptrinhjavasql.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.laptrinhjavasql.controller.BuildingController;
import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.model.BuildingModel;

public class BuildingView {

	private static BuildingController controller = new BuildingController();
	
	public static void add(Scanner sc) {
		BuildingEntity buildingEntity = createBuildingEntity(sc);
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

	public static void updateById(Scanner sc) {
		System.out.println("Enter the building ID: ");
		Long id = Long.parseLong(sc.nextLine());
		BuildingEntity buildingEntity = createBuildingEntity(sc);
		buildingEntity.setId(id);
		controller.update(buildingEntity);
	}

	public static void search(Scanner sc) {
		System.out.println("Enter name: ");
		String name = sc.nextLine();
		System.out.println("Enter number of basement: ");
		Integer numberOfBasement = Integer.parseInt(sc.nextLine());
		System.out.println("Enter floorArea: ");
		Integer floorArea = Integer.parseInt(sc.nextLine());
		System.out.println("Enter type code: ");
		String type = sc.nextLine();

		List<String> types = Arrays.asList(type.split(","));

		List<BuildingModel> results = controller.findByCondition(name, numberOfBasement, floorArea, types);
		for (BuildingModel item: results) {
			System.out.println(item);
		}
	}

	private static BuildingEntity createBuildingEntity(Scanner sc) {
		BuildingEntity buildingEntity = new BuildingEntity();

		System.out.println("Enter name: ");
		String name = sc.nextLine();
		System.out.println("Enter number of basement: ");
		Integer numberOfBasement = Integer.parseInt(sc.nextLine());
		System.out.println("Enter floorArea: ");
		Integer floorArea = Integer.parseInt(sc.nextLine());
		System.out.println("Enter street: ");
		String street = sc.nextLine();
		System.out.println("Enter type code: ");
		String type = sc.nextLine();

		buildingEntity.setName(name);
		buildingEntity.setFloorArea(floorArea);
		buildingEntity.setNumberOfBasement(numberOfBasement);
		buildingEntity.setStreet(street);
		buildingEntity.setTypes(type);
		return buildingEntity;
	}

}
