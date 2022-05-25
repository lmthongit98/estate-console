package com.laptrinhjavasql.repository.impl;

import com.laptrinhjavasql.entity.RentAreaEntity;
import com.laptrinhjavasql.repository.RentAreaRepository;

import java.util.List;

public class RentAreaRepositoryImpl extends SimpleRepository<RentAreaEntity> implements RentAreaRepository {
    @Override
    public List<RentAreaEntity> findRentAreaByBuildingId(Long buildingId) {
        String sql = "SELECT * FROM rentarea WHERE building_id = " + buildingId;
        return super.findByCondition(sql);
    }
}
