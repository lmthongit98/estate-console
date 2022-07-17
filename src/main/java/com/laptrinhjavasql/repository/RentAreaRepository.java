package com.laptrinhjavasql.repository;

import com.laptrinhjavasql.entity.RentAreaEntity;

import java.util.List;

public interface RentAreaRepository extends JpaRepository<RentAreaEntity> {
    List<RentAreaEntity> findRentAreaByBuildingId(Long buildingId);
}
