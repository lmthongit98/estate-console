package com.laptrinhjavasql.repository.impl;

import com.laptrinhjavasql.entity.AssignmentBuildingEntity;
import com.laptrinhjavasql.repository.AssignmentBuildingRepository;

import java.util.List;

public class AssignmentBuildingRepositoryImpl extends SimpleRepository<AssignmentBuildingEntity> implements AssignmentBuildingRepository {

    @Override
    public Long findAssignedId(Long staffId, Long buildingId) {
        StringBuilder sql = new StringBuilder()
                .append("SELECT * FROM assignment_building")
                .append(" WHERE staff_id = " + staffId)
                .append(" AND building_id = " + buildingId);
        List<AssignmentBuildingEntity> result = super.findByCondition(sql.toString());
        if(result != null  && result.size() > 0) {
            return result.get(0).getId();
        }
        return null;
    }
}
