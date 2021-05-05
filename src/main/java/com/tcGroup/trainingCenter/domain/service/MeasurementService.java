package com.tcGroup.trainingCenter.domain.service;

import com.tcGroup.trainingCenter.domain.entity.MeasurementData;

import java.util.List;

public interface MeasurementService {

    MeasurementData getById(Long id);
    List<MeasurementData> getAllDataForAccount();
    Long addMeasurement(MeasurementData measurement);
    MeasurementData modifyMeasurement(MeasurementData measurement);

}
