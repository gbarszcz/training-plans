package com.tcGroup.trainingCenter.domain.dao;

import com.tcGroup.trainingCenter.domain.entity.MeasurementData;
import com.tcGroup.trainingCenter.domain.repository.MeasurementRepository;
import com.tcGroup.trainingCenter.utility.logic.AbstractDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("measurementDAO")
public class MeasurementDAO extends AbstractDAO<MeasurementData, Long> {

    @Autowired
    protected void setRepository(MeasurementRepository repository) {
        super.setRepository(repository);
    }

    protected MeasurementRepository getRepository() {
        return (MeasurementRepository) super.getRepository();
    }

    public List<MeasurementData> findByAccountId(Long id) {
        return this.getRepository().findAllByAccount_Id(id);
    }

}
