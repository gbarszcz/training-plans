package com.tcGroup.trainingCenter.domain.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcGroup.trainingCenter.utility.entity.AuditData;

import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TAGS")
@AttributeOverrides({
    @AttributeOverride(name="auditCD", column=@Column(name="TAG_AUDIT_CD")),
    @AttributeOverride(name="auditCU", column=@Column(name="TAG_AUDIT_CU")),
    @AttributeOverride(name="auditMD", column=@Column(name="TAG_AUDIT_MD")),
    @AttributeOverride(name="auditMU", column=@Column(name="TAG_AUDIT_MU")),
    @AttributeOverride(name="auditRD", column=@Column(name="TAG_AUDIT_RD")),
    @AttributeOverride(name="auditRU", column=@Column(name="TAG_AUDIT_RU"))
})
@Data
public class TagData extends AuditData {

    @Id
    @Column(name = "TAG_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "TAG_CODE", length = 5, nullable = false)
    private String tagCode;

    @Column(name = "TAG_NAME", length = 30, nullable = false)
    private String tagName;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "tags")
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
        TagData tagData = (TagData) o;
        return id.equals(tagData.id) && tagCode.equals(tagData.tagCode)
                && tagName.equals(tagData.tagName)
                && Objects.equals(exercises, tagData.exercises);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, tagCode, tagName);
    }
}