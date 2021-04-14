package com.tcGroup.trainingCenter.domain.entity;

import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.utility.entity.AuditData;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRAININGS_HISTORY")
@AttributeOverrides({
        @AttributeOverride(name="auditCD", column=@Column(name="THI_AUDIT_CD")),
        @AttributeOverride(name="auditCU", column=@Column(name="THI_AUDIT_CU")),
        @AttributeOverride(name="auditMD", column=@Column(name="THI_AUDIT_MD")),
        @AttributeOverride(name="auditMU", column=@Column(name="THI_AUDIT_MU")),
        @AttributeOverride(name="auditRD", column=@Column(name="THI_AUDIT_RD")),
        @AttributeOverride(name="auditRU", column=@Column(name="THI_AUDIT_RU"))
})
@Data
public class TrainingHistoryData extends AuditData {

    @Id
    @Column(name = "THI_ID")
    @GeneratedValue
    protected long id;

    @Column(name = "THI_DATE")
    private Date trainingDate;

    @ManyToOne(targetEntity = AccountData.class)
    @JoinColumn(name = "ACC_ID")
    private AccountData account;

    @Override
    public Long getId() {
        return this.id;
    }

}
