package com.laptrinhjavasql.controller;

import com.laptrinhjavasql.model.UserModel;
import com.laptrinhjavasql.service.UserService;
import com.laptrinhjavasql.service.impl.UserServiceImpl;

import java.util.List;

public class UserController {

    private UserService userService = new UserServiceImpl();

    public List<UserModel> findUsersByBuildingId(Long buildingId) {
        return userService.findUserByBuildingId(buildingId);
    }
}
