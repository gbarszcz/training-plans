package com.tcGroup.trainingCenter.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.utility.entity.AuditData;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TRAINING_PLANS_TEMPLATES")
@AttributeOverrides({
        @AttributeOverride(name="auditCD", column=@Column(name="TPT_AUDIT_CD")),
        @AttributeOverride(name="auditCU", column=@Column(name="TPT_AUDIT_CU")),
        @AttributeOverride(name="auditMD", column=@Column(name="TPT_AUDIT_MD")),
        @AttributeOverride(name="auditMU", column=@Column(name="TPT_AUDIT_MU")),
        @AttributeOverride(name="auditRD", column=@Column(name="TPT_AUDIT_RD")),
        @AttributeOverride(name="auditRU", column=@Column(name="TPT_AUDIT_RU"))
})
@Data
public class TrainingPlanTemplateData extends AuditData {

    @Id
    @Column(name = "TPT_ID")
    @GeneratedValue
    protected long id;

    @Column(name = "TPT_NAME")
    private String name;

    @ManyToOne(targetEntity = AccountData.class)
    @JoinColumn(name = "ACC_ID", referencedColumnName = "ACC_ID")
    @JsonIgnore
    private AccountData account;

    @OneToMany(mappedBy = "trainingTemplate")
    private List<TrainingSeriesTemplateData> trainingSeriesTemplateData;

    @Override
    public Long getId() {
        return this.id;
    }
}
