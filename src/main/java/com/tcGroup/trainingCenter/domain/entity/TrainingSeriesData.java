package com.tcGroup.trainingCenter.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcGroup.trainingCenter.utility.entity.AuditData;
import lombok.Data;
import org.hibernate.annotations.Where;

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
@Where(clause = "TSE_AUDIT_RD is null or TSE_AUDIT_RU is null")
public class TrainingSeriesData extends AuditData {

    @Id
    @Column(name = "TSE_ID")
    @GeneratedValue
    protected Long id;

    @ManyToOne(targetEntity = TrainingHistoryData.class)
    @JoinColumn(name = "TSE_THI_ID", referencedColumnName = "THI_ID")
    @JsonIgnore
    private TrainingHistoryData training;

    @ManyToOne(targetEntity = ExerciseData.class)
    @JoinColumn(name = "TSE_EXC_ID", referencedColumnName = "EXC_ID")
    private ExerciseData exercise;

    @Column(name = "TSE_UNIT")
    private int trainingUnit;

    @ManyToOne(targetEntity = TrainingSeriesResultData.class)
    @JoinColumn(name = "TSE_TSR_ID", referencedColumnName = "TSR_ID")
    private TrainingSeriesResultData trainingSeriesResultData;

    @Override
    public Long getId() {
        return this.id;
    }

}
