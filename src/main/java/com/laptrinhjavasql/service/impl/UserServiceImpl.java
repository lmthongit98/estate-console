package com.laptrinhjavasql.service.impl;

import java.util.List;

import com.laptrinhjavasql.model.UserModel;
import com.laptrinhjavasql.repository.UserRepository;
import com.laptrinhjavasql.repository.impl.UserRepositoryImpl;
import com.laptrinhjavasql.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	public UserServiceImpl() {
		this.userRepository = new UserRepositoryImpl();
	}

	@Override
	public List<UserModel> findAll() {
		return this.userRepository.findAll();
	}

}
