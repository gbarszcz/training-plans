package com.tcGroup.trainingCenter.domain.controller;

import com.tcGroup.trainingCenter.domain.entity.MeasurementData;
import com.tcGroup.trainingCenter.domain.service.MeasurementService;
import com.tcGroup.trainingCenter.utility.ApplicationException;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@RequestMapping("measurement")
@RestController
public class MeasurementController extends AbstractController {

    @Autowired
    private MeasurementService measurementService;

    @GetMapping
    @ResponseBody
    public List<MeasurementData> getMeasurementsForAccount() {
        return measurementService.getMeasurements();
    }

    @GetMapping("/{id}")
    public MeasurementData getMeasurementData(@PathVariable Long id) {
        try {
            return measurementService.getById(id);
        } catch (ApplicationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PostMapping
    public Long addMeasurementData(@RequestBody @Valid MeasurementData request) {
        return measurementService.addMeasurement(request);
    }

    @PutMapping
    @ResponseBody
    public MeasurementData modifyMeasurementData(@RequestBody @Valid MeasurementData request) {
        try {
            return measurementService.modifyMeasurement(request);
        } catch (ApplicationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public boolean deleteMeasurementData(@PathVariable Long id) {
        try {
            return measurementService.deleteMeasurement(id);
        } catch (ApplicationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/{period}/{duration}")
    @ResponseBody
    public List<MeasurementData> getMeasurementsForAccount(@PathVariable String period, @PathVariable Integer duration) {
        ChronoUnit chronoUnit = ChronoUnit.valueOf(period.toUpperCase());
        LocalDate startDate = LocalDate.now().minus(duration, chronoUnit);
        Date from = Date.from(startDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
        Date to = Date.from(Instant.now());
        return measurementService.getMeasurementsForPeriod(from, to);
    }

}
