package com.tcGroup.trainingCenter.domain.service;

import com.tcGroup.trainingCenter.domain.entity.MeasurementData;

import java.util.List;

public interface MeasurementService {

    MeasurementData getById(Long id);
    Long addMeasurement(MeasurementData measurement);
    MeasurementData modifyMeasurement(MeasurementData measurement);
    boolean deleteMeasurement(Long id);

}
