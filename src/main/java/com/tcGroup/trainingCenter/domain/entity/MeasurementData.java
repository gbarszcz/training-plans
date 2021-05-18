package com.tcGroup.trainingCenter.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.utility.entity.AuditData;
import lombok.Data;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "MEASUREMENTS")
@AttributeOverrides({
        @AttributeOverride(name="auditCD", column=@Column(name="MSR_AUDIT_CD")),
        @AttributeOverride(name="auditCU", column=@Column(name="MSR_AUDIT_CU")),
        @AttributeOverride(name="auditMD", column=@Column(name="MSR_AUDIT_MD")),
        @AttributeOverride(name="auditMU", column=@Column(name="MSR_AUDIT_MU")),
        @AttributeOverride(name="auditRD", column=@Column(name="MSR_AUDIT_RD")),
        @AttributeOverride(name="auditRU", column=@Column(name="MSR_AUDIT_RU"))
})
@Data
@Where(clause = "MSR_AUDIT_RD is null or MSR_AUDIT_RU is null")
public class MeasurementData extends AuditData {
    @Id
    @Column(name = "MSR_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "MSR_DATE")
    private Date measurementDate;

    @Column(name = "MSR_HEIGHT")
    private Double height;

    @Column(name = "MSR_WEIGHT")
    private Double weight;

    @Column(name = "MSR_CALF")
    private Double calf;

    @Column(name = "MSR_THIGH")
    private Double thigh;

    @Column(name = "MSR_LOINS")
    private Double loins;

    @Column(name = "MSR_HIPS")
    private Double hips;

    @Column(name = "MSR_WAIST")
    private Double waist;

    @Column(name = "MSR_ABDOMEN")
    private Double abdomen;

    @Column(name = "MSR_CHEST")
    private Double chest;

    @Column(name = "MSR_SHOULDERS")
    private Double shoulders;

    @Column(name = "MSR_TRICEPS")
    private Double triceps;

    @Column(name = "MSR_BICEPS")
    private Double biceps;

    @Column(name = "MSR_FOREARM")
    private Double forearm;

    @Column(name = "MSR_WRIST")
    private Double wrist;

    @ManyToOne(targetEntity = AccountData.class)
    @JoinColumn(name = "ACC_ID")
    @JsonIgnore
    private AccountData account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasurementData that = (MeasurementData) o;
        return id.equals(that.id) && measurementDate.equals(that.measurementDate)
                && Objects.equals(weight, that.weight)
                && Objects.equals(calf, that.calf)
                && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, measurementDate, weight, calf, account);
    }
}
