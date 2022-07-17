package com.laptrinhjavasql.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavasql.converter.UserConverter;
import com.laptrinhjavasql.entity.UserEntity;
import com.laptrinhjavasql.model.UserModel;
import com.laptrinhjavasql.repository.UserRepository;
import com.laptrinhjavasql.repository.impl.UserRepositoryImpl;
import com.laptrinhjavasql.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private UserConverter userConverter;
	
	public UserServiceImpl() {
		this.userRepository = new UserRepositoryImpl();
		this.userConverter = new UserConverter();
	}

	@Override
	public List<UserModel> findAll() {
		List<UserEntity> userEntities =  this.userRepository.findAll();
		return convertToModelsFromEntities(userEntities);
	}

	@Override
	public List<UserModel> findUserByBuildingId(Long buildingID) {
		List<UserEntity> userEntities = userRepository.findUsersByBuildingId(buildingID);
		return convertToModelsFromEntities(userEntities);
	}

	private List<UserModel> convertToModelsFromEntities(List<UserEntity> userEntities) {
		if(userEntities == null) {
			return null;
		}
		List<UserModel> userModels = new ArrayList<>();
		userEntities.forEach(userEntity -> {
			UserModel userModel = userConverter.convertToUserModelFromEntity(userEntity);
			userModels.add(userModel);
		});
		return userModels;
	}

}
