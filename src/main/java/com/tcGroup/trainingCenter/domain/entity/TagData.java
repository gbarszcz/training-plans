package com.tcGroup.trainingCenter.domain.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tcGroup.trainingCenter.utility.entity.AuditData;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = false)
public class TagData extends AuditData {

    @Id
    @Column(name = "TAG_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "TAG_CODE", length = 5, nullable = false)
    private String tagCode;

    @Column(name = "TAG_NAME", length = 30, nullable = false)
    private String tagName;

    @Override
    public Long getId() {
        return this.id;
    }
}