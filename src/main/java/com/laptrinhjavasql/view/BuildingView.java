package com.laptrinhjavasql.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.laptrinhjavasql.controller.BuildingController;
import com.laptrinhjavasql.controller.UserController;
import com.laptrinhjavasql.model.BuildingModel;
import com.laptrinhjavasql.model.BuildingSearchInput;
import com.laptrinhjavasql.model.UserModel;

public class BuildingView {

	private static final BuildingController controller = new BuildingController();
	private static final UserController userController = new UserController();

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
		BuildingSearchInput buildingSearchInput = initSearchParams();

		List<BuildingModel> results = controller.findByCondition(buildingSearchInput);
		showBuildingList(results);
	}

	public static BuildingSearchInput initSearchParams() {
		BuildingSearchInput buildingSearchInput = new BuildingSearchInput();

		buildingSearchInput.setName("tower");
		buildingSearchInput.setStreet(null);
		buildingSearchInput.setNumberOfBasement(null);
		// etc
		//buildingSearchInput.setBuildingTypes(Arrays.asList("type1", "type2"));

		return buildingSearchInput;
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
		String leftAlignFormat = "| %-5s | %-20s | %-35s | %-15s | %-18s | %-14s | %-16s |%n";
		System.out.format("+-------+----------------------+-------------------------------------+-----------------+--------------------+----------------+------------------+%n");
		System.out.format("| Mã    |     Tên tòa nhà      |               Địa chỉ               |    Quản lý      |     Số tầng hầm    | Diện tích sàn  |     Giá thuê     |%n");
		System.out.format("+-------+----------------------+-------------------------------------+-----------------+--------------------+----------------+------------------+%n");
		for (BuildingModel buildingModel : buildingModels) {

			System.out.format(leftAlignFormat,
					displayValue(buildingModel.getId()),
					displayValue(buildingModel.getName()),
					displayValue(buildingModel.getAddress()),
					displayValue(buildingModel.getManagerName()),
					displayValue(buildingModel.getNumberOfBasement()),
					displayValue(buildingModel.getFloorArea() + "$"),
					displayValue(buildingModel.getRentPrice() + "$")
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

		System.out.println("Cập nhật lại ID nhân viên quản lý (vd: 1, 2, 3): ");
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
