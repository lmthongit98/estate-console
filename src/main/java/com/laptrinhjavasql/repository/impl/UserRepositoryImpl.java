package com.laptrinhjavasql.repository.impl;

import com.laptrinhjavasql.entity.UserEntity;
import com.laptrinhjavasql.repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl extends SimpleRepository<UserEntity> implements UserRepository {

    @Override
    public List<UserEntity> findUsersByBuildingId(Long buildingID) {
        StringBuilder sql = new StringBuilder()
                .append("SELECT u.id, u.email, u.username, u.fullname, u.status, u.password ")
                .append("FROM user u ")
                .append("INNER JOIN assignment_building ON u.id = assignment_building.staff_id ")
                .append("WHERE u.status != 0 AND assignment_building.building_id = " + buildingID);
        return super.findByCondition(sql.toString());
    }

}
