package com.tcGroup.trainingCenter.domain.repository;

import com.tcGroup.trainingCenter.domain.entity.MeasurementData;
import com.tcGroup.trainingCenter.utility.logic.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("measurementRepository")
public interface MeasurementRepository extends AbstractRepository<MeasurementData, Long> {

    List<MeasurementData> findAllByAccount_Id(long accountId);

}
