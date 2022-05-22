package com.laptrinhjavasql.repository;

import com.laptrinhjavasql.entity.AssignmentBuildingEntity;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity> {
    Long findAssignedId(Long id, Long buildingId);
}
