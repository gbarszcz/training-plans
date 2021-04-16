package com.tcGroup.trainingCenter.utility.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@MappedSuperclass
@Data
public class AuditData implements Serializable {

    private static final long serialVersionUID = -5623315375805555641L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AUDIT_CD")
    @JsonIgnore
    private Date auditCD;

    @Column(name = "AUDIT_CU")
    @JsonIgnore
    private Long auditCU;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AUDIT_MD")
    @JsonIgnore
    private Date auditMD;

    @Column(name = "AUDIT_MU")
    @JsonIgnore
    private Long auditMU;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AUDIT_RD")
    @JsonIgnore
    private Date auditRD;

    @Column(name = "AUDIT_RU")
    @JsonIgnore
    private Long auditRU;

    public Long getId() {
        return null;
    }
}