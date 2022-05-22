package com.laptrinhjavasql.converter;

import com.laptrinhjavasql.entity.UserEntity;
import com.laptrinhjavasql.model.UserModel;
import org.modelmapper.ModelMapper;

public class UserConverter {

    private ModelMapper modelMapper = new ModelMapper();

    public UserModel convertToUserModelFromEntity(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserModel.class);
    }

}
