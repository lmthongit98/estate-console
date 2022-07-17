package com.laptrinhjavasql.repository;

import com.laptrinhjavasql.entity.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity> {
    List<UserEntity> findUsersByBuildingId(Long buildingID);
}
