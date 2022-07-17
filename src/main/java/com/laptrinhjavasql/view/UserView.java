package com.laptrinhjavasql.view;

import com.laptrinhjavasql.controller.UserController;
import com.laptrinhjavasql.model.UserModel;

import java.util.List;
import java.util.Scanner;

public class UserView {

    private static UserController userController = new UserController();

    public static void showStaffsAssignedToBuilding(Scanner sc) {
        System.out.println("Nhập ID của toàn nhà: ");
        Long buildingId = Long.parseLong(sc.nextLine());
        List<UserModel> staffs = userController.findUsersByBuildingId(buildingId);
        if(staffs != null){
            staffs.forEach(System.out::println);
        }
    }

}
