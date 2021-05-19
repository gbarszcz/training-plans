package com.tcGroup.trainingCenter.domain.service.impl;

import com.tcGroup.trainingCenter.domain.dao.MeasurementDAO;
import com.tcGroup.trainingCenter.domain.entity.MeasurementData;
import com.tcGroup.trainingCenter.domain.service.MeasurementService;
import com.tcGroup.trainingCenter.user.dao.AccountDAO;
import com.tcGroup.trainingCenter.utility.AppParams;
import com.tcGroup.trainingCenter.utility.ApplicationException;
import com.tcGroup.trainingCenter.utility.logic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service(value = "measurementService")
public class MeasurementServiceImpl extends AbstractService implements MeasurementService {

    @Autowired
    private MeasurementDAO measurementDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public MeasurementData getById(Long id) throws ApplicationException {
        MeasurementData item = measurementDAO.getItem(id);
        if (item == null || !item.getAccount().getId().equals(getUserContext().getUserId()))
            throw new ApplicationException(ResourceBundle.getBundle(AppParams.EXCEPTION_MESSAGES_RESOURCE, Locale.getDefault())
                    .getString("measurement.invalidId"), id);
        return item;
    }

    @Override
    @Transactional
    public Long addMeasurement(MeasurementData measurementData) {
        measurementData.setAccount(accountDAO.getItem(getUserContext().getUserId()));
        return measurementDAO.createItem(getUserContext(), measurementData);
    }

    @Override
    @Transactional
    public MeasurementData modifyMeasurement(MeasurementData measurementData) throws ApplicationException {
        MeasurementData item = getById(measurementData.getId());
        measurementData.setAccount(accountDAO.getItem(getUserContext().getUserId()));
        measurementDAO.modifyItem(getUserContext(), measurementData);
        return item;
    }

    @Override
    @Transactional
    public boolean deleteMeasurement(Long id) throws ApplicationException {
        MeasurementData item = getById(id);
        measurementDAO.removeItem(getUserContext(), item);
        return true;
    }

    @Override
    public List<MeasurementData> getMeasurements() {
        return measurementDAO.findByAccountId(getUserContext().getUserId());
    }

    @Override
    public List<MeasurementData> getMeasurementsForPeriod(Date startDate, Date endDate) {
        return measurementDAO.findByAccountIdAndMeasurementDate(getUserContext().getUserId(), startDate, endDate);
    }

}
