package com.tcGroup.trainingCenter.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcGroup.trainingCenter.domain.enumeration.DifficultyLevel;
import com.tcGroup.trainingCenter.domain.enumeration.converter.DifficultyLevelConverter;
import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.utility.entity.AuditData;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @JsonIgnore
    private AccountData account;

    @OneToMany(mappedBy = "training")
    private List<TrainingSeriesData> trainingSeriesData;

    @Column(name = "THI_TITLE")
    private String title;

    @Column(name = "THI_DIFFICULTY_LVL", length = 1)
    @Convert(converter = DifficultyLevelConverter.class)
    private DifficultyLevel difficulty;

    @Override
    public Long getId() {
        return this.id;
    }

}
