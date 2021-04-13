package com.tcGroup.trainingCenter.domain.entity;

import com.tcGroup.trainingCenter.domain.enumeration.IterationUnit;
import com.tcGroup.trainingCenter.utility.entity.AuditData;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TRAINING_SERIES_RESULTS")
@AttributeOverrides({
        @AttributeOverride(name="auditCD", column=@Column(name="TSR_AUDIT_CD")),
        @AttributeOverride(name="auditCU", column=@Column(name="TSR_AUDIT_CU")),
        @AttributeOverride(name="auditMD", column=@Column(name="TSR_AUDIT_MD")),
        @AttributeOverride(name="auditMU", column=@Column(name="TSR_AUDIT_MU")),
        @AttributeOverride(name="auditRD", column=@Column(name="TSR_AUDIT_RD")),
        @AttributeOverride(name="auditRU", column=@Column(name="TSR_AUDIT_RU"))
})
@Data
public class TrainingSeriesResult extends AuditData {

    @Id
    @Column(name = "TSR_ID")
    @GeneratedValue
    protected long id;

    @Column(name = "TSR_ITERATION_UNIT")
    @Enumerated(EnumType.STRING)
    private IterationUnit name;

    @Column(name = "TSR_ITERATION_COUNT")
    private Double iterationCount;

    @Column(name = "TSR_ADDL_WEIGHT")
    private Double additionalWeight;

    @Column(name = "TSR_RESULT")
    private Double result;

    @Override
    public Long getId() {
        return this.id;
    }

}
