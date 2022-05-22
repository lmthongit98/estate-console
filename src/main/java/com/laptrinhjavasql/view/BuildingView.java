package com.laptrinhjavasql.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.laptrinhjavasql.builder.BuildingSearchBuilder;
import com.laptrinhjavasql.controller.BuildingController;
import com.laptrinhjavasql.controller.UserController;
import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.model.BuildingModel;
import com.laptrinhjavasql.model.UserModel;

public class BuildingView {

	private static BuildingController controller = new BuildingController();
	private static UserController userController = new UserController();
	
	public static void add(Scanner sc) {
		BuildingEntity buildingEntity = createBuildingEntity(sc);
		Long id = controller.add(buildingEntity);
		if(id != null) {
			System.out.println("Added building with id = " + id);
		}
	}

	public static void findAll() {
		List<BuildingModel> buildings = controller.findAll();
		showBuildingList(buildings);
	}

	public static void deleteById(Scanner sc) {
		System.out.println("Enter the building ID: ");
		Long id = Long.parseLong(sc.nextLine());
		controller.delete(id);
	}

	public static void search(Scanner sc) {
		System.out.println("(*)Enter the search values. Press enter to skip search field.");
		System.out.println("-------------------------------------------------------------");

		System.out.println("Tên tòa nhà: ");
		String name = validateAndGetValue(sc.nextLine(), String.class);

		System.out.println("Tên đường: ");
		String street = validateAndGetValue(sc.nextLine(), String.class);

		System.out.println("Mã nhân viên quản lý tòa nhà: ");
		Long staffId = validateAndGetValue(sc.nextLine(), Long.class);

		System.out.println("Số tầng hầm: ");
		Integer numberOfBasement = validateAndGetValue(sc.nextLine(), Integer.class);

		System.out.println("Quản lý: ");
		String manager = validateAndGetValue(sc.nextLine(), String.class);

		System.out.println("Diện tích sàn: ");
		Integer floorArea = validateAndGetValue(sc.nextLine(), Integer.class);

		System.out.println("Loại tòa nhà (ví dụ: tang-tret, nguyen-can): ");
		String type = validateAndGetValue(sc.nextLine(), String.class);

		List<String> types  = null;
		if(type != null && !type.equals("")) {
			types = Arrays.asList(type.split(","));
			types = types.stream().map(t -> t.trim()).collect(Collectors.toList());
		}

		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
				.setName(name)
				.setStreet(street)
				.setStaffId(staffId)
				.setNumberOfBasement(numberOfBasement)
				.setBuildingArea(floorArea)
				.setBuildingTypes(types)
				.setManagerName(manager)
				.build();

		List<BuildingModel> results = controller.findByCondition(builder);
		showBuildingList(results);
	}

	private static BuildingEntity createBuildingEntity(Scanner sc) {
		BuildingEntity buildingEntity = new BuildingEntity();
		System.out.println("Tên tòa nhà: ");
		String name = validateAndGetValue(sc.nextLine(), String.class);

		System.out.println("Số tầng hầm: ");
		Integer numberOfBasement = validateAndGetValue(sc.nextLine(), Integer.class);

		System.out.println("Nhập diện tích sàn: ");
		Integer floorArea = validateAndGetValue(sc.nextLine(), Integer.class);

		System.out.println("Nhập tên đường: ");
		String street = validateAndGetValue(sc.nextLine(), String.class);

		buildingEntity.setName(name);
		buildingEntity.setFloorArea(floorArea);
		buildingEntity.setNumberOfBasement(numberOfBasement);
		buildingEntity.setStreet(street);
		return buildingEntity;
	}

	private static <T>  T validateAndGetValue(String value, Class<T> tClass) {
		if (value == null || value.equals("")) {
			return null;
		}
		if (tClass.getName().equals("java.lang.Long")) {
			return (T) Long.valueOf(value);
		}
		if (tClass.getName().equals("java.lang.Integer")) {
			return (T) Integer.valueOf(value);
		}
		return (T) value;
	}

	private static String displayValue(Object value) {
		return  value != null ? value + "" : "null";
	}

	public static void showBuildingList(List<BuildingModel> buildingModels){
		System.out.format("+----------------------------------------------------------------RESULTS------------------------------------------------------------------------+%n");
		String leftAlignFormat = "| %-5s | %-20s | %-35s | %-15s | %-18s | %-1s %-10s | %-1s %-12s |%n";
		System.out.format("+-------+----------------------+-------------------------------------+-----------------+--------------------+----------------+------------------+%n");
		System.out.format("| Mã    |     Tên tòa nhà      |               Địa chỉ               |    Quản lý      |     Số tầng hầm    | Diện tích sàn  |     Giá thuê     |%n");
		System.out.format("+-------+----------------------+-------------------------------------+-----------------+--------------------+----------------+------------------+%n");
		for (int i = 0; i < buildingModels.size(); i++) {

			System.out.format(leftAlignFormat,
					displayValue(buildingModels.get(i).getId()),
					displayValue(buildingModels.get(i).getName()),
					displayValue(buildingModels.get(i).getAddress()),
					displayValue(buildingModels.get(i).getManagerName()),
					displayValue(buildingModels.get(i).getNumberOfBasement()),
					displayValue(buildingModels.get(i).getFloorArea()), "$",
					displayValue(buildingModels.get(i).getRentPrice()), "$"
			);
		}
		System.out.format("+-------+----------------------+-------------------------------------+-----------------+--------------------+----------------+------------------+%n");
	}


	public static void assignBuildingToStaffs(Scanner sc) {
		System.out.println("Nhập ID của tòa nhà: ");
		Long buildingID = Long.parseLong(sc.nextLine());

		List<UserModel> assignees = userController.findUsersByBuildingId(buildingID);
		System.out.println("Danh sách nhân hiện tại đang quản lý tòa nhà: ");
		assignees.forEach(System.out::println);

		System.out.println("Cập nhật lại ID nhân viên quản lý: ");
		String assigneesIdStr = sc.nextLine();
		List<Long> updatedAssigneeIds = Arrays.stream(assigneesIdStr.split(","))
				.map(String::trim)
				.map(Long::parseLong)
				.collect(Collectors.toList());
		List<Long> oldAssigneeIds = assignees.stream().map(UserModel::getId).collect(Collectors.toList());

		controller.assignBuildingToStaffs(updatedAssigneeIds, oldAssigneeIds, buildingID);

		List<UserModel> updatedAssignees = userController.findUsersByBuildingId(buildingID);
		System.out.println("Danh sách nhân nhiên mới cập nhật: ");
		updatedAssignees.forEach(System.out::println);
	}
}
