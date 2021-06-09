package com.tcGroup.trainingCenter.domain.service;

import com.tcGroup.trainingCenter.domain.entity.MeasurementData;
import com.tcGroup.trainingCenter.utility.ApplicationException;

import java.util.Date;
import java.util.List;

public interface MeasurementService {

    MeasurementData getById(Long id) throws ApplicationException;
    Long addMeasurement(MeasurementData measurement);
    MeasurementData modifyMeasurement(MeasurementData measurement) throws ApplicationException;
    boolean deleteMeasurement(Long id) throws ApplicationException;
    List<MeasurementData> getMeasurements();
    List<MeasurementData> getMeasurementsForPeriod(Date startDate, Date endDate);
}
