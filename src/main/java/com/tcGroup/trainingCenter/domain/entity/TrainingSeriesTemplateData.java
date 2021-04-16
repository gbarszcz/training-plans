package com.tcGroup.trainingCenter.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcGroup.trainingCenter.utility.entity.AuditData;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TRAINING_SERIES_TEMPLATE")
@AttributeOverrides({
        @AttributeOverride(name="auditCD", column=@Column(name="TST_AUDIT_CD")),
        @AttributeOverride(name="auditCU", column=@Column(name="TST_AUDIT_CU")),
        @AttributeOverride(name="auditMD", column=@Column(name="TST_AUDIT_MD")),
        @AttributeOverride(name="auditMU", column=@Column(name="TST_AUDIT_MU")),
        @AttributeOverride(name="auditRD", column=@Column(name="TST_AUDIT_RD")),
        @AttributeOverride(name="auditRU", column=@Column(name="TST_AUDIT_RU"))
})
@Data
public class TrainingSeriesTemplateData extends AuditData {

    @Id
    @Column(name = "TST_ID")
    @GeneratedValue
    protected long id;

    @ManyToOne(targetEntity = TrainingPlanTemplateData.class)
    @JoinColumn(name = "TST_TPT_ID", referencedColumnName = "TPT_ID")
    @JsonIgnore
    private TrainingPlanTemplateData trainingTemplate;

    @ManyToOne(targetEntity = ExerciseData.class)
    @JoinColumn(name = "TST_EXC_ID")
    private ExerciseData exercise;

    @Column(name = "TST_UNIT")
    private int trainingUnit;

    @Override
    public Long getId() {
        return this.id;
    }

}
