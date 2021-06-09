package com.tcGroup.trainingCenter.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class AggregateResponse {

    private Double maxReps;
    private Double minReps;
    private Double avgReps;

}
