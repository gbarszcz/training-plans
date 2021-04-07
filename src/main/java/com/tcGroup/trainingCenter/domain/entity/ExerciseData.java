package com.tcGroup.trainingCenter.domain.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tcGroup.trainingCenter.domain.enumeration.DifficultyLevel;
import com.tcGroup.trainingCenter.domain.enumeration.converter.DifficultyLevelConverter;
import com.tcGroup.trainingCenter.utility.entity.AuditData;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "EXERCISES")
@AttributeOverrides({
    @AttributeOverride(name="auditCD", column=@Column(name="EXC_AUDIT_CD")),
    @AttributeOverride(name="auditCU", column=@Column(name="EXC_AUDIT_CU")),
    @AttributeOverride(name="auditMD", column=@Column(name="EXC_AUDIT_MD")),
    @AttributeOverride(name="auditMU", column=@Column(name="EXC_AUDIT_MU")),
    @AttributeOverride(name="auditRD", column=@Column(name="EXC_AUDIT_RD")),
    @AttributeOverride(name="auditRU", column=@Column(name="EXC_AUDIT_RU"))
})
@Data
@EqualsAndHashCode(callSuper = false)
public class ExerciseData extends AuditData {
    
    private static final long serialVersionUID = 5311503488834008387L;

    @Id
    @Column(name = "EXC_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "EXC_NAME", length = 30, nullable = false)
    private String exerciseName;

    @Column(name = "EXC_DESCRIPTION", length = 200)
    private String exerciseDescription;

    @Column(name = "EXC_EQ_NEEDED", length = 200)
    private String exerciseEQNeeded;

    @Column(name = "EXC_TAGS", length = 200)
    private String exerciseTags;

    @Column(name = "EXC_DIFFICULTY_LVL", length = 1, nullable = false)
    @Convert(converter = DifficultyLevelConverter.class)
    private DifficultyLevel exerciseDifficultyLvl = DifficultyLevel.LOW;

    @Column(name = "EXC_DEMO") 
    private boolean exerciseDemo = false;

    @Override
    public Long getId() {
        return this.id;
    }
}