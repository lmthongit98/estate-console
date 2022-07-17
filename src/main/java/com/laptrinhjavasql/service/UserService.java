package com.laptrinhjavasql.service;

import java.util.List;

import com.laptrinhjavasql.model.UserModel;

public interface UserService {
	List<UserModel> findAll();
	List<UserModel> findUserByBuildingId(Long buildingID);
}
