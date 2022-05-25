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

	public static void search() {
		BuildingSearchInput buildingSearchInput = initSearchParams();

		List<BuildingModel> results = controller.findByCondition(buildingSearchInput);
		showBuildingList(results);
	}

	public static BuildingSearchInput initSearchParams() {

		BuildingSearchInput buildingSearchInput = new BuildingSearchInput();
		buildingSearchInput.setName("TMA");
		buildingSearchInput.setStreet("lê văn sỹ");
		buildingSearchInput.setStaffId(3L);
		buildingSearchInput.setNumberOfBasement(3);
		buildingSearchInput.setManagerName("Sơn");
		buildingSearchInput.setFloorArea(300);
		buildingSearchInput.setAreaRentFrom(200);
		buildingSearchInput.setAreaRentTo(500);
		buildingSearchInput.setCostRentFrom(100);
		buildingSearchInput.setCostRentTo(300);
		buildingSearchInput.setBuildingTypes(Arrays.asList("tang-tret", "nguyen-can"));

		return buildingSearchInput;
	}

	private static String displayValue(Object value) {
		return  value != null ? value + "" : "null";
	}

	public static void showBuildingList(List<BuildingModel> buildingModels){
		if(buildingModels.isEmpty()) {
			System.out.println("Không tìm thấy tòa nhà nào!");
			return;
		}
		System.out.println("Kết quả: ");
		String leftAlignFormat = "| %-5s | %-20s | %-35s | %-15s | %-19s | %-18s | %-14s | %-16s |%n";
		System.out.format("+-------+----------------------+-------------------------------------+-----------------+---------------------+--------------------+----------------+------------------+%n");
		System.out.format("| Mã    |     Tên tòa nhà      |               Địa chỉ               |    Quản lý      |   Diện tích thuê    |    Số tầng hầm     | Diện tích sàn  |     Giá thuê     |%n");
		System.out.format("+-------+----------------------+-------------------------------------+-----------------+---------------------+--------------------+----------------+------------------+%n");
		for (BuildingModel buildingModel : buildingModels) {

			System.out.format(leftAlignFormat,
					displayValue(buildingModel.getId()),
					displayValue(buildingModel.getName()),
					displayValue(buildingModel.getAddress()),
					displayValue(buildingModel.getManagerName()),
					displayValue(buildingModel.getRentArea()),
					displayValue(buildingModel.getNumberOfBasement()),
					displayValue(buildingModel.getFloorArea() + "$"),
					displayValue(buildingModel.getRentPrice() + "$")
			);
		}
		System.out.format("+-------+----------------------+-------------------------------------+-----------------+---------------------+--------------------+----------------+------------------+%n");
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
