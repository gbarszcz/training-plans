package com.tcGroup.trainingCenter.domain.entity;

import com.tcGroup.trainingCenter.utility.entity.AuditData;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TRAINING_SERIES")
@AttributeOverrides({
        @AttributeOverride(name="auditCD", column=@Column(name="TSE_AUDIT_CD")),
        @AttributeOverride(name="auditCU", column=@Column(name="TSE_AUDIT_CU")),
        @AttributeOverride(name="auditMD", column=@Column(name="TSE_AUDIT_MD")),
        @AttributeOverride(name="auditMU", column=@Column(name="TSE_AUDIT_MU")),
        @AttributeOverride(name="auditRD", column=@Column(name="TSE_AUDIT_RD")),
        @AttributeOverride(name="auditRU", column=@Column(name="TSE_AUDIT_RU"))
})
@Data
public class TrainingSeries extends AuditData {

    @Id
    @Column(name = "TSE_ID")
    @GeneratedValue
    protected long id;

    @ManyToOne(targetEntity = TrainingHistory.class)
    @JoinColumn(name = "TSE_THI_ID", referencedColumnName = "THI_ID")
    private TrainingHistory training;

    @ManyToOne(targetEntity = ExerciseData.class)
    @JoinColumn(name = "TSE_EXC_ID", referencedColumnName = "EXC_ID")
    private ExerciseData exercise;

    @Column(name = "TSE_UNIT")
    private int trainingUnit;

    @ManyToOne(targetEntity = TrainingSeriesResult.class)
    @JoinColumn(name = "TSE_TSR_ID", referencedColumnName = "TSR_ID")
    private TrainingSeriesResult trainingSeriesResult;

    @Override
    public Long getId() {
        return this.id;
    }

}
