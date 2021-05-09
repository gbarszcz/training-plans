package com.tcGroup.trainingCenter.domain.service.impl;

import com.tcGroup.trainingCenter.domain.dao.MeasurementDAO;
import com.tcGroup.trainingCenter.domain.entity.MeasurementData;
import com.tcGroup.trainingCenter.domain.service.MeasurementService;
import com.tcGroup.trainingCenter.user.dao.AccountDAO;
import com.tcGroup.trainingCenter.utility.logic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service(value = "measurementService")
public class MeasurementServiceImpl extends AbstractService implements MeasurementService {

    @Autowired
    private MeasurementDAO measurementDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public MeasurementData getById(Long id) {
        MeasurementData item = measurementDAO.getItem(id);
        if (item != null && item.getAccount().getId().equals(getUserContext().getUserId())) {
            return item;
        } else {
            throw new IllegalStateException("No measurement of given id was found");
        }
    }

    @Override
    @Transactional
    public Long addMeasurement(MeasurementData measurementData) {
        measurementData.setAccount(accountDAO.getItem(getUserContext().getUserId()));
        return measurementDAO.createItem(getUserContext(), measurementData);
    }

    @Override
    @Transactional
    public MeasurementData modifyMeasurement(MeasurementData measurementData) {
        try {
            MeasurementData item = getById(measurementData.getId());
            measurementData.setAccount(accountDAO.getItem(getUserContext().getUserId()));
            measurementDAO.modifyItem(getUserContext(), measurementData);
            return item;
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    @Transactional
    public boolean deleteMeasurement(Long id) {
        try {
            MeasurementData item = getById(id);
            measurementDAO.removeItem(getUserContext(), item);
            return true;
        } catch (IllegalStateException ex) {
            return false;
        }
    }

}
