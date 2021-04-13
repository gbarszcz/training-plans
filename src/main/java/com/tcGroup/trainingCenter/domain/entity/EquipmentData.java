package com.tcGroup.trainingCenter.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcGroup.trainingCenter.utility.entity.AuditData;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "EQUIPMENTS")
@AttributeOverrides({
        @AttributeOverride(name="auditCD", column=@Column(name="EQU_AUDIT_CD")),
        @AttributeOverride(name="auditCU", column=@Column(name="EQU_AUDIT_CU")),
        @AttributeOverride(name="auditMD", column=@Column(name="EQU_AUDIT_MD")),
        @AttributeOverride(name="auditMU", column=@Column(name="EQU_AUDIT_MU")),
        @AttributeOverride(name="auditRD", column=@Column(name="EQU_AUDIT_RD")),
        @AttributeOverride(name="auditRU", column=@Column(name="EQU_AUDIT_RU"))
})
@Data
public class EquipmentData extends AuditData {

    @Id
    @Column(name = "EQU_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "EQU_NAME", length = 200, nullable = false)
    private String equipmentName;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "equipments")
    @JsonIgnore
    private Set<ExerciseData> exercises;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EquipmentData that = (EquipmentData) o;
        return id.equals(that.id)
                && equipmentName.equals(that.equipmentName)
                && Objects.equals(exercises, that.exercises);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, equipmentName);
    }
}
