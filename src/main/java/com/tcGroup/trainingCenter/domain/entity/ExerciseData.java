package com.tcGroup.trainingCenter.domain.entity;

import javax.persistence.*;

import com.tcGroup.trainingCenter.domain.enumeration.DifficultyLevel;
import com.tcGroup.trainingCenter.domain.enumeration.converter.DifficultyLevelConverter;
import com.tcGroup.trainingCenter.utility.entity.AuditData;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @Column(name = "EXC_DIFFICULTY_LVL", length = 1, nullable = false)
    @Convert(converter = DifficultyLevelConverter.class)
    private DifficultyLevel exerciseDifficultyLvl = DifficultyLevel.LOW;

    @Column(name = "EXC_DEMO") 
    private boolean exerciseDemo = false;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "EXERCISE_EQUIPMENT",
            joinColumns = @JoinColumn(name = "EXC_ID"),
            inverseJoinColumns = @JoinColumn(name = "EQU_ID"))
    private List<EquipmentData> equipments;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "EXERCISE_TAG",
            joinColumns = @JoinColumn(name = "EXC_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<TagData> tags = new HashSet<>();

    @Override
    public Long getId() {
        return this.id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExerciseData that = (ExerciseData) o;
        return exerciseDemo == that.exerciseDemo
                && id.equals(that.id)
                && exerciseName.equals(that.exerciseName)
                && Objects.equals(exerciseDescription, that.exerciseDescription)
                && exerciseDifficultyLvl == that.exerciseDifficultyLvl
                && equipments.equals(that.equipments)
                && Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, exerciseName, exerciseDescription, exerciseDifficultyLvl, exerciseDemo, equipments, tags);
    }
}